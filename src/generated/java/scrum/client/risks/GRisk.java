// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtEntityGenerator










package scrum.client.risks;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GRisk
            extends ilarkesto.gwt.client.AGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public GRisk() {
    }

    public GRisk(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "risk";

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

    public final Risk setProject(scrum.client.project.Project project) {
        String id = project == null ? null : project.getId();
        if (equals(this.projectId, id)) return (Risk) this;
        this.projectId = id;
        propertyChanged("projectId", this.projectId);
        return (Risk)this;
    }

    public final boolean isProject(scrum.client.project.Project project) {
        return equals(this.projectId, project);
    }

    // --- number ---

    private int number ;

    public final int getNumber() {
        return this.number ;
    }

    public final Risk setNumber(int number) {
        if (isNumber(number)) return (Risk)this;
        this.number = number ;
        propertyChanged("number", this.number);
        return (Risk)this;
    }

    public final boolean isNumber(int number) {
        return equals(this.number, number);
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Risk setLabel(java.lang.String label) {
        if (isLabel(label)) return (Risk)this;
        this.label = label ;
        propertyChanged("label", this.label);
        return (Risk)this;
    }

    public final boolean isLabel(java.lang.String label) {
        return equals(this.label, label);
    }

    public transient ilarkesto.gwt.client.editor.ATextPropertyEditor labelEditor = new ilarkesto.gwt.client.editor.ATextPropertyEditor() {

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

    public final Risk setDescription(java.lang.String description) {
        if (isDescription(description)) return (Risk)this;
        this.description = description ;
        propertyChanged("description", this.description);
        return (Risk)this;
    }

    public final boolean isDescription(java.lang.String description) {
        return equals(this.description, description);
    }

    public transient ilarkesto.gwt.client.editor.ATextPropertyEditor descriptionEditor = new ilarkesto.gwt.client.editor.ATextPropertyEditor() {

        @Override
        public String getValue() {
            return getDescription();
        }

        @Override
        public void setValue(String value) {
            setDescription(value);
        }

    };

    // --- mitigationPlans ---

    private java.lang.String mitigationPlans ;

    public final java.lang.String getMitigationPlans() {
        return this.mitigationPlans ;
    }

    public final Risk setMitigationPlans(java.lang.String mitigationPlans) {
        if (isMitigationPlans(mitigationPlans)) return (Risk)this;
        this.mitigationPlans = mitigationPlans ;
        propertyChanged("mitigationPlans", this.mitigationPlans);
        return (Risk)this;
    }

    public final boolean isMitigationPlans(java.lang.String mitigationPlans) {
        return equals(this.mitigationPlans, mitigationPlans);
    }

    public transient ilarkesto.gwt.client.editor.ATextPropertyEditor mitigationPlansEditor = new ilarkesto.gwt.client.editor.ATextPropertyEditor() {

        @Override
        public String getValue() {
            return getMitigationPlans();
        }

        @Override
        public void setValue(String value) {
            setMitigationPlans(value);
        }

    };

    // --- probability ---

    private int probability ;

    public final int getProbability() {
        return this.probability ;
    }

    public final Risk setProbability(int probability) {
        if (isProbability(probability)) return (Risk)this;
        this.probability = probability ;
        propertyChanged("probability", this.probability);
        return (Risk)this;
    }

    public final boolean isProbability(int probability) {
        return equals(this.probability, probability);
    }

    // --- impact ---

    private int impact ;

    public final int getImpact() {
        return this.impact ;
    }

    public final Risk setImpact(int impact) {
        if (isImpact(impact)) return (Risk)this;
        this.impact = impact ;
        propertyChanged("impact", this.impact);
        return (Risk)this;
    }

    public final boolean isImpact(int impact) {
        return equals(this.impact, impact);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        projectId = (String) props.get("projectId");
        number  = (Integer) props.get("number");
        label  = (java.lang.String) props.get("label");
        description  = (java.lang.String) props.get("description");
        mitigationPlans  = (java.lang.String) props.get("mitigationPlans");
        probability  = (Integer) props.get("probability");
        impact  = (Integer) props.get("impact");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("number", this.number);
        properties.put("label", this.label);
        properties.put("description", this.description);
        properties.put("mitigationPlans", this.mitigationPlans);
        properties.put("probability", this.probability);
        properties.put("impact", this.impact);
    }

}