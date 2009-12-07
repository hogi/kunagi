package scrum.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import scrum.client.common.AScrumGwtEntity;

public class SearchResults {

	private static ComponentManager cm = ComponentManager.get();

	private List<AScrumGwtEntity> entities = new ArrayList<AScrumGwtEntity>();

	public boolean isEmpty() {
		return entities.isEmpty();
	}

	public void clear() {
		if (entities.isEmpty()) return;
		entities.clear();
		cm.getEventBus().fireSearchResultsChanged();
	}

	public void addEntities(List<AScrumGwtEntity> entities) {
		boolean changed = this.entities.addAll(entities);
		if (changed) cm.getEventBus().fireSearchResultsChanged();
	}

	public Map<String, List<AScrumGwtEntity>> getEntitiesGrouped() {
		Map<String, List<AScrumGwtEntity>> ret = new HashMap<String, List<AScrumGwtEntity>>();
		for (AScrumGwtEntity entity : entities) {
			String key = entity.getClass().getName();
			List<AScrumGwtEntity> list = ret.get(key);
			if (list == null) {
				list = new ArrayList<AScrumGwtEntity>();
				ret.put(key, list);
			}
			list.add(entity);
		}
		return ret;
	}

}
