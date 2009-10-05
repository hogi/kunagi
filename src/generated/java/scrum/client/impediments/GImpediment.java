// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtEntityGenerator










package scrum.client.impediments;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GImpediment
            extends ilarkesto.gwt.client.AGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public GImpediment() {
    }

    public GImpediment(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "impediment";

    @Override
    public final String getEntityType() {
        return ENTITY_TYPE;
    }

    // --- project ---

    private String projectId;

    public final scrum.client.project.Project getProject() {
        if (projectId == null) return null;
        return getDao().getProject(this.projectId);
    }

    public final boolean isProjectSet() {
        return projectId != null;
    }

    public final Impediment setProject(scrum.client.project.Project project) {
        String id = project == null ? null : project.getId();
        if (equals(this.projectId, id)) return (Impediment) this;
        this.projectId = id;
        propertyChanged("projectId", this.projectId);
        return (Impediment)this;
    }

    public final boolean isProject(scrum.client.project.Project project) {
        return equals(this.projectId, project);
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Impediment setLabel(java.lang.String label) {
        if (isLabel(label)) return (Impediment)this;
        this.label = label ;
        propertyChanged("label", this.label);
        return (Impediment)this;
    }

    public final boolean isLabel(java.lang.String label) {
        return equals(this.label, label);
    }

    // --- date ---

    private ilarkesto.gwt.client.Date date ;

    public final ilarkesto.gwt.client.Date getDate() {
        return this.date ;
    }

    public final Impediment setDate(ilarkesto.gwt.client.Date date) {
        if (isDate(date)) return (Impediment)this;
        this.date = date ;
        propertyChanged("date", this.date);
        return (Impediment)this;
    }

    public final boolean isDate(ilarkesto.gwt.client.Date date) {
        return equals(this.date, date);
    }

    // --- description ---

    private java.lang.String description ;

    public final java.lang.String getDescription() {
        return this.description ;
    }

    public final Impediment setDescription(java.lang.String description) {
        if (isDescription(description)) return (Impediment)this;
        this.description = description ;
        propertyChanged("description", this.description);
        return (Impediment)this;
    }

    public final boolean isDescription(java.lang.String description) {
        return equals(this.description, description);
    }

    // --- solution ---

    private java.lang.String solution ;

    public final java.lang.String getSolution() {
        return this.solution ;
    }

    public final Impediment setSolution(java.lang.String solution) {
        if (isSolution(solution)) return (Impediment)this;
        this.solution = solution ;
        propertyChanged("solution", this.solution);
        return (Impediment)this;
    }

    public final boolean isSolution(java.lang.String solution) {
        return equals(this.solution, solution);
    }

    // --- closed ---

    private boolean closed ;

    public final boolean isClosed() {
        return this.closed ;
    }

    public final Impediment setClosed(boolean closed) {
        if (isClosed(closed)) return (Impediment)this;
        this.closed = closed ;
        propertyChanged("closed", this.closed);
        return (Impediment)this;
    }

    public final boolean isClosed(boolean closed) {
        return equals(this.closed, closed);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        projectId = (String) props.get("projectId");
        label  = (java.lang.String) props.get("label");
        String dateAsString = (String) props.get("date");
        date  =  dateAsString == null ? null : new ilarkesto.gwt.client.Date(dateAsString);
        description  = (java.lang.String) props.get("description");
        solution  = (java.lang.String) props.get("solution");
        closed  = (Boolean) props.get("closed");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("label", this.label);
        properties.put("date", this.date == null ? null : this.date.toString());
        properties.put("description", this.description);
        properties.put("solution", this.solution);
        properties.put("closed", this.closed);
    }

}