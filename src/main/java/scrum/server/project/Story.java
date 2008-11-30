package scrum.server.project;

import java.util.Set;

import scrum.server.sprint.Task;
import scrum.server.sprint.TaskDao;

public class Story extends GStory {

	// --- dependencies ---

	private static TaskDao taskDao;

	public Story(Story template) {
		super(template);
	}

	public Story() {
		super(null);
	}

	public static void setTaskDao(TaskDao taskDao) {
		Story.taskDao = taskDao;
	}

	// --- ---

	public Set<Task> getTasks() {
		return taskDao.getTasksByStory(this);
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
