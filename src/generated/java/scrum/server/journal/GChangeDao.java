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

    // --- clear caches ---
    public void clearCaches() {
        changesByParentCache.clear();
        parentsCache = null;
        changesByUserCache.clear();
        usersCache = null;
        changesByDateAndTimeCache.clear();
        dateAndTimesCache = null;
        changesByPropertyCache.clear();
        propertysCache = null;
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
    // - property
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Change>> changesByPropertyCache = new Cache<java.lang.String,Set<Change>>(
            new Cache.Factory<java.lang.String,Set<Change>>() {
                public Set<Change> create(java.lang.String property) {
                    return getEntities(new IsProperty(property));
                }
            });

    public final Set<Change> getChangesByProperty(java.lang.String property) {
        return changesByPropertyCache.get(property);
    }
    private Set<java.lang.String> propertysCache;

    public final Set<java.lang.String> getPropertys() {
        if (propertysCache == null) {
            propertysCache = new HashSet<java.lang.String>();
            for (Change e : getEntities()) {
                if (e.isPropertySet()) propertysCache.add(e.getProperty());
            }
        }
        return propertysCache;
    }

    private static class IsProperty implements Predicate<Change> {

        private java.lang.String value;

        public IsProperty(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Change e) {
            return e.isProperty(value);
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