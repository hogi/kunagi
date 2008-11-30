package scrum.server.sprint;

import java.util.Set;

import scrum.server.project.Story;
import scrum.server.project.StoryDao;

public class Sprint extends GSprint {

	// --- dependencies ---

	private static StoryDao storyDao;
	private static TaskDao taskDao;

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

	// --- ---

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
