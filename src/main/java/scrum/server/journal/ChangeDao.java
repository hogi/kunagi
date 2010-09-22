package scrum.server.journal;

import ilarkesto.base.AFactoryCache;
import ilarkesto.base.Cache;
import ilarkesto.base.time.DateAndTime;
import ilarkesto.fp.Predicate;
import ilarkesto.persistence.AEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import scrum.server.admin.User;

public class ChangeDao extends GChangeDao {

	@Override
	public void clearCaches() {
		super.clearCaches();
		changesByParentIdCache.clear();
	}

	public Change postChange(AEntity parent, User user, String key, Object oldValue, Object newValue) {
		Change change = newEntityInstance();
		change.setDateAndTime(DateAndTime.now());
		change.setParent(parent);
		change.setUser(user);
		change.setKey(key);
		change.setOldValue(oldValue == null ? null : oldValue.toString());
		change.setNewValue(newValue == null ? null : newValue.toString());
		saveEntity(change);

		Change.merge(getChangesByParent(parent));

		return change;
	}

	private Cache<String, Set<Change>> changesByParentIdCache = new AFactoryCache<String, Set<Change>>() {

		@Override
		public Set<Change> create(final String parentId) {
			return getEntities(new Predicate<Change>() {

				@Override
				public boolean test(Change e) {
					return e.getParent().getId().equals(parentId);
				}
			});
		}

	};

	public Set<Change> getChangesByParentId(final String parentId) {
		return changesByParentIdCache.get(parentId);
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();

		Map<AEntity, List<Change>> changesByParent = new HashMap<AEntity, List<Change>>();
		for (Change change : getEntities()) {
			List<Change> list = changesByParent.get(change.getParent());
			if (list == null) {
				list = new ArrayList<Change>();
				changesByParent.put(change.getParent(), list);
			}
			list.add(change);
		}
		for (List<Change> changes : changesByParent.values()) {
			Change.merge(changes);
		}
	}
}