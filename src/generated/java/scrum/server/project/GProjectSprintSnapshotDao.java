









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.project;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.fp.*;
import ilarkesto.persistence.*;

public abstract class GProjectSprintSnapshotDao
            extends ilarkesto.persistence.ADao<ProjectSprintSnapshot> {

    public final String getEntityName() {
        return ProjectSprintSnapshot.TYPE;
    }

    public final Class getEntityClass() {
        return ProjectSprintSnapshot.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        projectSprintSnapshotsByBurndownCache.clear();
        burndownsCache = null;
        projectSprintSnapshotsByEffortCache.clear();
        effortsCache = null;
        projectSprintSnapshotsByProjectCache.clear();
        projectsCache = null;
        projectSprintSnapshotsByDateCrapCache.clear();
        dateCrapsCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof ProjectSprintSnapshot) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof ProjectSprintSnapshot) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - burndown
    // -----------------------------------------------------------

    private final Cache<Integer,Set<ProjectSprintSnapshot>> projectSprintSnapshotsByBurndownCache = new Cache<Integer,Set<ProjectSprintSnapshot>>(
            new Cache.Factory<Integer,Set<ProjectSprintSnapshot>>() {
                public Set<ProjectSprintSnapshot> create(Integer burndown) {
                    return getEntities(new IsBurndown(burndown));
                }
            });

    public final Set<ProjectSprintSnapshot> getProjectSprintSnapshotsByBurndown(int burndown) {
        return projectSprintSnapshotsByBurndownCache.get(burndown);
    }
    private Set<Integer> burndownsCache;

    public final Set<Integer> getBurndowns() {
        if (burndownsCache == null) {
            burndownsCache = new HashSet<Integer>();
            for (ProjectSprintSnapshot e : getEntities()) {
                burndownsCache.add(e.getBurndown());
            }
        }
        return burndownsCache;
    }

    private static class IsBurndown implements Predicate<ProjectSprintSnapshot> {

        private int value;

        public IsBurndown(int value) {
            this.value = value;
        }

        public boolean test(ProjectSprintSnapshot e) {
            return e.isBurndown(value);
        }

    }

    // -----------------------------------------------------------
    // - effort
    // -----------------------------------------------------------

    private final Cache<Integer,Set<ProjectSprintSnapshot>> projectSprintSnapshotsByEffortCache = new Cache<Integer,Set<ProjectSprintSnapshot>>(
            new Cache.Factory<Integer,Set<ProjectSprintSnapshot>>() {
                public Set<ProjectSprintSnapshot> create(Integer effort) {
                    return getEntities(new IsEffort(effort));
                }
            });

    public final Set<ProjectSprintSnapshot> getProjectSprintSnapshotsByEffort(int effort) {
        return projectSprintSnapshotsByEffortCache.get(effort);
    }
    private Set<Integer> effortsCache;

    public final Set<Integer> getEfforts() {
        if (effortsCache == null) {
            effortsCache = new HashSet<Integer>();
            for (ProjectSprintSnapshot e : getEntities()) {
                effortsCache.add(e.getEffort());
            }
        }
        return effortsCache;
    }

    private static class IsEffort implements Predicate<ProjectSprintSnapshot> {

        private int value;

        public IsEffort(int value) {
            this.value = value;
        }

        public boolean test(ProjectSprintSnapshot e) {
            return e.isEffort(value);
        }

    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Project,Set<ProjectSprintSnapshot>> projectSprintSnapshotsByProjectCache = new Cache<scrum.server.project.Project,Set<ProjectSprintSnapshot>>(
            new Cache.Factory<scrum.server.project.Project,Set<ProjectSprintSnapshot>>() {
                public Set<ProjectSprintSnapshot> create(scrum.server.project.Project project) {
                    return getEntities(new IsProject(project));
                }
            });

    public final Set<ProjectSprintSnapshot> getProjectSprintSnapshotsByProject(scrum.server.project.Project project) {
        return projectSprintSnapshotsByProjectCache.get(project);
    }
    private Set<scrum.server.project.Project> projectsCache;

    public final Set<scrum.server.project.Project> getProjects() {
        if (projectsCache == null) {
            projectsCache = new HashSet<scrum.server.project.Project>();
            for (ProjectSprintSnapshot e : getEntities()) {
                if (e.isProjectSet()) projectsCache.add(e.getProject());
            }
        }
        return projectsCache;
    }

    private static class IsProject implements Predicate<ProjectSprintSnapshot> {

        private scrum.server.project.Project value;

        public IsProject(scrum.server.project.Project value) {
            this.value = value;
        }

        public boolean test(ProjectSprintSnapshot e) {
            return e.isProject(value);
        }

    }

    // -----------------------------------------------------------
    // - dateCrap
    // -----------------------------------------------------------

    private final Cache<java.util.Date,Set<ProjectSprintSnapshot>> projectSprintSnapshotsByDateCrapCache = new Cache<java.util.Date,Set<ProjectSprintSnapshot>>(
            new Cache.Factory<java.util.Date,Set<ProjectSprintSnapshot>>() {
                public Set<ProjectSprintSnapshot> create(java.util.Date dateCrap) {
                    return getEntities(new IsDateCrap(dateCrap));
                }
            });

    public final Set<ProjectSprintSnapshot> getProjectSprintSnapshotsByDateCrap(java.util.Date dateCrap) {
        return projectSprintSnapshotsByDateCrapCache.get(dateCrap);
    }
    private Set<java.util.Date> dateCrapsCache;

    public final Set<java.util.Date> getDateCraps() {
        if (dateCrapsCache == null) {
            dateCrapsCache = new HashSet<java.util.Date>();
            for (ProjectSprintSnapshot e : getEntities()) {
                if (e.isDateCrapSet()) dateCrapsCache.add(e.getDateCrap());
            }
        }
        return dateCrapsCache;
    }

    private static class IsDateCrap implements Predicate<ProjectSprintSnapshot> {

        private java.util.Date value;

        public IsDateCrap(java.util.Date value) {
            this.value = value;
        }

        public boolean test(ProjectSprintSnapshot e) {
            return e.isDateCrap(value);
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
