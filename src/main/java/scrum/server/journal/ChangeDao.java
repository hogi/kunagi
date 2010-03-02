package scrum.server.journal;

import ilarkesto.base.AFactoryCache;
import ilarkesto.base.Cache;
import ilarkesto.fp.Predicate;

import java.util.Set;

public class ChangeDao extends GChangeDao {

	@Override
	public void clearCaches() {
		super.clearCaches();
		changesByParentIdCache.clear();
	}

	private Cache<String, Set<Change>> changesByParentIdCache = new AFactoryCache<String, Set<Change>>() {

		public Set<Change> create(final String parentId) {
			return getEntities(new Predicate<Change>() {

				public boolean test(Change e) {
					return e.getParent().getId().equals(parentId);
				}
			});
		}

	};

	public Set<Change> getChangesByParentId(final String parentId) {
		return changesByParentIdCache.get(parentId);
	}
}