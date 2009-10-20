// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtEntityGenerator










package scrum.client.sprint;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GSprint
            extends scrum.client.common.AScrumGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public GSprint() {
    }

    public GSprint(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "sprint";

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

    public final Sprint setProject(scrum.client.project.Project project) {
        String id = project == null ? null : project.getId();
        if (equals(this.projectId, id)) return (Sprint) this;
        this.projectId = id;
        propertyChanged("projectId", this.projectId);
        return (Sprint)this;
    }

    public final boolean isProject(scrum.client.project.Project project) {
        return equals(this.projectId, project);
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Sprint setLabel(java.lang.String label) {
        if (isLabel(label)) return (Sprint)this;
        this.label = label ;
        propertyChanged("label", this.label);
        return (Sprint)this;
    }

    public final boolean isLabel(java.lang.String label) {
        return equals(this.label, label);
    }

    public transient ilarkesto.gwt.client.editor.ATextEditorModel labelModel = new ilarkesto.gwt.client.editor.ATextEditorModel() {

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

    // --- goal ---

    private java.lang.String goal ;

    public final java.lang.String getGoal() {
        return this.goal ;
    }

    public final Sprint setGoal(java.lang.String goal) {
        if (isGoal(goal)) return (Sprint)this;
        this.goal = goal ;
        propertyChanged("goal", this.goal);
        return (Sprint)this;
    }

    public final boolean isGoal(java.lang.String goal) {
        return equals(this.goal, goal);
    }

    public transient ilarkesto.gwt.client.editor.ATextEditorModel goalModel = new ilarkesto.gwt.client.editor.ATextEditorModel() {

        @Override
        public String getValue() {
            return getGoal();
        }

        @Override
        public void setValue(String value) {
            setGoal(value);
        }

    };

    // --- begin ---

    private ilarkesto.gwt.client.Date begin ;

    public final ilarkesto.gwt.client.Date getBegin() {
        return this.begin ;
    }

    public final Sprint setBegin(ilarkesto.gwt.client.Date begin) {
        if (isBegin(begin)) return (Sprint)this;
        this.begin = begin ;
        propertyChanged("begin", this.begin);
        return (Sprint)this;
    }

    public final boolean isBegin(ilarkesto.gwt.client.Date begin) {
        return equals(this.begin, begin);
    }

    // --- end ---

    private ilarkesto.gwt.client.Date end ;

    public final ilarkesto.gwt.client.Date getEnd() {
        return this.end ;
    }

    public final Sprint setEnd(ilarkesto.gwt.client.Date end) {
        if (isEnd(end)) return (Sprint)this;
        this.end = end ;
        propertyChanged("end", this.end);
        return (Sprint)this;
    }

    public final boolean isEnd(ilarkesto.gwt.client.Date end) {
        return equals(this.end, end);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        projectId = (String) props.get("projectId");
        label  = (java.lang.String) props.get("label");
        goal  = (java.lang.String) props.get("goal");
        String beginAsString = (String) props.get("begin");
        begin  =  beginAsString == null ? null : new ilarkesto.gwt.client.Date(beginAsString);
        String endAsString = (String) props.get("end");
        end  =  endAsString == null ? null : new ilarkesto.gwt.client.Date(endAsString);
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("label", this.label);
        properties.put("goal", this.goal);
        properties.put("begin", this.begin == null ? null : this.begin.toString());
        properties.put("end", this.end == null ? null : this.end.toString());
    }

}