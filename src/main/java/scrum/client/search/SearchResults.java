package scrum.client.search;

import ilarkesto.core.logging.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import scrum.client.ComponentManager;
import scrum.client.common.AScrumGwtEntity;

public class SearchResults {

	private static final Log LOG = Log.get(SearchResults.class);
	private static ComponentManager cm = ComponentManager.get();

	private Collection<AScrumGwtEntity> entities = new HashSet<AScrumGwtEntity>();

	public boolean isEmpty() {
		return entities.isEmpty();
	}

	public void clear() {
		if (entities.isEmpty()) return;
		entities.clear();
		cm.getEventBus().fireSearchResultsChanged();
	}

	public void addEntities(List<? extends AScrumGwtEntity> entities) {
		boolean changed = this.entities.addAll(entities);
		if (changed) {
			LOG.info("SearchResults:", this.entities.size());
			cm.getEventBus().fireSearchResultsChanged();
		}
	}

	public Map<String, List<AScrumGwtEntity>> getEntitiesGrouped() {
		Map<String, List<AScrumGwtEntity>> ret = new LinkedHashMap<String, List<AScrumGwtEntity>>();
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
