// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtEntityGenerator










package scrum.client.risks;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.gwt.client.*;
import ilarkesto.logging.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.persistence.*;
import scrum.client.common.*;

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

    // --- probability ---

    private int probability ;

    public final int getProbability() {
        return this.probability ;
    }

    public final Risk setProbability(int probability) {
        this.probability = probability ;
        propertyChanged("probability", this.probability);
        return (Risk)this;
    }

    public final boolean isProbability(int probability) {
        return equals(this.probability, probability);
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Risk setLabel(java.lang.String label) {
        this.label = label ;
        propertyChanged("label", this.label);
        return (Risk)this;
    }

    public final boolean isLabel(java.lang.String label) {
        return equals(this.label, label);
    }

    // --- project ---

    private String projectId;

    public final scrum.client.project.Project getProject() {
        if (projectId == null) return null;
        return getDao().getProject(this.projectId);
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

    // --- description ---

    private java.lang.String description ;

    public final java.lang.String getDescription() {
        return this.description ;
    }

    public final Risk setDescription(java.lang.String description) {
        this.description = description ;
        propertyChanged("description", this.description);
        return (Risk)this;
    }

    public final boolean isDescription(java.lang.String description) {
        return equals(this.description, description);
    }

    // --- impact ---

    private int impact ;

    public final int getImpact() {
        return this.impact ;
    }

    public final Risk setImpact(int impact) {
        this.impact = impact ;
        propertyChanged("impact", this.impact);
        return (Risk)this;
    }

    public final boolean isImpact(int impact) {
        return equals(this.impact, impact);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        probability  = (Integer) props.get("probability");
        label  = (java.lang.String) props.get("label");
        projectId = (String) props.get("projectId");
        description  = (java.lang.String) props.get("description");
        impact  = (Integer) props.get("impact");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("probability", this.probability);
        properties.put("label", this.label);
        properties.put("projectId", this.projectId);
        properties.put("description", this.description);
        properties.put("impact", this.impact);
    }

}