









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: scrum.mda.GwtBeanGenerator










package scrum.client.project;

import java.util.*;

public abstract class GBacklogItem
            extends scrum.client.common.AEntity {

    public GBacklogItem() {}

    public GBacklogItem(Map data) {
        super(data);
        updateProperties(data);
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final BacklogItem setLabel(java.lang.String label) {
        this.label = label ;
        propertyChanged("label", label);
        return (BacklogItem)this;
    }

    // --- effort ---

    private java.lang.Integer effort ;

    public final java.lang.Integer getEffort() {
        return this.effort ;
    }

    public final BacklogItem setEffort(java.lang.Integer effort) {
        this.effort = effort ;
        propertyChanged("effort", toString(effort));
        return (BacklogItem)this;
    }

    // --- description ---

    private java.lang.String description ;

    public final java.lang.String getDescription() {
        return this.description ;
    }

    public final BacklogItem setDescription(java.lang.String description) {
        this.description = description ;
        propertyChanged("description", description);
        return (BacklogItem)this;
    }

    // --- project ---

    private String projectId;

    // --- done ---

    private boolean done ;

    public final boolean isDone() {
        return this.done ;
    }

    public final BacklogItem setDone(boolean done) {
        this.done = done ;
        propertyChanged("done", toString(done));
        return (BacklogItem)this;
    }

    // --- testDescription ---

    private java.lang.String testDescription ;

    public final java.lang.String getTestDescription() {
        return this.testDescription ;
    }

    public final BacklogItem setTestDescription(java.lang.String testDescription) {
        this.testDescription = testDescription ;
        propertyChanged("testDescription", testDescription);
        return (BacklogItem)this;
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        label  = (java.lang.String) props.get("label");
        effort  = (java.lang.Integer) props.get("effort");
        description  = (java.lang.String) props.get("description");
        projectId = (String) props.get("id");
        done  = (Boolean) props.get("done");
        testDescription  = (java.lang.String) props.get("testDescription");
    }

}
