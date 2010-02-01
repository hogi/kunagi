package scrum.client.project;

import ilarkesto.gwt.client.Gwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import scrum.client.ScrumJs;
import scrum.client.admin.User;
import scrum.client.common.ReferenceSupport;
import scrum.client.estimation.RequirementEstimationVote;
import scrum.client.issues.Issue;
import scrum.client.sprint.Sprint;
import scrum.client.sprint.Task;

public class Requirement extends GRequirement implements ReferenceSupport {

	public static final String REFERENCE_PREFIX = "req";
	public static String[] WORK_ESTIMATION_VALUES = new String[] { "", "0.5", "1", "2", "3", "5", "8", "13", "20",
			"40", "100" };

	private transient EstimationBar estimationBar;

	public Requirement(Project project) {
		setProject(project);
		setDirty(true);
	}

	public Requirement(Issue issue) {
		setProject(issue.getProject());
		setLabel(issue.getLabel());
		setDescription(issue.getDescription());
	}

	public Requirement(Map data) {
		super(data);
	}

	public String getEstimatedWorkAsString() {
		Float work = getEstimatedWork();
		if (work == null) return null;
		if (work <= 0.5f) return work.toString();
		return String.valueOf(work.intValue());
	}

	public String getEstimatedWorkWithUnit() {
		String work = getEstimatedWorkAsString();
		return work == null ? null : work + " " + getProject().getEffortUnit();
	}

	public List<RequirementEstimationVote> getEstimationVotes() {
		return getDao().getRequirementEstimationVotesByRequirement(this);
	}

	public boolean containsWorkEstimationVotes() {
		for (RequirementEstimationVote vote : getEstimationVotes()) {
			if (vote.getEstimatedWork() != null) return true;
		}
		return false;
	}

	public RequirementEstimationVote getEstimationVote(User user) {
		for (RequirementEstimationVote vote : getEstimationVotes()) {
			if (vote.isUser(user)) return vote;
		}
		return null;
	}

	public void setVote(Float estimatedWork) {
		RequirementEstimationVote vote = getEstimationVote(cm.getAuth().getUser());
		if (vote == null) {
			vote = new RequirementEstimationVote(this, cm.getAuth().getUser());
			getDao().createRequirementEstimationVote(vote);
		}
		vote.setEstimatedWork(estimatedWork);
	}

	public void activateWorkEstimationVoting() {
		setWorkEstimationVotingActive(true);
	}

	public void deactivateWorkEstimationVoting() {
		setWorkEstimationVotingActive(false);
	}

	public void activateWorkEstimationVotingShowoff() {
		setWorkEstimationVotingShowoff(true);
		// TODO autoset estimated work?
	}

	public void resetWorkEstimationVoting() {
		setWorkEstimationVotingShowoff(false);
		for (RequirementEstimationVote vote : getEstimationVotes()) {
			vote.setEstimatedWork(null);
		}
	}

	public void applyEstimationVoting(Float estimation) {
		setEstimatedWork(estimation);
		resetWorkEstimationVoting();
		deactivateWorkEstimationVoting();
		setDirty(false);
	}

	public String getTaskStatusLabel() {
		int burned = Task.sumBurnedWork(getTasks());
		int remaining = Task.sumRemainingWork(getTasks());
		if (remaining == 0) return "100% completed, " + burned + " hrs burned";
		int burnedPercent = Gwt.percent(burned + remaining, burned);
		return burnedPercent + "% completed, " + remaining + " hrs left";
	}

	public void setEstimationBar(EstimationBar estimationBar) {
		this.estimationBar = estimationBar;
	}

	public EstimationBar getEstimationBar() {
		return estimationBar;
	}

	public boolean isValidForSprint() {
		if (!isEstimatedWorkValid()) return false;
		return true;
	}

	public boolean isEstimatedWorkValid() {
		return !isDirty() && getEstimatedWork() != null;
	}

	public String getLongLabel() {
		StringBuilder sb = new StringBuilder();
		sb.append(getLabel());
		if (!isEstimatedWorkValid()) sb.append(" [requires estimation]");
		if (isInCurrentSprint()) sb.append(" [In Sprint]");
		return sb.toString();
	}

	public boolean isInCurrentSprint() {
		return isSprintSet() && getProject().isCurrentSprint(getSprint());
	}

	public String getReferenceAndLabel() {
		return getReference() + " " + getLabel();
	}

	public String getReference() {
		return REFERENCE_PREFIX + getNumber();
	}

	/**
	 * No tasks created yet.
	 */
	public boolean isPlanned() {
		return !getTasks().isEmpty();
	}

	/**
	 * All tasks are done. Not closed yet.
	 */
	public boolean isTasksClosed() {
		Collection<Task> tasks = getTasks();
		if (tasks.isEmpty()) return false;
		for (Task task : tasks) {
			if (!task.isClosed()) return false;
		}
		return true;
	}

	/**
	 * Summary to show in the product backlog.
	 */
	public String getProductBacklogSummary() {
		String summary = isDirty() ? "[dirty] " : "[not dirty] ";
		if (isClosed()) return summary += "Closed.";
		if (isTasksClosed()) return summary += "Done. Test required.";
		if (getEstimatedWork() == null) return summary += "No effort estimated.";
		if (!isSprintSet()) return summary += getEstimatedWorkWithUnit() + " to do. No sprint assigned.";
		Sprint sprint = getSprint();
		return summary += getEstimatedWorkWithUnit() + " to do in sprint " + sprint.getLabel() + ".";
	}

	/**
	 * Summary to show in the sprint backlog.
	 */
	public String getSprintBacklogSummary() {
		if (isClosed()) return "Closed.";
		if (!isPlanned()) return "Not planned yet.";
		if (isTasksClosed()) return "Done. Test required.";
		int taskCount = 0;
		int openTaskCount = 0;
		int effort = 0;
		for (Task task : getTasks()) {
			taskCount++;
			if (!task.isClosed()) {
				openTaskCount++;
				effort += task.getRemainingWork();
			}
		}
		return openTaskCount + " of " + taskCount + " Tasks open. About " + effort + " hours to do.";
	}

	public int getBurnedWorkInClosedTasks() {
		return Task.sumBurnedWork(getClosedTasks());
	}

	public int getBurnedWork() {
		return Task.sumBurnedWork(getTasks());
	}

	public int getBurnedWorkInClaimedTasks() {
		return Task.sumBurnedWork(getClaimedTasks());
	}

	public int getRemainingWorkInClaimedTasks() {
		return Task.sumRemainingWork(getClaimedTasks());
	}

	public int getRemainingWorkInUnclaimedTasks() {
		return Task.sumRemainingWork(getUnclaimedTasks());
	}

	public int getRemainingWork() {
		return Task.sumRemainingWork(getTasks());
	}

	public List<Task> getClaimedTasks() {
		List<Task> ret = new ArrayList<Task>();
		for (Task task : getTasks()) {
			if (task.isOwnerSet() && !task.isClosed()) ret.add(task);
		}
		return ret;
	}

	public List<Task> getClaimedTasks(User owner) {
		List<Task> ret = new ArrayList<Task>();
		for (Task task : getTasks()) {
			if (task.isOwner(owner) && !task.isClosed()) ret.add(task);
		}
		return ret;
	}

	public List<Task> getClosedTasks() {
		List<Task> ret = new ArrayList<Task>();
		for (Task task : getTasks()) {
			if (task.isClosed()) ret.add(task);
		}
		return ret;
	}

	public List<Task> getUnclaimedTasks() {
		List<Task> ret = new ArrayList<Task>();
		for (Task task : getTasks()) {
			if (!task.isOwnerSet()) ret.add(task);
		}
		return ret;
	}

	public List<Task> getTasks() {
		return getDao().getTasksByRequirement(this);
	}

	public static int sumBurnedWork(Iterable<Requirement> requirements) {
		int sum = 0;
		for (Requirement requirement : requirements) {
			sum += requirement.getBurnedWork();
		}
		return sum;
	}

	public Task createNewTask() {
		Task task = new Task(this);
		getDao().createTask(task);
		return task;
	}

	public void deleteTask(Task task) {
		getDao().deleteTask(task);
	}

	@Override
	public boolean isEditable() {
		if (isInCurrentSprint()) return false;
		if (!cm.getProjectContext().getProject().isProductOwner(cm.getAuth().getUser())) return false;
		return true;
	}

	@Override
	public String toHtml() {
		return ScrumJs.createShowEntityByReferenceLink(getReference()) + " " + Gwt.escapeHtml(getLabel());
	}

	@Override
	public String toString() {
		return getReferenceAndLabel();
	}

}
