package scrum.client.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import scrum.client.ScrumGwtApplication;
import scrum.client.collaboration.Comment;
import scrum.client.issues.Issue;
import scrum.client.sprint.Sprint;
import scrum.client.sprint.Task;

public class Requirement extends GRequirement {

	public static final String INIT_LABEL = "New Requirement";

	public Requirement(Project project) {
		setProject(project);
		setLabel(INIT_LABEL);
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

	public Comment createNewComment() {
		Comment comment = new Comment(this);
		getDao().createComment(comment);
		return comment;
	}

	public List<Comment> getComments() {
		return getDao().getCommentsByParent(this);
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
		sb.append(getReference()).append(" ").append(getLabel());
		if (!isEstimatedWorkValid()) sb.append(" [requires estimation]");
		if (isInCurrentSprint()) sb.append(" [In Sprint]");
		return sb.toString();
	}

	public boolean isInCurrentSprint() {
		return isSprintSet() && ScrumGwtApplication.get().getProject().isCurrentSprint(getSprint());
	}

	public String getReference() {
		return "r" + getNumber();
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
	public boolean isDone() {
		Collection<Task> tasks = getTasks();
		if (tasks.isEmpty()) return false;
		for (Task task : tasks) {
			if (!task.isDone()) return false;
		}
		return true;
	}

	/**
	 * Summary to show in the product backlog.
	 */
	public String getProductBacklogSummary() {
		String summary = isDirty() ? "[dirty] " : "[not dirty] ";
		if (isClosed()) return summary += "Closed.";
		if (isDone()) return summary += "Done. Test required.";
		if (getEstimatedWork() == null) return summary += "No effort estimated.";
		if (!isSprintSet()) return summary += getEstimatedWorkAsString() + " to do. No sprint assigned.";
		Sprint sprint = getSprint();
		return summary += getEstimatedWorkAsString() + " to do in sprint " + sprint.getLabel() + ".";
	}

	/**
	 * Summary to show in the sprint backlog.
	 */
	public String getSprintBacklogSummary() {
		if (isClosed()) return "Closed.";
		if (!isPlanned()) return "Not planned yet.";
		if (isDone()) return "Done. Test required.";
		int taskCount = 0;
		int openTaskCount = 0;
		int effort = 0;
		for (Task task : getTasks()) {
			taskCount++;
			if (!task.isDone()) {
				openTaskCount++;
				effort += task.getRemainingWork();
			}
		}
		return openTaskCount + " of " + taskCount + " Tasks open. About " + effort + " hours to do.";
	}

	public String getEstimatedWorkAsString() {
		if (getEstimatedWork() == null) return null;
		return getEstimatedWork() + " " + ScrumGwtApplication.get().getProject().getEffortUnit();
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
		return Task.sumRemainingWork(getUnlaimedTasks());
	}

	public int getRemainingWork() {
		return Task.sumRemainingWork(getTasks());
	}

	public List<Task> getClaimedTasks() {
		List<Task> ret = new ArrayList<Task>();
		for (Task task : getTasks()) {
			if (task.isOwnerSet()) ret.add(task);
		}
		return ret;
	}

	public List<Task> getClosedTasks() {
		List<Task> ret = new ArrayList<Task>();
		for (Task task : getTasks()) {
			if (task.isDone()) ret.add(task);
		}
		return ret;
	}

	public List<Task> getUnlaimedTasks() {
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
	public String toString() {
		return getReference() + " " + getLabel();
	}
}
