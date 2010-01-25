// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtEntityGenerator










package scrum.client.project;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GRequirement
            extends scrum.client.common.AScrumGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public abstract boolean isEditable();

    public GRequirement() {
    }

    public GRequirement(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "requirement";

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

    public final Requirement setProject(scrum.client.project.Project project) {
        String id = project == null ? null : project.getId();
        if (equals(this.projectId, id)) return (Requirement) this;
        this.projectId = id;
        propertyChanged("projectId", this.projectId);
        return (Requirement)this;
    }

    public final boolean isProject(scrum.client.project.Project project) {
        return equals(this.projectId, project);
    }

    // --- sprint ---

    private String sprintId;

    public final scrum.client.sprint.Sprint getSprint() {
        if (sprintId == null) return null;
        return getDao().getSprint(this.sprintId);
    }

    public final boolean isSprintSet() {
        return sprintId != null;
    }

    public final Requirement setSprint(scrum.client.sprint.Sprint sprint) {
        String id = sprint == null ? null : sprint.getId();
        if (equals(this.sprintId, id)) return (Requirement) this;
        this.sprintId = id;
        propertyChanged("sprintId", this.sprintId);
        return (Requirement)this;
    }

    public final boolean isSprint(scrum.client.sprint.Sprint sprint) {
        return equals(this.sprintId, sprint);
    }

    // --- number ---

    private int number ;

    public final int getNumber() {
        return this.number ;
    }

    public final Requirement setNumber(int number) {
        if (isNumber(number)) return (Requirement)this;
        this.number = number ;
        propertyChanged("number", this.number);
        return (Requirement)this;
    }

    public final boolean isNumber(int number) {
        return equals(this.number, number);
    }

    private transient NumberModel numberModel;

    public NumberModel getNumberModel() {
        if (numberModel == null) numberModel = createNumberModel();
        return numberModel;
    }

    protected NumberModel createNumberModel() { return new NumberModel(); }

    protected class NumberModel extends ilarkesto.gwt.client.editor.AIntegerEditorModel {

        @Override
        public java.lang.Integer getValue() {
            return getNumber();
        }

        @Override
        public void setValue(java.lang.Integer value) {
            setNumber(value);
        }

            @Override
            public void increment() {
                setNumber(getNumber() + 1);
            }

            @Override
            public void decrement() {
                setNumber(getNumber() - 1);
            }

        @Override
        protected void onChangeValue(java.lang.Integer oldValue, java.lang.Integer newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- qualitys ---

    private Set<String> qualitysIds = new HashSet<String>();

    public final java.util.Set<scrum.client.project.Quality> getQualitys() {
        if ( qualitysIds.isEmpty()) return Collections.emptySet();
        return getDao().getQualitys(this.qualitysIds);
    }

    public final void setQualitys(Collection<scrum.client.project.Quality> values) {
        qualitysIds = ilarkesto.gwt.client.Gwt.getIdsAsSet(values);
        propertyChanged("qualitysIds", this.qualitysIds);
    }

    public final void addQuality(scrum.client.project.Quality quality) {
        String id = quality.getId();
        if (qualitysIds.contains(id)) return;
        qualitysIds.add(id);
        propertyChanged("qualitysIds", this.qualitysIds);
    }

    public final void removeQuality(scrum.client.project.Quality quality) {
        String id = quality.getId();
        if (!qualitysIds.contains(id)) return;
        qualitysIds.remove(id);
        propertyChanged("qualitysIds", this.qualitysIds);
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Requirement setLabel(java.lang.String label) {
        if (isLabel(label)) return (Requirement)this;
        this.label = label ;
        propertyChanged("label", this.label);
        return (Requirement)this;
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
        public boolean isEditable() { return GRequirement.this.isEditable(); }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- description ---

    private java.lang.String description ;

    public final java.lang.String getDescription() {
        return this.description ;
    }

    public final Requirement setDescription(java.lang.String description) {
        if (isDescription(description)) return (Requirement)this;
        this.description = description ;
        propertyChanged("description", this.description);
        return (Requirement)this;
    }

    public final boolean isDescription(java.lang.String description) {
        return equals(this.description, description);
    }

    private transient DescriptionModel descriptionModel;

    public DescriptionModel getDescriptionModel() {
        if (descriptionModel == null) descriptionModel = createDescriptionModel();
        return descriptionModel;
    }

    protected DescriptionModel createDescriptionModel() { return new DescriptionModel(); }

    protected class DescriptionModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getDescription();
        }

        @Override
        public void setValue(java.lang.String value) {
            setDescription(value);
        }

        @Override
        public boolean isEditable() { return GRequirement.this.isEditable(); }

        @Override
        public boolean isRichtext() { return true; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- testDescription ---

    private java.lang.String testDescription ;

    public final java.lang.String getTestDescription() {
        return this.testDescription ;
    }

    public final Requirement setTestDescription(java.lang.String testDescription) {
        if (isTestDescription(testDescription)) return (Requirement)this;
        this.testDescription = testDescription ;
        propertyChanged("testDescription", this.testDescription);
        return (Requirement)this;
    }

    public final boolean isTestDescription(java.lang.String testDescription) {
        return equals(this.testDescription, testDescription);
    }

    private transient TestDescriptionModel testDescriptionModel;

    public TestDescriptionModel getTestDescriptionModel() {
        if (testDescriptionModel == null) testDescriptionModel = createTestDescriptionModel();
        return testDescriptionModel;
    }

    protected TestDescriptionModel createTestDescriptionModel() { return new TestDescriptionModel(); }

    protected class TestDescriptionModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getTestDescription();
        }

        @Override
        public void setValue(java.lang.String value) {
            setTestDescription(value);
        }

        @Override
        public boolean isEditable() { return GRequirement.this.isEditable(); }

        @Override
        public boolean isRichtext() { return true; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- estimatedWork ---

    private java.lang.Integer estimatedWork ;

    public final java.lang.Integer getEstimatedWork() {
        return this.estimatedWork ;
    }

    public final Requirement setEstimatedWork(java.lang.Integer estimatedWork) {
        if (isEstimatedWork(estimatedWork)) return (Requirement)this;
        this.estimatedWork = estimatedWork ;
        propertyChanged("estimatedWork", this.estimatedWork);
        return (Requirement)this;
    }

    public final boolean isEstimatedWork(java.lang.Integer estimatedWork) {
        return equals(this.estimatedWork, estimatedWork);
    }

    private transient EstimatedWorkModel estimatedWorkModel;

    public EstimatedWorkModel getEstimatedWorkModel() {
        if (estimatedWorkModel == null) estimatedWorkModel = createEstimatedWorkModel();
        return estimatedWorkModel;
    }

    protected EstimatedWorkModel createEstimatedWorkModel() { return new EstimatedWorkModel(); }

    protected class EstimatedWorkModel extends ilarkesto.gwt.client.editor.AIntegerEditorModel {

        @Override
        public java.lang.Integer getValue() {
            return getEstimatedWork();
        }

        @Override
        public void setValue(java.lang.Integer value) {
            setEstimatedWork(value);
        }

            @Override
            public void increment() {
                setEstimatedWork(getEstimatedWork() + 1);
            }

            @Override
            public void decrement() {
                setEstimatedWork(getEstimatedWork() - 1);
            }

        @Override
        protected void onChangeValue(java.lang.Integer oldValue, java.lang.Integer newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- closed ---

    private boolean closed ;

    public final boolean isClosed() {
        return this.closed ;
    }

    public final Requirement setClosed(boolean closed) {
        if (isClosed(closed)) return (Requirement)this;
        this.closed = closed ;
        propertyChanged("closed", this.closed);
        return (Requirement)this;
    }

    public final boolean isClosed(boolean closed) {
        return equals(this.closed, closed);
    }

    // --- dirty ---

    private boolean dirty ;

    public final boolean isDirty() {
        return this.dirty ;
    }

    public final Requirement setDirty(boolean dirty) {
        if (isDirty(dirty)) return (Requirement)this;
        this.dirty = dirty ;
        propertyChanged("dirty", this.dirty);
        return (Requirement)this;
    }

    public final boolean isDirty(boolean dirty) {
        return equals(this.dirty, dirty);
    }

    // --- workEstimationVotingActive ---

    private boolean workEstimationVotingActive ;

    public final boolean isWorkEstimationVotingActive() {
        return this.workEstimationVotingActive ;
    }

    public final Requirement setWorkEstimationVotingActive(boolean workEstimationVotingActive) {
        if (isWorkEstimationVotingActive(workEstimationVotingActive)) return (Requirement)this;
        this.workEstimationVotingActive = workEstimationVotingActive ;
        propertyChanged("workEstimationVotingActive", this.workEstimationVotingActive);
        return (Requirement)this;
    }

    public final boolean isWorkEstimationVotingActive(boolean workEstimationVotingActive) {
        return equals(this.workEstimationVotingActive, workEstimationVotingActive);
    }

    // --- workEstimationVotingShowoff ---

    private boolean workEstimationVotingShowoff ;

    public final boolean isWorkEstimationVotingShowoff() {
        return this.workEstimationVotingShowoff ;
    }

    public final Requirement setWorkEstimationVotingShowoff(boolean workEstimationVotingShowoff) {
        if (isWorkEstimationVotingShowoff(workEstimationVotingShowoff)) return (Requirement)this;
        this.workEstimationVotingShowoff = workEstimationVotingShowoff ;
        propertyChanged("workEstimationVotingShowoff", this.workEstimationVotingShowoff);
        return (Requirement)this;
    }

    public final boolean isWorkEstimationVotingShowoff(boolean workEstimationVotingShowoff) {
        return equals(this.workEstimationVotingShowoff, workEstimationVotingShowoff);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        projectId = (String) props.get("projectId");
        sprintId = (String) props.get("sprintId");
        number  = (Integer) props.get("number");
        qualitysIds = (Set<String>) props.get("qualitysIds");
        label  = (java.lang.String) props.get("label");
        description  = (java.lang.String) props.get("description");
        testDescription  = (java.lang.String) props.get("testDescription");
        estimatedWork  = (java.lang.Integer) props.get("estimatedWork");
        closed  = (Boolean) props.get("closed");
        dirty  = (Boolean) props.get("dirty");
        workEstimationVotingActive  = (Boolean) props.get("workEstimationVotingActive");
        workEstimationVotingShowoff  = (Boolean) props.get("workEstimationVotingShowoff");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("sprintId", this.sprintId);
        properties.put("number", this.number);
        properties.put("qualitysIds", this.qualitysIds);
        properties.put("label", this.label);
        properties.put("description", this.description);
        properties.put("testDescription", this.testDescription);
        properties.put("estimatedWork", this.estimatedWork);
        properties.put("closed", this.closed);
        properties.put("dirty", this.dirty);
        properties.put("workEstimationVotingActive", this.workEstimationVotingActive);
        properties.put("workEstimationVotingShowoff", this.workEstimationVotingShowoff);
    }

    @Override
    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getLabel(), key)) return true;
        if (matchesKey(getDescription(), key)) return true;
        if (matchesKey(getTestDescription(), key)) return true;
        return false;
    }

}