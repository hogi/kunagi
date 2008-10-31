









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.sprint;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.fp.*;
import ilarkesto.persistence.*;

public abstract class GTaskDao
            extends ilarkesto.persistence.ADao<Task> {

    public final String getEntityName() {
        return Task.TYPE;
    }

    public final Class getEntityClass() {
        return Task.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        tasksByLabelCache.clear();
        labelsCache = null;
        tasksByEffortCache.clear();
        effortsCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof Task) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof Task) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Task>> tasksByLabelCache = new Cache<java.lang.String,Set<Task>>(
            new Cache.Factory<java.lang.String,Set<Task>>() {
                public Set<Task> create(java.lang.String label) {
                    return getEntities(new IsLabel(label));
                }
            });

    public final Set<Task> getTasksByLabel(java.lang.String label) {
        return tasksByLabelCache.get(label);
    }
    private Set<java.lang.String> labelsCache;

    public final Set<java.lang.String> getLabels() {
        if (labelsCache == null) {
            labelsCache = new HashSet<java.lang.String>();
            for (Task e : getEntities()) {
                if (e.isLabelSet()) labelsCache.add(e.getLabel());
            }
        }
        return labelsCache;
    }

    private static class IsLabel implements Predicate<Task> {

        private java.lang.String value;

        public IsLabel(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Task e) {
            return e.isLabel(value);
        }

    }

    // -----------------------------------------------------------
    // - effort
    // -----------------------------------------------------------

    private final Cache<java.lang.Integer,Set<Task>> tasksByEffortCache = new Cache<java.lang.Integer,Set<Task>>(
            new Cache.Factory<java.lang.Integer,Set<Task>>() {
                public Set<Task> create(java.lang.Integer effort) {
                    return getEntities(new IsEffort(effort));
                }
            });

    public final Set<Task> getTasksByEffort(java.lang.Integer effort) {
        return tasksByEffortCache.get(effort);
    }
    private Set<java.lang.Integer> effortsCache;

    public final Set<java.lang.Integer> getEfforts() {
        if (effortsCache == null) {
            effortsCache = new HashSet<java.lang.Integer>();
            for (Task e : getEntities()) {
                if (e.isEffortSet()) effortsCache.add(e.getEffort());
            }
        }
        return effortsCache;
    }

    private static class IsEffort implements Predicate<Task> {

        private java.lang.Integer value;

        public IsEffort(java.lang.Integer value) {
            this.value = value;
        }

        public boolean test(Task e) {
            return e.isEffort(value);
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
