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

    public abstract boolean isEditable();

    public abstract boolean isPlanningEditable();

    public abstract boolean isReviewEditable();

    public abstract boolean isRetrospectiveEditable();

    public abstract boolean isDatesEditable();

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

    private transient LabelModel labelModel;

    public LabelModel getLabelModel() {
        if (labelModel == null) labelModel = createLabelModel();
        return labelModel;
    }

    protected LabelModel createLabelModel() { return new LabelModel(); }

    protected class LabelModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getLabel();
        }

        @Override
        public void setValue(java.lang.String value) {
            setLabel(value);
        }

        @Override
        public boolean isMandatory() { return true; }

        @Override
        public boolean isEditable() { return GSprint.this.isEditable(); }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

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

    private transient GoalModel goalModel;

    public GoalModel getGoalModel() {
        if (goalModel == null) goalModel = createGoalModel();
        return goalModel;
    }

    protected GoalModel createGoalModel() { return new GoalModel(); }

    protected class GoalModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getGoal();
        }

        @Override
        public void setValue(java.lang.String value) {
            setGoal(value);
        }

        @Override
        public boolean isEditable() { return GSprint.this.isEditable(); }

        @Override
        public boolean isRichtext() { return true; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

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

    private transient BeginModel beginModel;

    public BeginModel getBeginModel() {
        if (beginModel == null) beginModel = createBeginModel();
        return beginModel;
    }

    protected BeginModel createBeginModel() { return new BeginModel(); }

    protected class BeginModel extends ilarkesto.gwt.client.editor.ADateEditorModel {

        @Override
        public ilarkesto.gwt.client.Date getValue() {
            return getBegin();
        }

        @Override
        public void setValue(ilarkesto.gwt.client.Date value) {
            setBegin(value);
        }

        @Override
        public boolean isEditable() { return GSprint.this.isDatesEditable(); }

        @Override
        protected void onChangeValue(ilarkesto.gwt.client.Date oldValue, ilarkesto.gwt.client.Date newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

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

    private transient EndModel endModel;

    public EndModel getEndModel() {
        if (endModel == null) endModel = createEndModel();
        return endModel;
    }

    protected EndModel createEndModel() { return new EndModel(); }

    protected class EndModel extends ilarkesto.gwt.client.editor.ADateEditorModel {

        @Override
        public ilarkesto.gwt.client.Date getValue() {
            return getEnd();
        }

        @Override
        public void setValue(ilarkesto.gwt.client.Date value) {
            setEnd(value);
        }

        @Override
        public boolean isEditable() { return GSprint.this.isDatesEditable(); }

        @Override
        protected void onChangeValue(ilarkesto.gwt.client.Date oldValue, ilarkesto.gwt.client.Date newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

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

    private transient VelocityModel velocityModel;

    public VelocityModel getVelocityModel() {
        if (velocityModel == null) velocityModel = createVelocityModel();
        return velocityModel;
    }

    protected VelocityModel createVelocityModel() { return new VelocityModel(); }

    protected class VelocityModel extends ilarkesto.gwt.client.editor.AIntegerEditorModel {

        @Override
        public java.lang.Integer getValue() {
            return getVelocity();
        }

        @Override
        public void setValue(java.lang.Integer value) {
            setVelocity(value);
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
        protected void onChangeValue(java.lang.Integer oldValue, java.lang.Integer newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

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

    private transient CompletedRequirementLabelsModel completedRequirementLabelsModel;

    public CompletedRequirementLabelsModel getCompletedRequirementLabelsModel() {
        if (completedRequirementLabelsModel == null) completedRequirementLabelsModel = createCompletedRequirementLabelsModel();
        return completedRequirementLabelsModel;
    }

    protected CompletedRequirementLabelsModel createCompletedRequirementLabelsModel() { return new CompletedRequirementLabelsModel(); }

    protected class CompletedRequirementLabelsModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getCompletedRequirementLabels();
        }

        @Override
        public void setValue(java.lang.String value) {
            setCompletedRequirementLabels(value);
        }

        @Override
        public boolean isRichtext() { return true; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- planningNote ---

    private java.lang.String planningNote ;

    public final java.lang.String getPlanningNote() {
        return this.planningNote ;
    }

    public final Sprint setPlanningNote(java.lang.String planningNote) {
        if (isPlanningNote(planningNote)) return (Sprint)this;
        this.planningNote = planningNote ;
        propertyChanged("planningNote", this.planningNote);
        return (Sprint)this;
    }

    public final boolean isPlanningNote(java.lang.String planningNote) {
        return equals(this.planningNote, planningNote);
    }

    private transient PlanningNoteModel planningNoteModel;

    public PlanningNoteModel getPlanningNoteModel() {
        if (planningNoteModel == null) planningNoteModel = createPlanningNoteModel();
        return planningNoteModel;
    }

    protected PlanningNoteModel createPlanningNoteModel() { return new PlanningNoteModel(); }

    protected class PlanningNoteModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getPlanningNote();
        }

        @Override
        public void setValue(java.lang.String value) {
            setPlanningNote(value);
        }

        @Override
        public boolean isEditable() { return GSprint.this.isPlanningEditable(); }

        @Override
        public boolean isRichtext() { return true; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

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

    private transient ReviewNoteModel reviewNoteModel;

    public ReviewNoteModel getReviewNoteModel() {
        if (reviewNoteModel == null) reviewNoteModel = createReviewNoteModel();
        return reviewNoteModel;
    }

    protected ReviewNoteModel createReviewNoteModel() { return new ReviewNoteModel(); }

    protected class ReviewNoteModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getReviewNote();
        }

        @Override
        public void setValue(java.lang.String value) {
            setReviewNote(value);
        }

        @Override
        public boolean isEditable() { return GSprint.this.isReviewEditable(); }

        @Override
        public boolean isRichtext() { return true; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

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

    private transient RetrospectiveNoteModel retrospectiveNoteModel;

    public RetrospectiveNoteModel getRetrospectiveNoteModel() {
        if (retrospectiveNoteModel == null) retrospectiveNoteModel = createRetrospectiveNoteModel();
        return retrospectiveNoteModel;
    }

    protected RetrospectiveNoteModel createRetrospectiveNoteModel() { return new RetrospectiveNoteModel(); }

    protected class RetrospectiveNoteModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getRetrospectiveNote();
        }

        @Override
        public void setValue(java.lang.String value) {
            setRetrospectiveNote(value);
        }

        @Override
        public boolean isEditable() { return GSprint.this.isRetrospectiveEditable(); }

        @Override
        public boolean isRichtext() { return true; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

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
        planningNote  = (java.lang.String) props.get("planningNote");
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
        properties.put("planningNote", this.planningNote);
        properties.put("reviewNote", this.reviewNote);
        properties.put("retrospectiveNote", this.retrospectiveNote);
    }

}