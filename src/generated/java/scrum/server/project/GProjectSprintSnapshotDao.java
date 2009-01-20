









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
        projectSprintSnapshotsByProjectCache.clear();
        projectsCache = null;
        projectSprintSnapshotsByRemainingWorkCache.clear();
        remainingWorksCache = null;
        projectSprintSnapshotsByBurnedWorkCache.clear();
        burnedWorksCache = null;
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
    // - remainingWork
    // -----------------------------------------------------------

    private final Cache<Integer,Set<ProjectSprintSnapshot>> projectSprintSnapshotsByRemainingWorkCache = new Cache<Integer,Set<ProjectSprintSnapshot>>(
            new Cache.Factory<Integer,Set<ProjectSprintSnapshot>>() {
                public Set<ProjectSprintSnapshot> create(Integer remainingWork) {
                    return getEntities(new IsRemainingWork(remainingWork));
                }
            });

    public final Set<ProjectSprintSnapshot> getProjectSprintSnapshotsByRemainingWork(int remainingWork) {
        return projectSprintSnapshotsByRemainingWorkCache.get(remainingWork);
    }
    private Set<Integer> remainingWorksCache;

    public final Set<Integer> getRemainingWorks() {
        if (remainingWorksCache == null) {
            remainingWorksCache = new HashSet<Integer>();
            for (ProjectSprintSnapshot e : getEntities()) {
                remainingWorksCache.add(e.getRemainingWork());
            }
        }
        return remainingWorksCache;
    }

    private static class IsRemainingWork implements Predicate<ProjectSprintSnapshot> {

        private int value;

        public IsRemainingWork(int value) {
            this.value = value;
        }

        public boolean test(ProjectSprintSnapshot e) {
            return e.isRemainingWork(value);
        }

    }

    // -----------------------------------------------------------
    // - burnedWork
    // -----------------------------------------------------------

    private final Cache<Integer,Set<ProjectSprintSnapshot>> projectSprintSnapshotsByBurnedWorkCache = new Cache<Integer,Set<ProjectSprintSnapshot>>(
            new Cache.Factory<Integer,Set<ProjectSprintSnapshot>>() {
                public Set<ProjectSprintSnapshot> create(Integer burnedWork) {
                    return getEntities(new IsBurnedWork(burnedWork));
                }
            });

    public final Set<ProjectSprintSnapshot> getProjectSprintSnapshotsByBurnedWork(int burnedWork) {
        return projectSprintSnapshotsByBurnedWorkCache.get(burnedWork);
    }
    private Set<Integer> burnedWorksCache;

    public final Set<Integer> getBurnedWorks() {
        if (burnedWorksCache == null) {
            burnedWorksCache = new HashSet<Integer>();
            for (ProjectSprintSnapshot e : getEntities()) {
                burnedWorksCache.add(e.getBurnedWork());
            }
        }
        return burnedWorksCache;
    }

    private static class IsBurnedWork implements Predicate<ProjectSprintSnapshot> {

        private int value;

        public IsBurnedWork(int value) {
            this.value = value;
        }

        public boolean test(ProjectSprintSnapshot e) {
            return e.isBurnedWork(value);
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
