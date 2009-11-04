// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtEntityGenerator










package scrum.client.collaboration;

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

    // --- text ---

    private java.lang.String text ;

    public final java.lang.String getText() {
        return this.text ;
    }

    public final ProjectEvent setText(java.lang.String text) {
        if (isText(text)) return (ProjectEvent)this;
        this.text = text ;
        propertyChanged("text", this.text);
        return (ProjectEvent)this;
    }

    public final boolean isText(java.lang.String text) {
        return equals(this.text, text);
    }

    private transient ilarkesto.gwt.client.editor.ATextEditorModel textModel;

    public ilarkesto.gwt.client.editor.ATextEditorModel getTextModel() {
        if (textModel == null) textModel = new ilarkesto.gwt.client.editor.ATextEditorModel() {

            @Override
            public String getValue() {
                return getText();
            }

            @Override
            public void setValue(String value) {
                setText(value);
            }


            @Override
            public boolean isMandatory() { return true; }
        };
        return textModel;
    }

    // --- timestamp ---

    private ilarkesto.gwt.client.DateAndTime timestamp ;

    public final ilarkesto.gwt.client.DateAndTime getTimestamp() {
        return this.timestamp ;
    }

    public final ProjectEvent setTimestamp(ilarkesto.gwt.client.DateAndTime timestamp) {
        if (isTimestamp(timestamp)) return (ProjectEvent)this;
        this.timestamp = timestamp ;
        propertyChanged("timestamp", this.timestamp);
        return (ProjectEvent)this;
    }

    public final boolean isTimestamp(ilarkesto.gwt.client.DateAndTime timestamp) {
        return equals(this.timestamp, timestamp);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        projectId = (String) props.get("projectId");
        text  = (java.lang.String) props.get("text");
        String timestampAsString = (String) props.get("timestamp");
        timestamp  =  timestampAsString == null ? null : new ilarkesto.gwt.client.DateAndTime(timestampAsString);
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("text", this.text);
        properties.put("timestamp", this.timestamp == null ? null : this.timestamp.toString());
    }

}