









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
        properties.put("dateCrap", this.dateCrap);
        properties.put("sprintId", this.sprintId);
        properties.put("burndown", this.burndown);
        properties.put("effort", this.effort);
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

        setDateCrap(template.getDateCrap());
        setSprint(template.getSprint());
        setBurndown(template.getBurndown());
        setEffort(template.getEffort());
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
    // - burndown
    // -----------------------------------------------------------

    private int burndown;

    public final int getBurndown() {
        return burndown;
    }

    public final void setBurndown(int burndown) {
        burndown = prepareBurndown(burndown);
        if (isBurndown(burndown)) return;
        this.burndown = burndown;
        entityModified();
    }

    protected int prepareBurndown(int burndown) {
        return burndown;
    }

    public final boolean isBurndown(int burndown) {
        return this.burndown == burndown;
    }

    // -----------------------------------------------------------
    // - effort
    // -----------------------------------------------------------

    private int effort;

    public final int getEffort() {
        return effort;
    }

    public final void setEffort(int effort) {
        effort = prepareEffort(effort);
        if (isEffort(effort)) return;
        this.effort = effort;
        entityModified();
    }

    protected int prepareEffort(int effort) {
        return effort;
    }

    public final boolean isEffort(int effort) {
        return this.effort == effort;
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
