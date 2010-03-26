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
        changesByOldValueCache.clear();
        oldValuesCache = null;
        changesByNewValueCache.clear();
        newValuesCache = null;
        changesByCommentCache.clear();
        commentsCache = null;
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
    // - oldValue
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Change>> changesByOldValueCache = new Cache<java.lang.String,Set<Change>>(
            new Cache.Factory<java.lang.String,Set<Change>>() {
                public Set<Change> create(java.lang.String oldValue) {
                    return getEntities(new IsOldValue(oldValue));
                }
            });

    public final Set<Change> getChangesByOldValue(java.lang.String oldValue) {
        return changesByOldValueCache.get(oldValue);
    }
    private Set<java.lang.String> oldValuesCache;

    public final Set<java.lang.String> getOldValues() {
        if (oldValuesCache == null) {
            oldValuesCache = new HashSet<java.lang.String>();
            for (Change e : getEntities()) {
                if (e.isOldValueSet()) oldValuesCache.add(e.getOldValue());
            }
        }
        return oldValuesCache;
    }

    private static class IsOldValue implements Predicate<Change> {

        private java.lang.String value;

        public IsOldValue(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Change e) {
            return e.isOldValue(value);
        }

    }

    // -----------------------------------------------------------
    // - newValue
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Change>> changesByNewValueCache = new Cache<java.lang.String,Set<Change>>(
            new Cache.Factory<java.lang.String,Set<Change>>() {
                public Set<Change> create(java.lang.String newValue) {
                    return getEntities(new IsNewValue(newValue));
                }
            });

    public final Set<Change> getChangesByNewValue(java.lang.String newValue) {
        return changesByNewValueCache.get(newValue);
    }
    private Set<java.lang.String> newValuesCache;

    public final Set<java.lang.String> getNewValues() {
        if (newValuesCache == null) {
            newValuesCache = new HashSet<java.lang.String>();
            for (Change e : getEntities()) {
                if (e.isNewValueSet()) newValuesCache.add(e.getNewValue());
            }
        }
        return newValuesCache;
    }

    private static class IsNewValue implements Predicate<Change> {

        private java.lang.String value;

        public IsNewValue(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Change e) {
            return e.isNewValue(value);
        }

    }

    // -----------------------------------------------------------
    // - comment
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Change>> changesByCommentCache = new Cache<java.lang.String,Set<Change>>(
            new Cache.Factory<java.lang.String,Set<Change>>() {
                public Set<Change> create(java.lang.String comment) {
                    return getEntities(new IsComment(comment));
                }
            });

    public final Set<Change> getChangesByComment(java.lang.String comment) {
        return changesByCommentCache.get(comment);
    }
    private Set<java.lang.String> commentsCache;

    public final Set<java.lang.String> getComments() {
        if (commentsCache == null) {
            commentsCache = new HashSet<java.lang.String>();
            for (Change e : getEntities()) {
                if (e.isCommentSet()) commentsCache.add(e.getComment());
            }
        }
        return commentsCache;
    }

    private static class IsComment implements Predicate<Change> {

        private java.lang.String value;

        public IsComment(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Change e) {
            return e.isComment(value);
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