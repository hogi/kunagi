// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.sprint;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

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
        sprintDaySnapshotsBySprintCache.clear();
        sprintsCache = null;
        sprintDaySnapshotsByDateCache.clear();
        datesCache = null;
        sprintDaySnapshotsByRemainingWorkCache.clear();
        remainingWorksCache = null;
        sprintDaySnapshotsByBurnedWorkCache.clear();
        burnedWorksCache = null;
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
    // - date
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.Date,Set<SprintDaySnapshot>> sprintDaySnapshotsByDateCache = new Cache<ilarkesto.base.time.Date,Set<SprintDaySnapshot>>(
            new Cache.Factory<ilarkesto.base.time.Date,Set<SprintDaySnapshot>>() {
                public Set<SprintDaySnapshot> create(ilarkesto.base.time.Date date) {
                    return getEntities(new IsDate(date));
                }
            });

    public final Set<SprintDaySnapshot> getSprintDaySnapshotsByDate(ilarkesto.base.time.Date date) {
        return sprintDaySnapshotsByDateCache.get(date);
    }
    private Set<ilarkesto.base.time.Date> datesCache;

    public final Set<ilarkesto.base.time.Date> getDates() {
        if (datesCache == null) {
            datesCache = new HashSet<ilarkesto.base.time.Date>();
            for (SprintDaySnapshot e : getEntities()) {
                if (e.isDateSet()) datesCache.add(e.getDate());
            }
        }
        return datesCache;
    }

    private static class IsDate implements Predicate<SprintDaySnapshot> {

        private ilarkesto.base.time.Date value;

        public IsDate(ilarkesto.base.time.Date value) {
            this.value = value;
        }

        public boolean test(SprintDaySnapshot e) {
            return e.isDate(value);
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

    scrum.server.sprint.SprintDao sprintDao;

    public void setSprintDao(scrum.server.sprint.SprintDao sprintDao) {
        this.sprintDao = sprintDao;
    }

}