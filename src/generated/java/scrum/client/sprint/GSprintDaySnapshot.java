









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtEntityGenerator










package scrum.client.sprint;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.gwt.client.*;
import ilarkesto.logging.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.persistence.*;
import scrum.client.common.*;

public abstract class GSprintDaySnapshot
            extends ilarkesto.gwt.client.AGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

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

    // --- date ---

    private ilarkesto.gwt.client.Date date ;

    public final ilarkesto.gwt.client.Date getDate() {
        return this.date ;
    }

    public final SprintDaySnapshot setDate(ilarkesto.gwt.client.Date date) {
        this.date = date ;
        propertyChanged("date", this.date);
        return (SprintDaySnapshot)this;
    }

    public final boolean isDate(ilarkesto.gwt.client.Date date) {
        return equals(this.date, date);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        burnedWork  = (Integer) props.get("burnedWork");
        remainingWork  = (Integer) props.get("remainingWork");
        sprintId = (String) props.get("sprintId");
        String dateAsString = (String) props.get("date");
        date  =  dateAsString == null ? null : new ilarkesto.gwt.client.Date(dateAsString);
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("burnedWork", this.burnedWork);
        properties.put("remainingWork", this.remainingWork);
        properties.put("sprintId", this.sprintId);
        properties.put("date", this.date == null ? null : this.date.toString());
    }

}
