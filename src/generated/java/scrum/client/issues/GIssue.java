// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtEntityGenerator










package scrum.client.issues;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GIssue
            extends scrum.client.common.AScrumGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public GIssue() {
    }

    public GIssue(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "issue";

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

    public final Issue setProject(scrum.client.project.Project project) {
        String id = project == null ? null : project.getId();
        if (equals(this.projectId, id)) return (Issue) this;
        this.projectId = id;
        propertyChanged("projectId", this.projectId);
        return (Issue)this;
    }

    public final boolean isProject(scrum.client.project.Project project) {
        return equals(this.projectId, project);
    }

    // --- number ---

    private int number ;

    public final int getNumber() {
        return this.number ;
    }

    public final Issue setNumber(int number) {
        if (isNumber(number)) return (Issue)this;
        this.number = number ;
        propertyChanged("number", this.number);
        return (Issue)this;
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

    }

    // --- type ---

    private java.lang.String type ;

    public final java.lang.String getType() {
        return this.type ;
    }

    public final Issue setType(java.lang.String type) {
        if (isType(type)) return (Issue)this;
        this.type = type ;
        propertyChanged("type", this.type);
        return (Issue)this;
    }

    public abstract List<java.lang.String> getTypeOptions();

    public final boolean isType(java.lang.String type) {
        return equals(this.type, type);
    }

    private transient TypeModel typeModel;

    public TypeModel getTypeModel() {
        if (typeModel == null) typeModel = createTypeModel();
        return typeModel;
    }

    protected TypeModel createTypeModel() { return new TypeModel(); }

    protected class TypeModel extends ilarkesto.gwt.client.editor.AOptionEditorModel<java.lang.String> {

        @Override
        public java.lang.String getValue() {
            return getType();
        }

        @Override
        public void setValue(java.lang.String value) {
            setType(value);
        }

        @Override
        public List<java.lang.String> getOptions() {
            return getTypeOptions();
        }

        @Override
        public boolean isMandatory() { return true; }

    }

    // --- date ---

    private ilarkesto.gwt.client.Date date ;

    public final ilarkesto.gwt.client.Date getDate() {
        return this.date ;
    }

    public final Issue setDate(ilarkesto.gwt.client.Date date) {
        if (isDate(date)) return (Issue)this;
        this.date = date ;
        propertyChanged("date", this.date);
        return (Issue)this;
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
        public ilarkesto.gwt.client.Date getValue() {
            return getDate();
        }

        @Override
        public void setValue(ilarkesto.gwt.client.Date value) {
            setDate(value);
        }

        @Override
        public boolean isMandatory() { return true; }

    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Issue setLabel(java.lang.String label) {
        if (isLabel(label)) return (Issue)this;
        this.label = label ;
        propertyChanged("label", this.label);
        return (Issue)this;
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

    }

    // --- description ---

    private java.lang.String description ;

    public final java.lang.String getDescription() {
        return this.description ;
    }

    public final Issue setDescription(java.lang.String description) {
        if (isDescription(description)) return (Issue)this;
        this.description = description ;
        propertyChanged("description", this.description);
        return (Issue)this;
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

    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        projectId = (String) props.get("projectId");
        number  = (Integer) props.get("number");
        type  = (java.lang.String) props.get("type");
        String dateAsString = (String) props.get("date");
        date  =  dateAsString == null ? null : new ilarkesto.gwt.client.Date(dateAsString);
        label  = (java.lang.String) props.get("label");
        description  = (java.lang.String) props.get("description");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("number", this.number);
        properties.put("type", this.type);
        properties.put("date", this.date == null ? null : this.date.toString());
        properties.put("label", this.label);
        properties.put("description", this.description);
    }

}