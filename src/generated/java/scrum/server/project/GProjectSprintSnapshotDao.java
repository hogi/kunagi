// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.project;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

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
        sprintsCache = null;
        projectSprintSnapshotsByRemainingWorkCache.clear();
        remainingWorksCache = null;
        projectSprintSnapshotsByBurnedWorkCache.clear();
        burnedWorksCache = null;
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
    // - sprint
    // -----------------------------------------------------------

    public final ProjectSprintSnapshot getProjectSprintSnapshotBySprint(scrum.server.sprint.Sprint sprint) {
        return getEntity(new IsSprint(sprint));
    }
    private Set<scrum.server.sprint.Sprint> sprintsCache;

    public final Set<scrum.server.sprint.Sprint> getSprints() {
        if (sprintsCache == null) {
            sprintsCache = new HashSet<scrum.server.sprint.Sprint>();
            for (ProjectSprintSnapshot e : getEntities()) {
                if (e.isSprintSet()) sprintsCache.add(e.getSprint());
            }
        }
        return sprintsCache;
    }

    private static class IsSprint implements Predicate<ProjectSprintSnapshot> {

        private scrum.server.sprint.Sprint value;

        public IsSprint(scrum.server.sprint.Sprint value) {
            this.value = value;
        }

        public boolean test(ProjectSprintSnapshot e) {
            return e.isSprint(value);
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

    protected scrum.server.sprint.SprintDao sprintDao;

    public void setSprintDao(scrum.server.sprint.SprintDao sprintDao) {
        this.sprintDao = sprintDao;
    }

}