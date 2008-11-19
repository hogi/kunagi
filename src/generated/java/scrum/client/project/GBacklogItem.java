









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
    public final String getEntityType() {
        return ENTITY_TYPE;
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

    // --- sprint ---

    private String sprintId;

    public final scrum.client.sprint.Sprint getSprint() {
        return getDao().getSprint(this.sprintId);
    }

    public final BacklogItem setSprint(scrum.client.sprint.Sprint sprint) {
        this.sprintId = sprint.getId();
        propertyChanged("sprint", this.sprintId);
        return (BacklogItem)this;
    }

    public final boolean isSprint(scrum.client.sprint.Sprint sprint) {
        return equals(this.sprintId, sprint);
    }

    // --- closed ---

    private boolean closed ;

    public final boolean isClosed() {
        return this.closed ;
    }

    public final BacklogItem setClosed(boolean closed) {
        this.closed = closed ;
        propertyChanged("closed", this.closed);
        return (BacklogItem)this;
    }

    public final boolean isClosed(boolean closed) {
        return equals(this.closed, closed);
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

    // --- update properties by map ---

    public void updateProperties(Map props) {
        testDescription  = (java.lang.String) props.get("testDescription");
        sprintId = (String) props.get("sprintId");
        closed  = (Boolean) props.get("closed");
        description  = (java.lang.String) props.get("description");
        label  = (java.lang.String) props.get("label");
        effort  = (java.lang.Integer) props.get("effort");
        projectId = (String) props.get("projectId");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("testDescription", this.testDescription);
        properties.put("sprintId", this.sprintId);
        properties.put("closed", this.closed);
        properties.put("description", this.description);
        properties.put("label", this.label);
        properties.put("effort", this.effort);
        properties.put("projectId", this.projectId);
    }

}
