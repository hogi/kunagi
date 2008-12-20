









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtEntityGenerator










package scrum.client.sprint;

import java.util.*;

public abstract class GSprintDaySnapshot
            extends scrum.client.common.AGwtEntity {

    public GSprintDaySnapshot() {
    }

    public GSprintDaySnapshot(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "sprintDaySnapshot";

    @Override
    public final String getEntityType() {
        return ENTITY_TYPE;
    }

    // --- sprint ---

    private String sprintId;

    public final scrum.client.sprint.Sprint getSprint() {
        return getDao().getSprint(this.sprintId);
    }

    public final SprintDaySnapshot setSprint(scrum.client.sprint.Sprint sprint) {
        String id = sprint == null ? null : sprint.getId();
        if (equals(this.sprintId, id)) return (SprintDaySnapshot) this;
        this.sprintId = id;
        propertyChanged("sprint", this.sprintId);
        return (SprintDaySnapshot)this;
    }

    public final boolean isSprint(scrum.client.sprint.Sprint sprint) {
        return equals(this.sprintId, sprint);
    }

    // --- burndown ---

    private int burndown ;

    public final int getBurndown() {
        return this.burndown ;
    }

    public final SprintDaySnapshot setBurndown(int burndown) {
        this.burndown = burndown ;
        propertyChanged("burndown", this.burndown);
        return (SprintDaySnapshot)this;
    }

    public final boolean isBurndown(int burndown) {
        return equals(this.burndown, burndown);
    }

    // --- dateCrap ---

    private java.util.Date dateCrap ;

    public final java.util.Date getDateCrap() {
        return this.dateCrap ;
    }

    public final SprintDaySnapshot setDateCrap(java.util.Date dateCrap) {
        this.dateCrap = dateCrap ;
        propertyChanged("dateCrap", this.dateCrap);
        return (SprintDaySnapshot)this;
    }

    public final boolean isDateCrap(java.util.Date dateCrap) {
        return equals(this.dateCrap, dateCrap);
    }

    // --- effort ---

    private int effort ;

    public final int getEffort() {
        return this.effort ;
    }

    public final SprintDaySnapshot setEffort(int effort) {
        this.effort = effort ;
        propertyChanged("effort", this.effort);
        return (SprintDaySnapshot)this;
    }

    public final boolean isEffort(int effort) {
        return equals(this.effort, effort);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        sprintId = (String) props.get("sprintId");
        burndown  = (Integer) props.get("burndown");
        dateCrap  = (java.util.Date) props.get("dateCrap");
        effort  = (Integer) props.get("effort");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("sprintId", this.sprintId);
        properties.put("burndown", this.burndown);
        properties.put("dateCrap", this.dateCrap);
        properties.put("effort", this.effort);
    }

}
