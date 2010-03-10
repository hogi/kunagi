package scrum.client.journal;

import java.util.List;

import scrum.client.common.AScrumGwtEntity;

public class ChangeHistoryManager extends GChangeHistoryManager {

	public List<Change> getChanges(AScrumGwtEntity entity) {
		return dao.getChangesByParent(entity);
	}

}
