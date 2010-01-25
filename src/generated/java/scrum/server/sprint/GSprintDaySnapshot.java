// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.EntityGenerator










package scrum.server.sprint;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GSprintDaySnapshot
            extends AEntity
            implements java.lang.Comparable<SprintDaySnapshot> {

    // --- AEntity ---

    public final SprintDaySnapshotDao getDao() {
        return sprintDaySnapshotDao;
    }

    protected void repairDeadDatob(ADatob datob) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("sprintId", this.sprintId);
        properties.put("date", this.date == null ? null : this.date.toString());
        properties.put("remainingWork", this.remainingWork);
        properties.put("burnedWork", this.burnedWork);
    }

    public int compareTo(SprintDaySnapshot other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final Logger LOG = Logger.get(GSprintDaySnapshot.class);

    public static final String TYPE = "sprintDaySnapshot";

    // -----------------------------------------------------------
    // - sprint
    // -----------------------------------------------------------

    private String sprintId;
    private transient scrum.server.sprint.Sprint sprintCache;

    private void updateSprintCache() {
        sprintCache = this.sprintId == null ? null : (scrum.server.sprint.Sprint)sprintDao.getById(this.sprintId);
    }

    public final scrum.server.sprint.Sprint getSprint() {
        if (sprintCache == null) updateSprintCache();
        return sprintCache;
    }

    public final void setSprint(scrum.server.sprint.Sprint sprint) {
        sprint = prepareSprint(sprint);
        if (isSprint(sprint)) return;
        this.sprintId = sprint == null ? null : sprint.getId();
        sprintCache = sprint;
        fireModified();
    }

    protected scrum.server.sprint.Sprint prepareSprint(scrum.server.sprint.Sprint sprint) {
        return sprint;
    }

    protected void repairDeadSprintReference(String entityId) {
        if (entityId.equals(this.sprintId)) {
            this.sprintId = null;
            fireModified();
        }
    }

    public final boolean isSprintSet() {
        return this.sprintId != null;
    }

    public final boolean isSprint(scrum.server.sprint.Sprint sprint) {
        if (this.sprintId == null && sprint == null) return true;
        return sprint != null && sprint.getId().equals(this.sprintId);
    }

    protected final void updateSprint(Object value) {
        setSprint(value == null ? null : (scrum.server.sprint.Sprint)sprintDao.getById((String)value));
    }

    // -----------------------------------------------------------
    // - date
    // -----------------------------------------------------------

    private ilarkesto.base.time.Date date;

    public final ilarkesto.base.time.Date getDate() {
        return date;
    }

    public final void setDate(ilarkesto.base.time.Date date) {
        date = prepareDate(date);
        if (isDate(date)) return;
        this.date = date;
        fireModified();
    }

    protected ilarkesto.base.time.Date prepareDate(ilarkesto.base.time.Date date) {
        return date;
    }

    public final boolean isDateSet() {
        return this.date != null;
    }

    public final boolean isDate(ilarkesto.base.time.Date date) {
        if (this.date == null && date == null) return true;
        return this.date != null && this.date.equals(date);
    }

    protected final void updateDate(Object value) {
        value = value == null ? null : new ilarkesto.base.time.Date((String)value);
        setDate((ilarkesto.base.time.Date)value);
    }

    // -----------------------------------------------------------
    // - remainingWork
    // -----------------------------------------------------------

    private int remainingWork;

    public final int getRemainingWork() {
        return remainingWork;
    }

    public final void setRemainingWork(int remainingWork) {
        remainingWork = prepareRemainingWork(remainingWork);
        if (isRemainingWork(remainingWork)) return;
        this.remainingWork = remainingWork;
        fireModified();
    }

    protected int prepareRemainingWork(int remainingWork) {
        return remainingWork;
    }

    public final boolean isRemainingWork(int remainingWork) {
        return this.remainingWork == remainingWork;
    }

    protected final void updateRemainingWork(Object value) {
        setRemainingWork((Integer)value);
    }

    // -----------------------------------------------------------
    // - burnedWork
    // -----------------------------------------------------------

    private int burnedWork;

    public final int getBurnedWork() {
        return burnedWork;
    }

    public final void setBurnedWork(int burnedWork) {
        burnedWork = prepareBurnedWork(burnedWork);
        if (isBurnedWork(burnedWork)) return;
        this.burnedWork = burnedWork;
        fireModified();
    }

    protected int prepareBurnedWork(int burnedWork) {
        return burnedWork;
    }

    public final boolean isBurnedWork(int burnedWork) {
        return this.burnedWork == burnedWork;
    }

    protected final void updateBurnedWork(Object value) {
        setBurnedWork((Integer)value);
    }

    public void updateProperties(Map<?, ?> properties) {
        for (Map.Entry entry : properties.entrySet()) {
            String property = (String) entry.getKey();
            if (property.equals("id")) continue;
            Object value = entry.getValue();
            if (property.equals("sprintId")) updateSprint(value);
            if (property.equals("date")) updateDate(value);
            if (property.equals("remainingWork")) updateRemainingWork(value);
            if (property.equals("burnedWork")) updateBurnedWork(value);
        }
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadSprintReference(entityId);
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
        try {
            getSprint();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead sprint reference");
            repairDeadSprintReference(this.sprintId);
        }
    }


    // -----------------------------------------------------------
    // - dependencies
    // -----------------------------------------------------------

    static scrum.server.sprint.SprintDao sprintDao;

    public static final void setSprintDao(scrum.server.sprint.SprintDao sprintDao) {
        GSprintDaySnapshot.sprintDao = sprintDao;
    }

    static SprintDaySnapshotDao sprintDaySnapshotDao;

    public static final void setSprintDaySnapshotDao(SprintDaySnapshotDao sprintDaySnapshotDao) {
        GSprintDaySnapshot.sprintDaySnapshotDao = sprintDaySnapshotDao;
    }

}