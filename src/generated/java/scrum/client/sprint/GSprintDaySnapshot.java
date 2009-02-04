









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

    // --- burnedWork ---

    private int burnedWork ;

    public final int getBurnedWork() {
        return this.burnedWork ;
    }

    public final SprintDaySnapshot setBurnedWork(int burnedWork) {
        this.burnedWork = burnedWork ;
        propertyChanged("burnedWork", this.burnedWork);
        return (SprintDaySnapshot)this;
    }

    public final boolean isBurnedWork(int burnedWork) {
        return equals(this.burnedWork, burnedWork);
    }

    // --- sprint ---

    private String sprintId;

    public final scrum.client.sprint.Sprint getSprint() {
        if (sprintId == null) return null;
        return getDao().getSprint(this.sprintId);
    }

    public final SprintDaySnapshot setSprint(scrum.client.sprint.Sprint sprint) {
        String id = sprint == null ? null : sprint.getId();
        if (equals(this.sprintId, id)) return (SprintDaySnapshot) this;
        this.sprintId = id;
        propertyChanged("sprintId", this.sprintId);
        return (SprintDaySnapshot)this;
    }

    public final boolean isSprint(scrum.client.sprint.Sprint sprint) {
        return equals(this.sprintId, sprint);
    }

    // --- remainingWork ---

    private int remainingWork ;

    public final int getRemainingWork() {
        return this.remainingWork ;
    }

    public final SprintDaySnapshot setRemainingWork(int remainingWork) {
        this.remainingWork = remainingWork ;
        propertyChanged("remainingWork", this.remainingWork);
        return (SprintDaySnapshot)this;
    }

    public final boolean isRemainingWork(int remainingWork) {
        return equals(this.remainingWork, remainingWork);
    }

    // --- date ---

    private scrum.client.common.Date date ;

    public final scrum.client.common.Date getDate() {
        return this.date ;
    }

    public final SprintDaySnapshot setDate(scrum.client.common.Date date) {
        this.date = date ;
        propertyChanged("date", this.date);
        return (SprintDaySnapshot)this;
    }

    public final boolean isDate(scrum.client.common.Date date) {
        return equals(this.date, date);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        burnedWork  = (Integer) props.get("burnedWork");
        sprintId = (String) props.get("sprintId");
        remainingWork  = (Integer) props.get("remainingWork");
        String dateAsString = (String) props.get("date");
        date  =  dateAsString == null ? null : new scrum.client.common.Date(dateAsString);
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("burnedWork", this.burnedWork);
        properties.put("sprintId", this.sprintId);
        properties.put("remainingWork", this.remainingWork);
        properties.put("date", this.date == null ? null : this.date.toString());
    }

}
