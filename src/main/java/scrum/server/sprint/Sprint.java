package scrum.server.sprint;

import ilarkesto.base.time.Date;

import java.util.List;
import java.util.Set;

import scrum.server.project.Requirement;
import scrum.server.project.RequirementDao;

public class Sprint extends GSprint {

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

	public List<SprintDaySnapshot> getDaySnapshots() {
		return sprintDaySnapshotDao.getSprintDaySnapshots(this);
	}

	public int getLenghtInDays() {
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
	public String toString() {
		return getLabel();
	}

}
