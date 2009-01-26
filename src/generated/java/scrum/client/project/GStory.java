









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtEntityGenerator










package scrum.client.project;

import java.util.*;

public abstract class GStory
            extends scrum.client.common.AGwtEntity {

    public GStory() {
    }

    public GStory(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "story";

    @Override
    public final String getEntityType() {
        return ENTITY_TYPE;
    }

    // --- closed ---

    private boolean closed ;

    public final boolean isClosed() {
        return this.closed ;
    }

    public final Story setClosed(boolean closed) {
        this.closed = closed ;
        propertyChanged("closed", this.closed);
        return (Story)this;
    }

    public final boolean isClosed(boolean closed) {
        return equals(this.closed, closed);
    }

    // --- sprint ---

    private String sprintId;

    public final scrum.client.sprint.Sprint getSprint() {
        return getDao().getSprint(this.sprintId);
    }

    public final Story setSprint(scrum.client.sprint.Sprint sprint) {
        String id = sprint == null ? null : sprint.getId();
        if (equals(this.sprintId, id)) return (Story) this;
        this.sprintId = id;
        propertyChanged("sprint", this.sprintId);
        return (Story)this;
    }

    public final boolean isSprint(scrum.client.sprint.Sprint sprint) {
        return equals(this.sprintId, sprint);
    }

    // --- project ---

    private String projectId;

    public final scrum.client.project.Project getProject() {
        return getDao().getProject(this.projectId);
    }

    public final Story setProject(scrum.client.project.Project project) {
        String id = project == null ? null : project.getId();
        if (equals(this.projectId, id)) return (Story) this;
        this.projectId = id;
        propertyChanged("project", this.projectId);
        return (Story)this;
    }

    public final boolean isProject(scrum.client.project.Project project) {
        return equals(this.projectId, project);
    }

    // --- estimatedWork ---

    private java.lang.Integer estimatedWork ;

    public final java.lang.Integer getEstimatedWork() {
        return this.estimatedWork ;
    }

    public final Story setEstimatedWork(java.lang.Integer estimatedWork) {
        this.estimatedWork = estimatedWork ;
        propertyChanged("estimatedWork", this.estimatedWork);
        return (Story)this;
    }

    public final boolean isEstimatedWork(java.lang.Integer estimatedWork) {
        return equals(this.estimatedWork, estimatedWork);
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Story setLabel(java.lang.String label) {
        this.label = label ;
        propertyChanged("label", this.label);
        return (Story)this;
    }

    public final boolean isLabel(java.lang.String label) {
        return equals(this.label, label);
    }

    // --- description ---

    private java.lang.String description ;

    public final java.lang.String getDescription() {
        return this.description ;
    }

    public final Story setDescription(java.lang.String description) {
        this.description = description ;
        propertyChanged("description", this.description);
        return (Story)this;
    }

    public final boolean isDescription(java.lang.String description) {
        return equals(this.description, description);
    }

    // --- testDescription ---

    private java.lang.String testDescription ;

    public final java.lang.String getTestDescription() {
        return this.testDescription ;
    }

    public final Story setTestDescription(java.lang.String testDescription) {
        this.testDescription = testDescription ;
        propertyChanged("testDescription", this.testDescription);
        return (Story)this;
    }

    public final boolean isTestDescription(java.lang.String testDescription) {
        return equals(this.testDescription, testDescription);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        closed  = (Boolean) props.get("closed");
        sprintId = (String) props.get("sprintId");
        projectId = (String) props.get("projectId");
        estimatedWork  = (java.lang.Integer) props.get("estimatedWork");
        label  = (java.lang.String) props.get("label");
        description  = (java.lang.String) props.get("description");
        testDescription  = (java.lang.String) props.get("testDescription");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("closed", this.closed);
        properties.put("sprintId", this.sprintId);
        properties.put("projectId", this.projectId);
        properties.put("estimatedWork", this.estimatedWork);
        properties.put("label", this.label);
        properties.put("description", this.description);
        properties.put("testDescription", this.testDescription);
    }

}
