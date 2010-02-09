package scrum.server.sprint;

import ilarkesto.base.time.Date;
import ilarkesto.logging.Logger;

import java.util.List;
import java.util.Set;

import scrum.server.ScrumWebApplication;
import scrum.server.project.Requirement;
import scrum.server.project.RequirementDao;

public class Sprint extends GSprint {

	private static final Logger LOG = Logger.get(Sprint.class);

	// --- dependencies ---

	private static RequirementDao requirementDao;
	private static TaskDao taskDao;
	private static SprintDaySnapshotDao sprintDaySnapshotDao;

	public static void setRequirementDao(RequirementDao storyDao) {
		Sprint.requirementDao = storyDao;
	}

	public static void setTaskDao(TaskDao taskDao) {
		Sprint.taskDao = taskDao;
	}

	public static void setSprintDaySnapshotDao(SprintDaySnapshotDao sprintDaySnapshotDao) {
		Sprint.sprintDaySnapshotDao = sprintDaySnapshotDao;
	}

	// --- ---

	public void close() {
		float velocity = 0;
		StringBuilder sb = new StringBuilder();
		for (Requirement requirement : getRequirements()) {
			if (requirement.isClosed()) {
				Float work = requirement.getEstimatedWork();
				if (work != null) velocity += work;
				sb.append("* ");
				sb.append(requirement.getLabel());
				sb.append("\n");
			}
			requirement.setSprint(null);
		}
		setVelocity(velocity);
		getProject().setVelocity(Math.round(velocity));
		setCompletedRequirementLabels(sb.toString());
	}

	public List<SprintDaySnapshot> getDaySnapshots() {
		return sprintDaySnapshotDao.getSprintDaySnapshots(this);
	}

	public Set<SprintDaySnapshot> getExistingDaySnapshots() {
		return sprintDaySnapshotDao.getSprintDaySnapshotsBySprint(this);
	}

	public Integer getLengthInDays() {
		if (!isBeginSet() || !isEndSet()) return null;
		return getBegin().getPeriodTo(getEnd()).toDays();
	}

	public SprintDaySnapshot getDaySnapshot(Date date) {
		return sprintDaySnapshotDao.getSprintDaySnapshot(this, date, true);
	}

	public int getRemainingWork() {
		int sum = 0;
		for (Task task : getTasks()) {
			Integer effort = task.getRemainingWork();
			if (effort != null) sum += effort;
		}
		return sum;
	}

	public int getBurnedWork() {
		int sum = 0;
		for (Task task : getTasks()) {
			sum += task.getBurnedWork();
		}
		return sum;
	}

	public Set<Requirement> getRequirements() {
		return requirementDao.getRequirementsBySprint(this);
	}

	public Set<Task> getTasks() {
		return taskDao.getTasksBySprint(this);
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();

		if (getProject().isCurrentSprint(this)) {
			if (!isBeginSet()) setBegin(Date.today());
			if (!isEndSet()) setEnd(getBegin().addDays(14));
		}

		// delete when not current and end date older than 4 weeks
		// if (isEndSet() && !getProject().isCurrentSprint(this) && getEnd().isPast()
		// && getEnd().getPeriodToNow().toWeeks() > 4) {
		// LOG.info("Deleting sprint, which ended on", getEnd(), "->", toString());
		// getDao().deleteEntity(this);
		// }

		// renaming: req -> sto
		if (ScrumWebApplication.REQ_RENAMING_DATE.isAfter(getLastModified())) {
			setCompletedRequirementLabels(ScrumWebApplication.convertReqToSto(getCompletedRequirementLabels()));
		}
	}

	@Override
	public String toString() {
		return getLabel();
	}

}
