









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
        sprintDaySnapshotsBySprintCache.clear();
        sprintsCache = null;
        sprintDaySnapshotsByBurndownCache.clear();
        burndownsCache = null;
        sprintDaySnapshotsByDateCrapCache.clear();
        dateCrapsCache = null;
        sprintDaySnapshotsByEffortCache.clear();
        effortsCache = null;
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
    // - burndown
    // -----------------------------------------------------------

    private final Cache<Integer,Set<SprintDaySnapshot>> sprintDaySnapshotsByBurndownCache = new Cache<Integer,Set<SprintDaySnapshot>>(
            new Cache.Factory<Integer,Set<SprintDaySnapshot>>() {
                public Set<SprintDaySnapshot> create(Integer burndown) {
                    return getEntities(new IsBurndown(burndown));
                }
            });

    public final Set<SprintDaySnapshot> getSprintDaySnapshotsByBurndown(int burndown) {
        return sprintDaySnapshotsByBurndownCache.get(burndown);
    }
    private Set<Integer> burndownsCache;

    public final Set<Integer> getBurndowns() {
        if (burndownsCache == null) {
            burndownsCache = new HashSet<Integer>();
            for (SprintDaySnapshot e : getEntities()) {
                burndownsCache.add(e.getBurndown());
            }
        }
        return burndownsCache;
    }

    private static class IsBurndown implements Predicate<SprintDaySnapshot> {

        private int value;

        public IsBurndown(int value) {
            this.value = value;
        }

        public boolean test(SprintDaySnapshot e) {
            return e.isBurndown(value);
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
    // - effort
    // -----------------------------------------------------------

    private final Cache<Integer,Set<SprintDaySnapshot>> sprintDaySnapshotsByEffortCache = new Cache<Integer,Set<SprintDaySnapshot>>(
            new Cache.Factory<Integer,Set<SprintDaySnapshot>>() {
                public Set<SprintDaySnapshot> create(Integer effort) {
                    return getEntities(new IsEffort(effort));
                }
            });

    public final Set<SprintDaySnapshot> getSprintDaySnapshotsByEffort(int effort) {
        return sprintDaySnapshotsByEffortCache.get(effort);
    }
    private Set<Integer> effortsCache;

    public final Set<Integer> getEfforts() {
        if (effortsCache == null) {
            effortsCache = new HashSet<Integer>();
            for (SprintDaySnapshot e : getEntities()) {
                effortsCache.add(e.getEffort());
            }
        }
        return effortsCache;
    }

    private static class IsEffort implements Predicate<SprintDaySnapshot> {

        private int value;

        public IsEffort(int value) {
            this.value = value;
        }

        public boolean test(SprintDaySnapshot e) {
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

    protected scrum.server.sprint.SprintDao sprintDao;

    public void setSprintDao(scrum.server.sprint.SprintDao sprintDao) {
        this.sprintDao = sprintDao;
    }

}
