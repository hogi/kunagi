package scrum.server.sprint;

import ilarkesto.base.time.Date;

import java.util.Set;

import scrum.server.project.Story;
import scrum.server.project.StoryDao;

public class Sprint extends GSprint {

	// --- dependencies ---

	private static StoryDao storyDao;
	private static TaskDao taskDao;
	private static SprintDaySnapshotDao sprintDaySnapshotDao;

	public Sprint(Sprint template) {
		super(template);
	}

	public Sprint() {
		super(null);
	}

	public static void setStoryDao(StoryDao storyDao) {
		Sprint.storyDao = storyDao;
	}

	public static void setTaskDao(TaskDao taskDao) {
		Sprint.taskDao = taskDao;
	}

	public static void setSprintDaySnapshotDao(SprintDaySnapshotDao sprintDaySnapshotDao) {
		Sprint.sprintDaySnapshotDao = sprintDaySnapshotDao;
	}

	// --- ---

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

	public Set<Story> getStorys() {
		return storyDao.getStorysBySprint(this);
	}

	public Set<Task> getTasks() {
		return taskDao.getTasksBySprint(this);
	}

	@Override
	public String toString() {
		return getLabel();
	}

}
