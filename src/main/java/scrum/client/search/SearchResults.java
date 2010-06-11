package scrum.client.search;

import ilarkesto.core.logging.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import scrum.client.common.AScrumGwtEntity;

public class SearchResults {

	private static final Log LOG = Log.get(SearchResults.class);

	private Collection<AScrumGwtEntity> entities = new HashSet<AScrumGwtEntity>();

	public boolean isEmpty() {
		return entities.isEmpty();
	}

	public void clear() {
		if (entities.isEmpty()) return;
		entities.clear();
		new SearchResultsChangedEvent().fireInCurrentScope();
	}

	public void addEntities(List<? extends AScrumGwtEntity> entities) {
		boolean changed = this.entities.addAll(entities);
		if (changed) {
			LOG.info("SearchResults:", this.entities.size());
			new SearchResultsChangedEvent().fireInCurrentScope();
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
