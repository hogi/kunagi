package scrum.server.sprint;

import java.util.Set;

import scrum.server.project.BacklogItem;
import scrum.server.project.BacklogItemDao;

public class Sprint extends GSprint {

	// --- dependencies ---

	private static BacklogItemDao backlogItemDao;

	public Sprint(Sprint template) {
		super(template);
	}

	public Sprint() {
		super(null);
	}

	public static void setBacklogItemDao(BacklogItemDao backlogItemDao) {
		Sprint.backlogItemDao = backlogItemDao;
	}

	// --- ---

	public Set<BacklogItem> getBacklogItems() {
		return backlogItemDao.getBacklogItemsBySprint(this);
	}

}
