// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.DaoGenerator










package scrum.server.journal;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

public abstract class GChangeDao
            extends ilarkesto.persistence.ADao<Change> {

    public final String getEntityName() {
        return Change.TYPE;
    }

    public final Class getEntityClass() {
        return Change.class;
    }

    public Set<Change> getEntitiesVisibleForUser(final scrum.server.admin.User user) {
        return getEntities(new Predicate<Change>() {
            public boolean test(Change e) {
                return Auth.isVisible(e, user);
            }
        });
    }

    // --- clear caches ---
    public void clearCaches() {
        changesByParentCache.clear();
        parentsCache = null;
        changesByUserCache.clear();
        usersCache = null;
        changesByDateAndTimeCache.clear();
        dateAndTimesCache = null;
        changesByKeyCache.clear();
        keysCache = null;
        changesByValueCache.clear();
        valuesCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof Change) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof Change) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - parent
    // -----------------------------------------------------------

    private final Cache<ilarkesto.persistence.AEntity,Set<Change>> changesByParentCache = new Cache<ilarkesto.persistence.AEntity,Set<Change>>(
            new Cache.Factory<ilarkesto.persistence.AEntity,Set<Change>>() {
                public Set<Change> create(ilarkesto.persistence.AEntity parent) {
                    return getEntities(new IsParent(parent));
                }
            });

    public final Set<Change> getChangesByParent(ilarkesto.persistence.AEntity parent) {
        return changesByParentCache.get(parent);
    }
    private Set<ilarkesto.persistence.AEntity> parentsCache;

    public final Set<ilarkesto.persistence.AEntity> getParents() {
        if (parentsCache == null) {
            parentsCache = new HashSet<ilarkesto.persistence.AEntity>();
            for (Change e : getEntities()) {
                if (e.isParentSet()) parentsCache.add(e.getParent());
            }
        }
        return parentsCache;
    }

    private static class IsParent implements Predicate<Change> {

        private ilarkesto.persistence.AEntity value;

        public IsParent(ilarkesto.persistence.AEntity value) {
            this.value = value;
        }

        public boolean test(Change e) {
            return e.isParent(value);
        }

    }

    // -----------------------------------------------------------
    // - user
    // -----------------------------------------------------------

    private final Cache<scrum.server.admin.User,Set<Change>> changesByUserCache = new Cache<scrum.server.admin.User,Set<Change>>(
            new Cache.Factory<scrum.server.admin.User,Set<Change>>() {
                public Set<Change> create(scrum.server.admin.User user) {
                    return getEntities(new IsUser(user));
                }
            });

    public final Set<Change> getChangesByUser(scrum.server.admin.User user) {
        return changesByUserCache.get(user);
    }
    private Set<scrum.server.admin.User> usersCache;

    public final Set<scrum.server.admin.User> getUsers() {
        if (usersCache == null) {
            usersCache = new HashSet<scrum.server.admin.User>();
            for (Change e : getEntities()) {
                if (e.isUserSet()) usersCache.add(e.getUser());
            }
        }
        return usersCache;
    }

    private static class IsUser implements Predicate<Change> {

        private scrum.server.admin.User value;

        public IsUser(scrum.server.admin.User value) {
            this.value = value;
        }

        public boolean test(Change e) {
            return e.isUser(value);
        }

    }

    // -----------------------------------------------------------
    // - dateAndTime
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.DateAndTime,Set<Change>> changesByDateAndTimeCache = new Cache<ilarkesto.base.time.DateAndTime,Set<Change>>(
            new Cache.Factory<ilarkesto.base.time.DateAndTime,Set<Change>>() {
                public Set<Change> create(ilarkesto.base.time.DateAndTime dateAndTime) {
                    return getEntities(new IsDateAndTime(dateAndTime));
                }
            });

    public final Set<Change> getChangesByDateAndTime(ilarkesto.base.time.DateAndTime dateAndTime) {
        return changesByDateAndTimeCache.get(dateAndTime);
    }
    private Set<ilarkesto.base.time.DateAndTime> dateAndTimesCache;

    public final Set<ilarkesto.base.time.DateAndTime> getDateAndTimes() {
        if (dateAndTimesCache == null) {
            dateAndTimesCache = new HashSet<ilarkesto.base.time.DateAndTime>();
            for (Change e : getEntities()) {
                if (e.isDateAndTimeSet()) dateAndTimesCache.add(e.getDateAndTime());
            }
        }
        return dateAndTimesCache;
    }

    private static class IsDateAndTime implements Predicate<Change> {

        private ilarkesto.base.time.DateAndTime value;

        public IsDateAndTime(ilarkesto.base.time.DateAndTime value) {
            this.value = value;
        }

        public boolean test(Change e) {
            return e.isDateAndTime(value);
        }

    }

    // -----------------------------------------------------------
    // - key
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Change>> changesByKeyCache = new Cache<java.lang.String,Set<Change>>(
            new Cache.Factory<java.lang.String,Set<Change>>() {
                public Set<Change> create(java.lang.String key) {
                    return getEntities(new IsKey(key));
                }
            });

    public final Set<Change> getChangesByKey(java.lang.String key) {
        return changesByKeyCache.get(key);
    }
    private Set<java.lang.String> keysCache;

    public final Set<java.lang.String> getKeys() {
        if (keysCache == null) {
            keysCache = new HashSet<java.lang.String>();
            for (Change e : getEntities()) {
                if (e.isKeySet()) keysCache.add(e.getKey());
            }
        }
        return keysCache;
    }

    private static class IsKey implements Predicate<Change> {

        private java.lang.String value;

        public IsKey(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Change e) {
            return e.isKey(value);
        }

    }

    // -----------------------------------------------------------
    // - value
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Change>> changesByValueCache = new Cache<java.lang.String,Set<Change>>(
            new Cache.Factory<java.lang.String,Set<Change>>() {
                public Set<Change> create(java.lang.String value) {
                    return getEntities(new IsValue(value));
                }
            });

    public final Set<Change> getChangesByValue(java.lang.String value) {
        return changesByValueCache.get(value);
    }
    private Set<java.lang.String> valuesCache;

    public final Set<java.lang.String> getValues() {
        if (valuesCache == null) {
            valuesCache = new HashSet<java.lang.String>();
            for (Change e : getEntities()) {
                if (e.isValueSet()) valuesCache.add(e.getValue());
            }
        }
        return valuesCache;
    }

    private static class IsValue implements Predicate<Change> {

        private java.lang.String value;

        public IsValue(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Change e) {
            return e.isValue(value);
        }

    }

    // --- valueObject classes ---
    @Override
    protected Set<Class> getValueObjectClasses() {
        Set<Class> ret = new HashSet<Class>(super.getValueObjectClasses());
        return ret;
    }

    @Override
    public Map<String, Class> getAliases() {
        Map<String, Class> aliases = new HashMap<String, Class>(super.getAliases());
        return aliases;
    }

    // --- dependencies ---

}