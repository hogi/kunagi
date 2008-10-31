









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.impediments;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.fp.*;
import ilarkesto.persistence.*;

public abstract class GImpedimentDao
            extends ilarkesto.persistence.ADao<Impediment> {

    public final String getEntityName() {
        return Impediment.TYPE;
    }

    public final Class getEntityClass() {
        return Impediment.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        impedimentsBySolutionCache.clear();
        solutionsCache = null;
        impedimentsBySolvedCache.clear();
        impedimentsByLabelCache.clear();
        labelsCache = null;
        impedimentsByDescriptionCache.clear();
        descriptionsCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof Impediment) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof Impediment) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - solution
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Impediment>> impedimentsBySolutionCache = new Cache<java.lang.String,Set<Impediment>>(
            new Cache.Factory<java.lang.String,Set<Impediment>>() {
                public Set<Impediment> create(java.lang.String solution) {
                    return getEntities(new IsSolution(solution));
                }
            });

    public final Set<Impediment> getImpedimentsBySolution(java.lang.String solution) {
        return impedimentsBySolutionCache.get(solution);
    }
    private Set<java.lang.String> solutionsCache;

    public final Set<java.lang.String> getSolutions() {
        if (solutionsCache == null) {
            solutionsCache = new HashSet<java.lang.String>();
            for (Impediment e : getEntities()) {
                if (e.isSolutionSet()) solutionsCache.add(e.getSolution());
            }
        }
        return solutionsCache;
    }

    private static class IsSolution implements Predicate<Impediment> {

        private java.lang.String value;

        public IsSolution(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Impediment e) {
            return e.isSolution(value);
        }

    }

    // -----------------------------------------------------------
    // - solved
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<Impediment>> impedimentsBySolvedCache = new Cache<Boolean,Set<Impediment>>(
            new Cache.Factory<Boolean,Set<Impediment>>() {
                public Set<Impediment> create(Boolean solved) {
                    return getEntities(new IsSolved(solved));
                }
            });

    public final Set<Impediment> getImpedimentsBySolved(boolean solved) {
        return impedimentsBySolvedCache.get(solved);
    }

    private static class IsSolved implements Predicate<Impediment> {

        private boolean value;

        public IsSolved(boolean value) {
            this.value = value;
        }

        public boolean test(Impediment e) {
            return value == e.isSolved();
        }

    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Impediment>> impedimentsByLabelCache = new Cache<java.lang.String,Set<Impediment>>(
            new Cache.Factory<java.lang.String,Set<Impediment>>() {
                public Set<Impediment> create(java.lang.String label) {
                    return getEntities(new IsLabel(label));
                }
            });

    public final Set<Impediment> getImpedimentsByLabel(java.lang.String label) {
        return impedimentsByLabelCache.get(label);
    }
    private Set<java.lang.String> labelsCache;

    public final Set<java.lang.String> getLabels() {
        if (labelsCache == null) {
            labelsCache = new HashSet<java.lang.String>();
            for (Impediment e : getEntities()) {
                if (e.isLabelSet()) labelsCache.add(e.getLabel());
            }
        }
        return labelsCache;
    }

    private static class IsLabel implements Predicate<Impediment> {

        private java.lang.String value;

        public IsLabel(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Impediment e) {
            return e.isLabel(value);
        }

    }

    // -----------------------------------------------------------
    // - description
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Impediment>> impedimentsByDescriptionCache = new Cache<java.lang.String,Set<Impediment>>(
            new Cache.Factory<java.lang.String,Set<Impediment>>() {
                public Set<Impediment> create(java.lang.String description) {
                    return getEntities(new IsDescription(description));
                }
            });

    public final Set<Impediment> getImpedimentsByDescription(java.lang.String description) {
        return impedimentsByDescriptionCache.get(description);
    }
    private Set<java.lang.String> descriptionsCache;

    public final Set<java.lang.String> getDescriptions() {
        if (descriptionsCache == null) {
            descriptionsCache = new HashSet<java.lang.String>();
            for (Impediment e : getEntities()) {
                if (e.isDescriptionSet()) descriptionsCache.add(e.getDescription());
            }
        }
        return descriptionsCache;
    }

    private static class IsDescription implements Predicate<Impediment> {

        private java.lang.String value;

        public IsDescription(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Impediment e) {
            return e.isDescription(value);
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
