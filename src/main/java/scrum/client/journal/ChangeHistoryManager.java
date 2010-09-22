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
		deactivateChangeHistory();
		currentChangeHistoryParent = entity;
		log.info("ChangeHistory activated for", entity);
		if (currentChangeHistoryParent != null)
			new RequestChangesServiceCall(currentChangeHistoryParent.getId()).execute();
	}

	public void deactivateChangeHistory() {
		currentChangeHistoryParent = null;
		dao.clearChanges();
		log.info("ChangeHistory deactivated.");
	}

	public boolean isChangeHistoryActive(AScrumGwtEntity entity) {
		return currentChangeHistoryParent == entity;
	}

	public List<Change> getChanges(AScrumGwtEntity entity) {
		return dao.getChangesByParent(entity);
	}

}
