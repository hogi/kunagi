package scrum.server.project;

import java.util.Set;

import scrum.server.admin.User;
import scrum.server.common.Numbered;
import scrum.server.estimation.RequirementEstimationVote;
import scrum.server.estimation.RequirementEstimationVoteDao;
import scrum.server.sprint.Task;
import scrum.server.sprint.TaskDao;

public class Requirement extends GRequirement implements Numbered {

	// --- dependencies ---

	private static TaskDao taskDao;
	private static RequirementEstimationVoteDao requirementEstimationVoteDao;

	public static void setRequirementEstimationVoteDao(RequirementEstimationVoteDao requirementEstimationVoteDao) {
		Requirement.requirementEstimationVoteDao = requirementEstimationVoteDao;
	}

	public static void setTaskDao(TaskDao taskDao) {
		Requirement.taskDao = taskDao;
	}

	// --- ---

	public boolean isInCurrentSprint() {
		if (!isSprintSet()) return false;
		return isSprint(getProject().getCurrentSprint());
	}

	public void initializeEstimationVotes() {
		for (User user : getProject().getTeamMembers()) {
			RequirementEstimationVote vote = getEstimationVote(user);
			if (vote == null) vote = requirementEstimationVoteDao.postVote(this, user);
			vote.setEstimatedWork(null);
		}
	}

	private RequirementEstimationVote getEstimationVote(User user) {
		return requirementEstimationVoteDao.getRequirementEstimationVoteByUser(this, user);
	}

	public Set<RequirementEstimationVote> getEstimationVotes() {
		return requirementEstimationVoteDao.getRequirementEstimationVotesByRequirement(this);
	}

	public void clearEstimationVotes() {
		for (RequirementEstimationVote vote : getEstimationVotes()) {
			requirementEstimationVoteDao.deleteEntity(vote);
		}
	}

	public boolean isTasksClosed() {
		for (Task task : getTasks()) {
			if (!task.isClosed()) return false;
		}
		return true;
	}

	public String getReferenceAndLabel() {
		return getReference() + " (" + getLabel() + ")";
	}

	public String getReference() {
		return scrum.client.project.Requirement.REFERENCE_PREFIX + getNumber();
	}

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateRequirementNumber());
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();

		// delete estimation votes when closed or in sprint
		if (isClosed() || isSprintSet()) clearEstimationVotes();

		// delete when closed and older than 4 weeks
		if (isClosed() && getLastModified().getPeriodToNow().toWeeks() > 4) getDao().deleteEntity(this);

	}

	public Set<Task> getTasks() {
		return taskDao.getTasksByRequirement(this);
	}

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	@Override
	public String toString() {
		return getReferenceAndLabel();
	}
}
