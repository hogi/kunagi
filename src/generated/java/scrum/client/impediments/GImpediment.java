









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtEntityGenerator










package scrum.client.impediments;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.logging.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.persistence.*;

public abstract class GImpediment
            extends scrum.client.common.AGwtEntity {

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

    // --- solution ---

    private java.lang.String solution ;

    public final java.lang.String getSolution() {
        return this.solution ;
    }

    public final Impediment setSolution(java.lang.String solution) {
        this.solution = solution ;
        propertyChanged("solution", this.solution);
        return (Impediment)this;
    }

    public final boolean isSolution(java.lang.String solution) {
        return equals(this.solution, solution);
    }

    // --- date ---

    private scrum.client.common.Date date ;

    public final scrum.client.common.Date getDate() {
        return this.date ;
    }

    public final Impediment setDate(scrum.client.common.Date date) {
        this.date = date ;
        propertyChanged("date", this.date);
        return (Impediment)this;
    }

    public final boolean isDate(scrum.client.common.Date date) {
        return equals(this.date, date);
    }

    // --- description ---

    private java.lang.String description ;

    public final java.lang.String getDescription() {
        return this.description ;
    }

    public final Impediment setDescription(java.lang.String description) {
        this.description = description ;
        propertyChanged("description", this.description);
        return (Impediment)this;
    }

    public final boolean isDescription(java.lang.String description) {
        return equals(this.description, description);
    }

    // --- solveDate ---

    private scrum.client.common.Date solveDate ;

    public final scrum.client.common.Date getSolveDate() {
        return this.solveDate ;
    }

    public final Impediment setSolveDate(scrum.client.common.Date solveDate) {
        this.solveDate = solveDate ;
        propertyChanged("solveDate", this.solveDate);
        return (Impediment)this;
    }

    public final boolean isSolveDate(scrum.client.common.Date solveDate) {
        return equals(this.solveDate, solveDate);
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Impediment setLabel(java.lang.String label) {
        this.label = label ;
        propertyChanged("label", this.label);
        return (Impediment)this;
    }

    public final boolean isLabel(java.lang.String label) {
        return equals(this.label, label);
    }

    // --- project ---

    private String projectId;

    public final scrum.client.project.Project getProject() {
        if (projectId == null) return null;
        return getDao().getProject(this.projectId);
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

    // --- update properties by map ---

    public void updateProperties(Map props) {
        solution  = (java.lang.String) props.get("solution");
        String dateAsString = (String) props.get("date");
        date  =  dateAsString == null ? null : new scrum.client.common.Date(dateAsString);
        description  = (java.lang.String) props.get("description");
        String solveDateAsString = (String) props.get("solveDate");
        solveDate  =  solveDateAsString == null ? null : new scrum.client.common.Date(solveDateAsString);
        label  = (java.lang.String) props.get("label");
        projectId = (String) props.get("projectId");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("solution", this.solution);
        properties.put("date", this.date == null ? null : this.date.toString());
        properties.put("description", this.description);
        properties.put("solveDate", this.solveDate == null ? null : this.solveDate.toString());
        properties.put("label", this.label);
        properties.put("projectId", this.projectId);
    }

}
