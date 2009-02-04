









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
        projectSprintSnapshotsByRemainingWorkCache.clear();
        remainingWorksCache = null;
        projectSprintSnapshotsByBurnedWorkCache.clear();
        burnedWorksCache = null;
        projectSprintSnapshotsByDateCache.clear();
        datesCache = null;
        projectSprintSnapshotsByProjectCache.clear();
        projectsCache = null;
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
    // - date
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.Date,Set<ProjectSprintSnapshot>> projectSprintSnapshotsByDateCache = new Cache<ilarkesto.base.time.Date,Set<ProjectSprintSnapshot>>(
            new Cache.Factory<ilarkesto.base.time.Date,Set<ProjectSprintSnapshot>>() {
                public Set<ProjectSprintSnapshot> create(ilarkesto.base.time.Date date) {
                    return getEntities(new IsDate(date));
                }
            });

    public final Set<ProjectSprintSnapshot> getProjectSprintSnapshotsByDate(ilarkesto.base.time.Date date) {
        return projectSprintSnapshotsByDateCache.get(date);
    }
    private Set<ilarkesto.base.time.Date> datesCache;

    public final Set<ilarkesto.base.time.Date> getDates() {
        if (datesCache == null) {
            datesCache = new HashSet<ilarkesto.base.time.Date>();
            for (ProjectSprintSnapshot e : getEntities()) {
                if (e.isDateSet()) datesCache.add(e.getDate());
            }
        }
        return datesCache;
    }

    private static class IsDate implements Predicate<ProjectSprintSnapshot> {

        private ilarkesto.base.time.Date value;

        public IsDate(ilarkesto.base.time.Date value) {
            this.value = value;
        }

        public boolean test(ProjectSprintSnapshot e) {
            return e.isDate(value);
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
