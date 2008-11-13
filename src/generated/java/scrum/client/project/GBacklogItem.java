









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtEntityGenerator










package scrum.client.project;

import java.util.*;

public abstract class GBacklogItem
            extends scrum.client.common.AGwtEntity {

    public GBacklogItem() {
    }

    public GBacklogItem(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "backlogItem";

    @Override
    protected final String getEntityType() {
        return ENTITY_TYPE;
    }

    // --- description ---

    private java.lang.String description ;

    public final java.lang.String getDescription() {
        return this.description ;
    }

    public final BacklogItem setDescription(java.lang.String description) {
        this.description = description ;
        propertyChanged("description", this.description);
        return (BacklogItem)this;
    }

    public final boolean isDescription(java.lang.String description) {
        return equals(this.description, description);
    }

    // --- done ---

    private boolean done ;

    public final boolean isDone() {
        return this.done ;
    }

    public final BacklogItem setDone(boolean done) {
        this.done = done ;
        propertyChanged("done", this.done);
        return (BacklogItem)this;
    }

    public final boolean isDone(boolean done) {
        return equals(this.done, done);
    }

    // --- testDescription ---

    private java.lang.String testDescription ;

    public final java.lang.String getTestDescription() {
        return this.testDescription ;
    }

    public final BacklogItem setTestDescription(java.lang.String testDescription) {
        this.testDescription = testDescription ;
        propertyChanged("testDescription", this.testDescription);
        return (BacklogItem)this;
    }

    public final boolean isTestDescription(java.lang.String testDescription) {
        return equals(this.testDescription, testDescription);
    }

    // --- project ---

    private String projectId;

    public final scrum.client.project.Project getProject() {
        return getDao().getProject(this.projectId);
    }

    public final BacklogItem setProject(scrum.client.project.Project project) {
        this.projectId = project.getId();
        propertyChanged("project", this.projectId);
        return (BacklogItem)this;
    }

    public final boolean isProject(scrum.client.project.Project project) {
        return equals(this.projectId, project);
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final BacklogItem setLabel(java.lang.String label) {
        this.label = label ;
        propertyChanged("label", this.label);
        return (BacklogItem)this;
    }

    public final boolean isLabel(java.lang.String label) {
        return equals(this.label, label);
    }

    // --- effort ---

    private java.lang.Integer effort ;

    public final java.lang.Integer getEffort() {
        return this.effort ;
    }

    public final BacklogItem setEffort(java.lang.Integer effort) {
        this.effort = effort ;
        propertyChanged("effort", this.effort);
        return (BacklogItem)this;
    }

    public final boolean isEffort(java.lang.Integer effort) {
        return equals(this.effort, effort);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        description  = (java.lang.String) props.get("description");
        done  = (Boolean) props.get("done");
        testDescription  = (java.lang.String) props.get("testDescription");
        projectId = (String) props.get("id");
        label  = (java.lang.String) props.get("label");
        effort  = (java.lang.Integer) props.get("effort");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("description", this.description);
        properties.put("done", this.done);
        properties.put("testDescription", this.testDescription);
        properties.put("project", this.projectId);
        properties.put("label", this.label);
        properties.put("effort", this.effort);
    }

}
