package scrum.client.journal;

import java.util.List;

import scrum.client.common.AScrumGwtEntity;

public class ChangeHistoryManager extends GChangeHistoryManager {

	private AScrumGwtEntity currentChangeHistoryParent;

	public void activateChangeHistory(AScrumGwtEntity entity) {
		if (currentChangeHistoryParent == entity) {
			log.debug("ChangeHistory already active for", entity);
			return;
		}
		currentChangeHistoryParent = entity;
		log.info("ChangeHistory activated for", entity);
		if (currentChangeHistoryParent != null) app.callRequestChanges(currentChangeHistoryParent.getId());
	}

	public boolean isChangeHistoryActive(AScrumGwtEntity entity) {
		return currentChangeHistoryParent == entity;
	}

	public List<Change> getChanges(AScrumGwtEntity entity) {
		return dao.getChangesByParent(entity);
	}

}
