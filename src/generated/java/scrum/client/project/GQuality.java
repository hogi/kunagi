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

public abstract class GQuality
            extends scrum.client.common.AScrumGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public GQuality() {
    }

    public GQuality(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "quality";

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

    public final Quality setProject(scrum.client.project.Project project) {
        String id = project == null ? null : project.getId();
        if (equals(this.projectId, id)) return (Quality) this;
        this.projectId = id;
        propertyChanged("projectId", this.projectId);
        return (Quality)this;
    }

    public final boolean isProject(scrum.client.project.Project project) {
        return equals(this.projectId, project);
    }

    // --- number ---

    private int number ;

    public final int getNumber() {
        return this.number ;
    }

    public final Quality setNumber(int number) {
        if (isNumber(number)) return (Quality)this;
        this.number = number ;
        propertyChanged("number", this.number);
        return (Quality)this;
    }

    public final boolean isNumber(int number) {
        return equals(this.number, number);
    }

    public transient ilarkesto.gwt.client.editor.AIntegerEditorModel numberModel = new ilarkesto.gwt.client.editor.AIntegerEditorModel() {

        @Override
        public Integer getValue() {
            return getNumber();
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
        public void setValue(Integer value) {
            setNumber(value);
        }

    };

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Quality setLabel(java.lang.String label) {
        if (isLabel(label)) return (Quality)this;
        this.label = label ;
        propertyChanged("label", this.label);
        return (Quality)this;
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

    // --- description ---

    private java.lang.String description ;

    public final java.lang.String getDescription() {
        return this.description ;
    }

    public final Quality setDescription(java.lang.String description) {
        if (isDescription(description)) return (Quality)this;
        this.description = description ;
        propertyChanged("description", this.description);
        return (Quality)this;
    }

    public final boolean isDescription(java.lang.String description) {
        return equals(this.description, description);
    }

    public transient ilarkesto.gwt.client.editor.ATextEditorModel descriptionModel = new ilarkesto.gwt.client.editor.ATextEditorModel() {

        @Override
        public String getValue() {
            return getDescription();
        }

        @Override
        public void setValue(String value) {
            setDescription(value);
        }

    };

    // --- testDescription ---

    private java.lang.String testDescription ;

    public final java.lang.String getTestDescription() {
        return this.testDescription ;
    }

    public final Quality setTestDescription(java.lang.String testDescription) {
        if (isTestDescription(testDescription)) return (Quality)this;
        this.testDescription = testDescription ;
        propertyChanged("testDescription", this.testDescription);
        return (Quality)this;
    }

    public final boolean isTestDescription(java.lang.String testDescription) {
        return equals(this.testDescription, testDescription);
    }

    public transient ilarkesto.gwt.client.editor.ATextEditorModel testDescriptionModel = new ilarkesto.gwt.client.editor.ATextEditorModel() {

        @Override
        public String getValue() {
            return getTestDescription();
        }

        @Override
        public void setValue(String value) {
            setTestDescription(value);
        }

    };

    // --- update properties by map ---

    public void updateProperties(Map props) {
        projectId = (String) props.get("projectId");
        number  = (Integer) props.get("number");
        label  = (java.lang.String) props.get("label");
        description  = (java.lang.String) props.get("description");
        testDescription  = (java.lang.String) props.get("testDescription");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("number", this.number);
        properties.put("label", this.label);
        properties.put("description", this.description);
        properties.put("testDescription", this.testDescription);
    }

}