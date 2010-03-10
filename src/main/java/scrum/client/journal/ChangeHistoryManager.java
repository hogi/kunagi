package scrum.client.journal;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import scrum.client.common.AScrumGwtEntity;

public class ChangeHistoryManager extends GChangeHistoryManager {

	private Set<AScrumGwtEntity> entitiesWithActivatedChangeHistories = new HashSet<AScrumGwtEntity>();

	public void activateChangeHistory(AScrumGwtEntity entity) {
		if (entitiesWithActivatedChangeHistories.contains(entity)) {
			log.debug("ChangeHistory already active for", entity);
			return;
		}
		entitiesWithActivatedChangeHistories.add(entity);
		log.info("ChangeHistory activated for", entity);
		app.callRequestChanges(entity.getId());
	}

	public boolean isChangeHistoryActive(AScrumGwtEntity entity) {
		return entitiesWithActivatedChangeHistories.contains(entity);
	}

	public List<Change> getChanges(AScrumGwtEntity entity) {
		return dao.getChangesByParent(entity);
	}

}
