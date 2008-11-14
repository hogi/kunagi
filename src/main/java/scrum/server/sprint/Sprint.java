package scrum.server.sprint;

import java.util.Set;

import scrum.server.project.BacklogItem;
import scrum.server.project.BacklogItemDao;

public class Sprint extends GSprint {

	// --- dependencies ---

	private static BacklogItemDao backlogItemDao;
	private static TaskDao taskDao;

	public Sprint(Sprint template) {
		super(template);
	}

	public Sprint() {
		super(null);
	}

	public static void setBacklogItemDao(BacklogItemDao backlogItemDao) {
		Sprint.backlogItemDao = backlogItemDao;
	}

	public static void setTaskDao(TaskDao taskDao) {
		Sprint.taskDao = taskDao;
	}

	// --- ---

	public Set<BacklogItem> getBacklogItems() {
		return backlogItemDao.getBacklogItemsBySprint(this);
	}

	public Set<Task> getTasks() {
		return taskDao.getTasksBySprint(this);
	}

	@Override
	public String toString() {
		return getLabel();
	}

}
