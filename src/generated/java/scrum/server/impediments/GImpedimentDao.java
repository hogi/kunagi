// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.impediments;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

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
        impedimentsByProjectCache.clear();
        projectsCache = null;
        impedimentsByLabelCache.clear();
        labelsCache = null;
        impedimentsByDateCache.clear();
        datesCache = null;
        impedimentsByDescriptionCache.clear();
        descriptionsCache = null;
        impedimentsBySolutionCache.clear();
        solutionsCache = null;
        impedimentsBySolveDateCache.clear();
        solveDatesCache = null;
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
    // - project
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Project,Set<Impediment>> impedimentsByProjectCache = new Cache<scrum.server.project.Project,Set<Impediment>>(
            new Cache.Factory<scrum.server.project.Project,Set<Impediment>>() {
                public Set<Impediment> create(scrum.server.project.Project project) {
                    return getEntities(new IsProject(project));
                }
            });

    public final Set<Impediment> getImpedimentsByProject(scrum.server.project.Project project) {
        return impedimentsByProjectCache.get(project);
    }
    private Set<scrum.server.project.Project> projectsCache;

    public final Set<scrum.server.project.Project> getProjects() {
        if (projectsCache == null) {
            projectsCache = new HashSet<scrum.server.project.Project>();
            for (Impediment e : getEntities()) {
                if (e.isProjectSet()) projectsCache.add(e.getProject());
            }
        }
        return projectsCache;
    }

    private static class IsProject implements Predicate<Impediment> {

        private scrum.server.project.Project value;

        public IsProject(scrum.server.project.Project value) {
            this.value = value;
        }

        public boolean test(Impediment e) {
            return e.isProject(value);
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
    // - date
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.Date,Set<Impediment>> impedimentsByDateCache = new Cache<ilarkesto.base.time.Date,Set<Impediment>>(
            new Cache.Factory<ilarkesto.base.time.Date,Set<Impediment>>() {
                public Set<Impediment> create(ilarkesto.base.time.Date date) {
                    return getEntities(new IsDate(date));
                }
            });

    public final Set<Impediment> getImpedimentsByDate(ilarkesto.base.time.Date date) {
        return impedimentsByDateCache.get(date);
    }
    private Set<ilarkesto.base.time.Date> datesCache;

    public final Set<ilarkesto.base.time.Date> getDates() {
        if (datesCache == null) {
            datesCache = new HashSet<ilarkesto.base.time.Date>();
            for (Impediment e : getEntities()) {
                if (e.isDateSet()) datesCache.add(e.getDate());
            }
        }
        return datesCache;
    }

    private static class IsDate implements Predicate<Impediment> {

        private ilarkesto.base.time.Date value;

        public IsDate(ilarkesto.base.time.Date value) {
            this.value = value;
        }

        public boolean test(Impediment e) {
            return e.isDate(value);
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
    // - solveDate
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.Date,Set<Impediment>> impedimentsBySolveDateCache = new Cache<ilarkesto.base.time.Date,Set<Impediment>>(
            new Cache.Factory<ilarkesto.base.time.Date,Set<Impediment>>() {
                public Set<Impediment> create(ilarkesto.base.time.Date solveDate) {
                    return getEntities(new IsSolveDate(solveDate));
                }
            });

    public final Set<Impediment> getImpedimentsBySolveDate(ilarkesto.base.time.Date solveDate) {
        return impedimentsBySolveDateCache.get(solveDate);
    }
    private Set<ilarkesto.base.time.Date> solveDatesCache;

    public final Set<ilarkesto.base.time.Date> getSolveDates() {
        if (solveDatesCache == null) {
            solveDatesCache = new HashSet<ilarkesto.base.time.Date>();
            for (Impediment e : getEntities()) {
                if (e.isSolveDateSet()) solveDatesCache.add(e.getSolveDate());
            }
        }
        return solveDatesCache;
    }

    private static class IsSolveDate implements Predicate<Impediment> {

        private ilarkesto.base.time.Date value;

        public IsSolveDate(ilarkesto.base.time.Date value) {
            this.value = value;
        }

        public boolean test(Impediment e) {
            return e.isSolveDate(value);
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

    protected scrum.server.project.ProjectDao projectDao;

    public void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

}