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

    private transient ilarkesto.gwt.client.editor.ATextEditorModel goalModel;

    public ilarkesto.gwt.client.editor.ATextEditorModel getGoalModel() {
        if (goalModel == null) goalModel = new ilarkesto.gwt.client.editor.ATextEditorModel() {

            @Override
            public String getValue() {
                return getGoal();
            }

            @Override
            public void setValue(String value) {
                setGoal(value);
            }

        };
        return goalModel;
    }

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

    private transient ilarkesto.gwt.client.editor.ADateEditorModel beginModel;

    public ilarkesto.gwt.client.editor.ADateEditorModel getBeginModel() {
        if (beginModel == null) beginModel = new ilarkesto.gwt.client.editor.ADateEditorModel() {

            @Override
            public ilarkesto.gwt.client.Date getValue() {
                return getBegin();
            }

            @Override
            public void setValue(ilarkesto.gwt.client.Date value) {
                setBegin(value);
            }

        };
        return beginModel;
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

    private transient ilarkesto.gwt.client.editor.ADateEditorModel endModel;

    public ilarkesto.gwt.client.editor.ADateEditorModel getEndModel() {
        if (endModel == null) endModel = new ilarkesto.gwt.client.editor.ADateEditorModel() {

            @Override
            public ilarkesto.gwt.client.Date getValue() {
                return getEnd();
            }

            @Override
            public void setValue(ilarkesto.gwt.client.Date value) {
                setEnd(value);
            }

        };
        return endModel;
    }

    // --- velocity ---

    private java.lang.Integer velocity ;

    public final java.lang.Integer getVelocity() {
        return this.velocity ;
    }

    public final Sprint setVelocity(java.lang.Integer velocity) {
        if (isVelocity(velocity)) return (Sprint)this;
        this.velocity = velocity ;
        propertyChanged("velocity", this.velocity);
        return (Sprint)this;
    }

    public final boolean isVelocity(java.lang.Integer velocity) {
        return equals(this.velocity, velocity);
    }

    private transient ilarkesto.gwt.client.editor.AIntegerEditorModel velocityModel;

    public ilarkesto.gwt.client.editor.AIntegerEditorModel getVelocityModel() {
        if (velocityModel == null) velocityModel = new ilarkesto.gwt.client.editor.AIntegerEditorModel() {

            @Override
            public Integer getValue() {
                return getVelocity();
            }

            @Override
            public void increment() {
                setVelocity(getVelocity() + 1);
            }

            @Override
            public void decrement() {
                setVelocity(getVelocity() - 1);
            }

            @Override
            public void setValue(Integer value) {
                setVelocity(value);
            }

        };
        return velocityModel;
    }

    // --- completedRequirementLabels ---

    private java.lang.String completedRequirementLabels ;

    public final java.lang.String getCompletedRequirementLabels() {
        return this.completedRequirementLabels ;
    }

    public final Sprint setCompletedRequirementLabels(java.lang.String completedRequirementLabels) {
        if (isCompletedRequirementLabels(completedRequirementLabels)) return (Sprint)this;
        this.completedRequirementLabels = completedRequirementLabels ;
        propertyChanged("completedRequirementLabels", this.completedRequirementLabels);
        return (Sprint)this;
    }

    public final boolean isCompletedRequirementLabels(java.lang.String completedRequirementLabels) {
        return equals(this.completedRequirementLabels, completedRequirementLabels);
    }

    private transient ilarkesto.gwt.client.editor.ATextEditorModel completedRequirementLabelsModel;

    public ilarkesto.gwt.client.editor.ATextEditorModel getCompletedRequirementLabelsModel() {
        if (completedRequirementLabelsModel == null) completedRequirementLabelsModel = new ilarkesto.gwt.client.editor.ATextEditorModel() {

            @Override
            public String getValue() {
                return getCompletedRequirementLabels();
            }

            @Override
            public void setValue(String value) {
                setCompletedRequirementLabels(value);
            }

        };
        return completedRequirementLabelsModel;
    }

    // --- planingNote ---

    private java.lang.String planingNote ;

    public final java.lang.String getPlaningNote() {
        return this.planingNote ;
    }

    public final Sprint setPlaningNote(java.lang.String planingNote) {
        if (isPlaningNote(planingNote)) return (Sprint)this;
        this.planingNote = planingNote ;
        propertyChanged("planingNote", this.planingNote);
        return (Sprint)this;
    }

    public final boolean isPlaningNote(java.lang.String planingNote) {
        return equals(this.planingNote, planingNote);
    }

    private transient ilarkesto.gwt.client.editor.ATextEditorModel planingNoteModel;

    public ilarkesto.gwt.client.editor.ATextEditorModel getPlaningNoteModel() {
        if (planingNoteModel == null) planingNoteModel = new ilarkesto.gwt.client.editor.ATextEditorModel() {

            @Override
            public String getValue() {
                return getPlaningNote();
            }

            @Override
            public void setValue(String value) {
                setPlaningNote(value);
            }

        };
        return planingNoteModel;
    }

    // --- reviewNote ---

    private java.lang.String reviewNote ;

    public final java.lang.String getReviewNote() {
        return this.reviewNote ;
    }

    public final Sprint setReviewNote(java.lang.String reviewNote) {
        if (isReviewNote(reviewNote)) return (Sprint)this;
        this.reviewNote = reviewNote ;
        propertyChanged("reviewNote", this.reviewNote);
        return (Sprint)this;
    }

    public final boolean isReviewNote(java.lang.String reviewNote) {
        return equals(this.reviewNote, reviewNote);
    }

    private transient ilarkesto.gwt.client.editor.ATextEditorModel reviewNoteModel;

    public ilarkesto.gwt.client.editor.ATextEditorModel getReviewNoteModel() {
        if (reviewNoteModel == null) reviewNoteModel = new ilarkesto.gwt.client.editor.ATextEditorModel() {

            @Override
            public String getValue() {
                return getReviewNote();
            }

            @Override
            public void setValue(String value) {
                setReviewNote(value);
            }

        };
        return reviewNoteModel;
    }

    // --- retrospectiveNote ---

    private java.lang.String retrospectiveNote ;

    public final java.lang.String getRetrospectiveNote() {
        return this.retrospectiveNote ;
    }

    public final Sprint setRetrospectiveNote(java.lang.String retrospectiveNote) {
        if (isRetrospectiveNote(retrospectiveNote)) return (Sprint)this;
        this.retrospectiveNote = retrospectiveNote ;
        propertyChanged("retrospectiveNote", this.retrospectiveNote);
        return (Sprint)this;
    }

    public final boolean isRetrospectiveNote(java.lang.String retrospectiveNote) {
        return equals(this.retrospectiveNote, retrospectiveNote);
    }

    private transient ilarkesto.gwt.client.editor.ATextEditorModel retrospectiveNoteModel;

    public ilarkesto.gwt.client.editor.ATextEditorModel getRetrospectiveNoteModel() {
        if (retrospectiveNoteModel == null) retrospectiveNoteModel = new ilarkesto.gwt.client.editor.ATextEditorModel() {

            @Override
            public String getValue() {
                return getRetrospectiveNote();
            }

            @Override
            public void setValue(String value) {
                setRetrospectiveNote(value);
            }

        };
        return retrospectiveNoteModel;
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
        velocity  = (java.lang.Integer) props.get("velocity");
        completedRequirementLabels  = (java.lang.String) props.get("completedRequirementLabels");
        planingNote  = (java.lang.String) props.get("planingNote");
        reviewNote  = (java.lang.String) props.get("reviewNote");
        retrospectiveNote  = (java.lang.String) props.get("retrospectiveNote");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("label", this.label);
        properties.put("goal", this.goal);
        properties.put("begin", this.begin == null ? null : this.begin.toString());
        properties.put("end", this.end == null ? null : this.end.toString());
        properties.put("velocity", this.velocity);
        properties.put("completedRequirementLabels", this.completedRequirementLabels);
        properties.put("planingNote", this.planingNote);
        properties.put("reviewNote", this.reviewNote);
        properties.put("retrospectiveNote", this.retrospectiveNote);
    }

}