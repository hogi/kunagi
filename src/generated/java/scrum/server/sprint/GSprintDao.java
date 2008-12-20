









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
        sprintsByProjectCache.clear();
        projectsCache = null;
        sprintsByEndCrapCache.clear();
        endCrapsCache = null;
        sprintsByBeginCrapCache.clear();
        beginCrapsCache = null;
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

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Project,Set<Sprint>> sprintsByProjectCache = new Cache<scrum.server.project.Project,Set<Sprint>>(
            new Cache.Factory<scrum.server.project.Project,Set<Sprint>>() {
                public Set<Sprint> create(scrum.server.project.Project project) {
                    return getEntities(new IsProject(project));
                }
            });

    public final Set<Sprint> getSprintsByProject(scrum.server.project.Project project) {
        return sprintsByProjectCache.get(project);
    }
    private Set<scrum.server.project.Project> projectsCache;

    public final Set<scrum.server.project.Project> getProjects() {
        if (projectsCache == null) {
            projectsCache = new HashSet<scrum.server.project.Project>();
            for (Sprint e : getEntities()) {
                if (e.isProjectSet()) projectsCache.add(e.getProject());
            }
        }
        return projectsCache;
    }

    private static class IsProject implements Predicate<Sprint> {

        private scrum.server.project.Project value;

        public IsProject(scrum.server.project.Project value) {
            this.value = value;
        }

        public boolean test(Sprint e) {
            return e.isProject(value);
        }

    }

    // -----------------------------------------------------------
    // - endCrap
    // -----------------------------------------------------------

    private final Cache<java.util.Date,Set<Sprint>> sprintsByEndCrapCache = new Cache<java.util.Date,Set<Sprint>>(
            new Cache.Factory<java.util.Date,Set<Sprint>>() {
                public Set<Sprint> create(java.util.Date endCrap) {
                    return getEntities(new IsEndCrap(endCrap));
                }
            });

    public final Set<Sprint> getSprintsByEndCrap(java.util.Date endCrap) {
        return sprintsByEndCrapCache.get(endCrap);
    }
    private Set<java.util.Date> endCrapsCache;

    public final Set<java.util.Date> getEndCraps() {
        if (endCrapsCache == null) {
            endCrapsCache = new HashSet<java.util.Date>();
            for (Sprint e : getEntities()) {
                if (e.isEndCrapSet()) endCrapsCache.add(e.getEndCrap());
            }
        }
        return endCrapsCache;
    }

    private static class IsEndCrap implements Predicate<Sprint> {

        private java.util.Date value;

        public IsEndCrap(java.util.Date value) {
            this.value = value;
        }

        public boolean test(Sprint e) {
            return e.isEndCrap(value);
        }

    }

    // -----------------------------------------------------------
    // - beginCrap
    // -----------------------------------------------------------

    private final Cache<java.util.Date,Set<Sprint>> sprintsByBeginCrapCache = new Cache<java.util.Date,Set<Sprint>>(
            new Cache.Factory<java.util.Date,Set<Sprint>>() {
                public Set<Sprint> create(java.util.Date beginCrap) {
                    return getEntities(new IsBeginCrap(beginCrap));
                }
            });

    public final Set<Sprint> getSprintsByBeginCrap(java.util.Date beginCrap) {
        return sprintsByBeginCrapCache.get(beginCrap);
    }
    private Set<java.util.Date> beginCrapsCache;

    public final Set<java.util.Date> getBeginCraps() {
        if (beginCrapsCache == null) {
            beginCrapsCache = new HashSet<java.util.Date>();
            for (Sprint e : getEntities()) {
                if (e.isBeginCrapSet()) beginCrapsCache.add(e.getBeginCrap());
            }
        }
        return beginCrapsCache;
    }

    private static class IsBeginCrap implements Predicate<Sprint> {

        private java.util.Date value;

        public IsBeginCrap(java.util.Date value) {
            this.value = value;
        }

        public boolean test(Sprint e) {
            return e.isBeginCrap(value);
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
