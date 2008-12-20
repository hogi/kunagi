









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.sprint;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.fp.*;
import ilarkesto.persistence.*;

public abstract class GSprintDaySnapshotDao
            extends ilarkesto.persistence.ADao<SprintDaySnapshot> {

    public final String getEntityName() {
        return SprintDaySnapshot.TYPE;
    }

    public final Class getEntityClass() {
        return SprintDaySnapshot.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        sprintDaySnapshotsByDateCrapCache.clear();
        dateCrapsCache = null;
        sprintDaySnapshotsBySprintCache.clear();
        sprintsCache = null;
        sprintDaySnapshotsByBurnedWorkCache.clear();
        burnedWorksCache = null;
        sprintDaySnapshotsByRemainingWorkCache.clear();
        remainingWorksCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof SprintDaySnapshot) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof SprintDaySnapshot) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - dateCrap
    // -----------------------------------------------------------

    private final Cache<java.util.Date,Set<SprintDaySnapshot>> sprintDaySnapshotsByDateCrapCache = new Cache<java.util.Date,Set<SprintDaySnapshot>>(
            new Cache.Factory<java.util.Date,Set<SprintDaySnapshot>>() {
                public Set<SprintDaySnapshot> create(java.util.Date dateCrap) {
                    return getEntities(new IsDateCrap(dateCrap));
                }
            });

    public final Set<SprintDaySnapshot> getSprintDaySnapshotsByDateCrap(java.util.Date dateCrap) {
        return sprintDaySnapshotsByDateCrapCache.get(dateCrap);
    }
    private Set<java.util.Date> dateCrapsCache;

    public final Set<java.util.Date> getDateCraps() {
        if (dateCrapsCache == null) {
            dateCrapsCache = new HashSet<java.util.Date>();
            for (SprintDaySnapshot e : getEntities()) {
                if (e.isDateCrapSet()) dateCrapsCache.add(e.getDateCrap());
            }
        }
        return dateCrapsCache;
    }

    private static class IsDateCrap implements Predicate<SprintDaySnapshot> {

        private java.util.Date value;

        public IsDateCrap(java.util.Date value) {
            this.value = value;
        }

        public boolean test(SprintDaySnapshot e) {
            return e.isDateCrap(value);
        }

    }

    // -----------------------------------------------------------
    // - sprint
    // -----------------------------------------------------------

    private final Cache<scrum.server.sprint.Sprint,Set<SprintDaySnapshot>> sprintDaySnapshotsBySprintCache = new Cache<scrum.server.sprint.Sprint,Set<SprintDaySnapshot>>(
            new Cache.Factory<scrum.server.sprint.Sprint,Set<SprintDaySnapshot>>() {
                public Set<SprintDaySnapshot> create(scrum.server.sprint.Sprint sprint) {
                    return getEntities(new IsSprint(sprint));
                }
            });

    public final Set<SprintDaySnapshot> getSprintDaySnapshotsBySprint(scrum.server.sprint.Sprint sprint) {
        return sprintDaySnapshotsBySprintCache.get(sprint);
    }
    private Set<scrum.server.sprint.Sprint> sprintsCache;

    public final Set<scrum.server.sprint.Sprint> getSprints() {
        if (sprintsCache == null) {
            sprintsCache = new HashSet<scrum.server.sprint.Sprint>();
            for (SprintDaySnapshot e : getEntities()) {
                if (e.isSprintSet()) sprintsCache.add(e.getSprint());
            }
        }
        return sprintsCache;
    }

    private static class IsSprint implements Predicate<SprintDaySnapshot> {

        private scrum.server.sprint.Sprint value;

        public IsSprint(scrum.server.sprint.Sprint value) {
            this.value = value;
        }

        public boolean test(SprintDaySnapshot e) {
            return e.isSprint(value);
        }

    }

    // -----------------------------------------------------------
    // - burnedWork
    // -----------------------------------------------------------

    private final Cache<Integer,Set<SprintDaySnapshot>> sprintDaySnapshotsByBurnedWorkCache = new Cache<Integer,Set<SprintDaySnapshot>>(
            new Cache.Factory<Integer,Set<SprintDaySnapshot>>() {
                public Set<SprintDaySnapshot> create(Integer burnedWork) {
                    return getEntities(new IsBurnedWork(burnedWork));
                }
            });

    public final Set<SprintDaySnapshot> getSprintDaySnapshotsByBurnedWork(int burnedWork) {
        return sprintDaySnapshotsByBurnedWorkCache.get(burnedWork);
    }
    private Set<Integer> burnedWorksCache;

    public final Set<Integer> getBurnedWorks() {
        if (burnedWorksCache == null) {
            burnedWorksCache = new HashSet<Integer>();
            for (SprintDaySnapshot e : getEntities()) {
                burnedWorksCache.add(e.getBurnedWork());
            }
        }
        return burnedWorksCache;
    }

    private static class IsBurnedWork implements Predicate<SprintDaySnapshot> {

        private int value;

        public IsBurnedWork(int value) {
            this.value = value;
        }

        public boolean test(SprintDaySnapshot e) {
            return e.isBurnedWork(value);
        }

    }

    // -----------------------------------------------------------
    // - remainingWork
    // -----------------------------------------------------------

    private final Cache<Integer,Set<SprintDaySnapshot>> sprintDaySnapshotsByRemainingWorkCache = new Cache<Integer,Set<SprintDaySnapshot>>(
            new Cache.Factory<Integer,Set<SprintDaySnapshot>>() {
                public Set<SprintDaySnapshot> create(Integer remainingWork) {
                    return getEntities(new IsRemainingWork(remainingWork));
                }
            });

    public final Set<SprintDaySnapshot> getSprintDaySnapshotsByRemainingWork(int remainingWork) {
        return sprintDaySnapshotsByRemainingWorkCache.get(remainingWork);
    }
    private Set<Integer> remainingWorksCache;

    public final Set<Integer> getRemainingWorks() {
        if (remainingWorksCache == null) {
            remainingWorksCache = new HashSet<Integer>();
            for (SprintDaySnapshot e : getEntities()) {
                remainingWorksCache.add(e.getRemainingWork());
            }
        }
        return remainingWorksCache;
    }

    private static class IsRemainingWork implements Predicate<SprintDaySnapshot> {

        private int value;

        public IsRemainingWork(int value) {
            this.value = value;
        }

        public boolean test(SprintDaySnapshot e) {
            return e.isRemainingWork(value);
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
