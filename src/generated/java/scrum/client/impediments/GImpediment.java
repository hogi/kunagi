// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtEntityGenerator










package scrum.client.impediments;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GImpediment
            extends scrum.client.common.AScrumGwtEntity {

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

    // --- number ---

    private int number ;

    public final int getNumber() {
        return this.number ;
    }

    public final Impediment setNumber(int number) {
        if (isNumber(number)) return (Impediment)this;
        this.number = number ;
        propertyChanged("number", this.number);
        return (Impediment)this;
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
        public String getId() {
            return "Impediment_number";
        }

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

    private transient LabelModel labelModel;

    public LabelModel getLabelModel() {
        if (labelModel == null) labelModel = createLabelModel();
        return labelModel;
    }

    protected LabelModel createLabelModel() { return new LabelModel(); }

    protected class LabelModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public String getId() {
            return "Impediment_label";
        }

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
        public String getTooltip() { return "The label should be short (as it appears where the Impediment is referenced),yet give a hint strong enough to make the content of it come to mind."; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

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

    private transient DateModel dateModel;

    public DateModel getDateModel() {
        if (dateModel == null) dateModel = createDateModel();
        return dateModel;
    }

    protected DateModel createDateModel() { return new DateModel(); }

    protected class DateModel extends ilarkesto.gwt.client.editor.ADateEditorModel {

        @Override
        public String getId() {
            return "Impediment_date";
        }

        @Override
        public ilarkesto.gwt.client.Date getValue() {
            return getDate();
        }

        @Override
        public void setValue(ilarkesto.gwt.client.Date value) {
            setDate(value);
        }

        @Override
        public boolean isMandatory() { return true; }
        @Override
        public String getTooltip() { return "The date the Impediment came up."; }

        @Override
        protected void onChangeValue(ilarkesto.gwt.client.Date oldValue, ilarkesto.gwt.client.Date newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

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

    private transient DescriptionModel descriptionModel;

    public DescriptionModel getDescriptionModel() {
        if (descriptionModel == null) descriptionModel = createDescriptionModel();
        return descriptionModel;
    }

    protected DescriptionModel createDescriptionModel() { return new DescriptionModel(); }

    protected class DescriptionModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public String getId() {
            return "Impediment_description";
        }

        @Override
        public java.lang.String getValue() {
            return getDescription();
        }

        @Override
        public void setValue(java.lang.String value) {
            setDescription(value);
        }

        @Override
        public boolean isRichtext() { return true; }
        @Override
        public String getTooltip() { return "The detailed description of the Impediment that explains problems,states who is affected and how, gives background information and solution hints."; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

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

    private transient SolutionModel solutionModel;

    public SolutionModel getSolutionModel() {
        if (solutionModel == null) solutionModel = createSolutionModel();
        return solutionModel;
    }

    protected SolutionModel createSolutionModel() { return new SolutionModel(); }

    protected class SolutionModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public String getId() {
            return "Impediment_solution";
        }

        @Override
        public java.lang.String getValue() {
            return getSolution();
        }

        @Override
        public void setValue(java.lang.String value) {
            setSolution(value);
        }

        @Override
        public boolean isRichtext() { return true; }
        @Override
        public String getTooltip() { return "As soon as a solution is found, it can be documented here, so that people affected byor holding stake in the Impediment can read it up."; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

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
        number  = (Integer) props.get("number");
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
        properties.put("number", this.number);
        properties.put("label", this.label);
        properties.put("date", this.date == null ? null : this.date.toString());
        properties.put("description", this.description);
        properties.put("solution", this.solution);
        properties.put("closed", this.closed);
    }

    @Override
    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getLabel(), key)) return true;
        if (matchesKey(getDescription(), key)) return true;
        if (matchesKey(getSolution(), key)) return true;
        return false;
    }

}