// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.admin;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

public abstract class GUserDao
            extends ilarkesto.auth.AUserDao<User> {

    public final String getEntityName() {
        return User.TYPE;
    }

    public final Class getEntityClass() {
        return User.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        usersByNameCache.clear();
        namesCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof User) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof User) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - name
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<User>> usersByNameCache = new Cache<java.lang.String,Set<User>>(
            new Cache.Factory<java.lang.String,Set<User>>() {
                public Set<User> create(java.lang.String name) {
                    return getEntities(new IsName(name));
                }
            });

    public final Set<User> getUsersByName(java.lang.String name) {
        return usersByNameCache.get(name);
    }
    private Set<java.lang.String> namesCache;

    public final Set<java.lang.String> getNames() {
        if (namesCache == null) {
            namesCache = new HashSet<java.lang.String>();
            for (User e : getEntities()) {
                if (e.isNameSet()) namesCache.add(e.getName());
            }
        }
        return namesCache;
    }

    private static class IsName implements Predicate<User> {

        private java.lang.String value;

        public IsName(java.lang.String value) {
            this.value = value;
        }

        public boolean test(User e) {
            return e.isName(value);
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