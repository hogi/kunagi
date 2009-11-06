// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtEntityGenerator










package scrum.client.journal;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GProjectEvent
            extends scrum.client.common.AScrumGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public GProjectEvent() {
    }

    public GProjectEvent(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "projectEvent";

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

    public final ProjectEvent setProject(scrum.client.project.Project project) {
        String id = project == null ? null : project.getId();
        if (equals(this.projectId, id)) return (ProjectEvent) this;
        this.projectId = id;
        propertyChanged("projectId", this.projectId);
        return (ProjectEvent)this;
    }

    public final boolean isProject(scrum.client.project.Project project) {
        return equals(this.projectId, project);
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final ProjectEvent setLabel(java.lang.String label) {
        if (isLabel(label)) return (ProjectEvent)this;
        this.label = label ;
        propertyChanged("label", this.label);
        return (ProjectEvent)this;
    }

    public final boolean isLabel(java.lang.String label) {
        return equals(this.label, label);
    }

    private transient ilarkesto.gwt.client.editor.ATextEditorModel labelModel;

    public ilarkesto.gwt.client.editor.ATextEditorModel getLabelModel() {
        if (labelModel == null) labelModel = new ilarkesto.gwt.client.editor.ATextEditorModel() {

            @Override
            public String getValue() {
                return getLabel();
            }

            @Override
            public void setValue(String value) {
                setLabel(value);
            }


            @Override
            public boolean isMandatory() { return true; }
        };
        return labelModel;
    }

    // --- dateAndTime ---

    private ilarkesto.gwt.client.DateAndTime dateAndTime ;

    public final ilarkesto.gwt.client.DateAndTime getDateAndTime() {
        return this.dateAndTime ;
    }

    public final ProjectEvent setDateAndTime(ilarkesto.gwt.client.DateAndTime dateAndTime) {
        if (isDateAndTime(dateAndTime)) return (ProjectEvent)this;
        this.dateAndTime = dateAndTime ;
        propertyChanged("dateAndTime", this.dateAndTime);
        return (ProjectEvent)this;
    }

    public final boolean isDateAndTime(ilarkesto.gwt.client.DateAndTime dateAndTime) {
        return equals(this.dateAndTime, dateAndTime);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        projectId = (String) props.get("projectId");
        label  = (java.lang.String) props.get("label");
        String dateAndTimeAsString = (String) props.get("dateAndTime");
        dateAndTime  =  dateAndTimeAsString == null ? null : new ilarkesto.gwt.client.DateAndTime(dateAndTimeAsString);
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("label", this.label);
        properties.put("dateAndTime", this.dateAndTime == null ? null : this.dateAndTime.toString());
    }

}