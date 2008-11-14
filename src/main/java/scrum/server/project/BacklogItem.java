package scrum.server.project;

import java.util.Set;

import scrum.server.sprint.Task;
import scrum.server.sprint.TaskDao;

public class BacklogItem extends GBacklogItem {

	// --- dependencies ---

	private static TaskDao taskDao;

	public BacklogItem(BacklogItem template) {
		super(template);
	}

	public BacklogItem() {
		super(null);
	}

	public static void setTaskDao(TaskDao taskDao) {
		BacklogItem.taskDao = taskDao;
	}

	// --- ---

	public Set<Task> getTasks() {
		return taskDao.getTasksByBacklogItem(this);
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
