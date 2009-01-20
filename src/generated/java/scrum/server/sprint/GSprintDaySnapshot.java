









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.EntityGenerator










package scrum.server.sprint;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.logging.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.persistence.*;

public abstract class GSprintDaySnapshot
            extends AEntity
            implements java.lang.Comparable<SprintDaySnapshot> {

    // --- AEntity ---

    public final SprintDaySnapshotDao getDao() {
        return sprintDaySnapshotDao;
    }

    protected void repairDeadValueObject(AValueObject valueObject) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("remainingWork", this.remainingWork);
        properties.put("burnedWork", this.burnedWork);
        properties.put("sprintId", this.sprintId);
        properties.put("dateCrap", this.dateCrap);
    }

    public int compareTo(SprintDaySnapshot other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final Logger LOG = Logger.get(GSprintDaySnapshot.class);

    public static final String TYPE = "sprintDaySnapshot";

    // --- copy constructor ---
    public GSprintDaySnapshot(GSprintDaySnapshot template) {
        super(template);
        if (template==null) return;

        setRemainingWork(template.getRemainingWork());
        setBurnedWork(template.getBurnedWork());
        setSprint(template.getSprint());
        setDateCrap(template.getDateCrap());
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
        entityModified();
    }

    protected int prepareRemainingWork(int remainingWork) {
        return remainingWork;
    }

    public final boolean isRemainingWork(int remainingWork) {
        return this.remainingWork == remainingWork;
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
        entityModified();
    }

    protected int prepareBurnedWork(int burnedWork) {
        return burnedWork;
    }

    public final boolean isBurnedWork(int burnedWork) {
        return this.burnedWork == burnedWork;
    }

    // -----------------------------------------------------------
    // - sprint
    // -----------------------------------------------------------

    private String sprintId;

    public final scrum.server.sprint.Sprint getSprint() {
        if (this.sprintId == null) return null;
        return (scrum.server.sprint.Sprint)sprintDao.getById(this.sprintId);
    }

    public final void setSprint(scrum.server.sprint.Sprint sprint) {
        sprint = prepareSprint(sprint);
        if (isSprint(sprint)) return;
        this.sprintId = sprint == null ? null : sprint.getId();
        entityModified();
    }

    protected scrum.server.sprint.Sprint prepareSprint(scrum.server.sprint.Sprint sprint) {
        return sprint;
    }

    protected void repairDeadSprintReference(String entityId) {
        if (entityId.equals(this.sprintId)) {
            this.sprintId = null;
            entityModified();
        }
    }

    public final boolean isSprintSet() {
        return this.sprintId != null;
    }

    public final boolean isSprint(scrum.server.sprint.Sprint sprint) {
        if (this.sprintId == null && sprint == null) return true;
        return sprint != null && sprint.getId().equals(this.sprintId);
    }

    // -----------------------------------------------------------
    // - dateCrap
    // -----------------------------------------------------------

    private java.util.Date dateCrap;

    public final java.util.Date getDateCrap() {
        return dateCrap;
    }

    public final void setDateCrap(java.util.Date dateCrap) {
        dateCrap = prepareDateCrap(dateCrap);
        if (isDateCrap(dateCrap)) return;
        this.dateCrap = dateCrap;
        entityModified();
    }

    protected java.util.Date prepareDateCrap(java.util.Date dateCrap) {
        return dateCrap;
    }

    public final boolean isDateCrapSet() {
        return this.dateCrap != null;
    }

    public final boolean isDateCrap(java.util.Date dateCrap) {
        if (this.dateCrap == null && dateCrap == null) return true;
        return this.dateCrap != null && this.dateCrap.equals(dateCrap);
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
    // - composites
    // -----------------------------------------------------------

    // --- dependencies ---

    protected static scrum.server.sprint.SprintDao sprintDao;

    public static final void setSprintDao(scrum.server.sprint.SprintDao sprintDao) {
        GSprintDaySnapshot.sprintDao = sprintDao;
    }

    protected static SprintDaySnapshotDao sprintDaySnapshotDao;

    public static final void setSprintDaySnapshotDao(SprintDaySnapshotDao sprintDaySnapshotDao) {
        GSprintDaySnapshot.sprintDaySnapshotDao = sprintDaySnapshotDao;
    }

}
