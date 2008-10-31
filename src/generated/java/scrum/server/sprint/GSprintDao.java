









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.sprint;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.fp.*;
import ilarkesto.persistence.*;

public abstract class GSprintDao
            extends ilarkesto.persistence.ADao<Sprint> {

    public final String getEntityName() {
        return Sprint.TYPE;
    }

    public final Class getEntityClass() {
        return Sprint.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        sprintsByLabelCache.clear();
        labelsCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof Sprint) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof Sprint) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Sprint>> sprintsByLabelCache = new Cache<java.lang.String,Set<Sprint>>(
            new Cache.Factory<java.lang.String,Set<Sprint>>() {
                public Set<Sprint> create(java.lang.String label) {
                    return getEntities(new IsLabel(label));
                }
            });

    public final Set<Sprint> getSprintsByLabel(java.lang.String label) {
        return sprintsByLabelCache.get(label);
    }
    private Set<java.lang.String> labelsCache;

    public final Set<java.lang.String> getLabels() {
        if (labelsCache == null) {
            labelsCache = new HashSet<java.lang.String>();
            for (Sprint e : getEntities()) {
                if (e.isLabelSet()) labelsCache.add(e.getLabel());
            }
        }
        return labelsCache;
    }

    private static class IsLabel implements Predicate<Sprint> {

        private java.lang.String value;

        public IsLabel(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Sprint e) {
            return e.isLabel(value);
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
