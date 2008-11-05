









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: scrum.mda.GwtBeanGenerator










package scrum.client.project;

import java.util.*;

public abstract class GBacklogItem
            extends scrum.client.common.AEntity {

    public GBacklogItem() {}

    public GBacklogItem(Map data) {
        super(data);
    }

    // --- done ---

    private boolean done ;

    public final boolean isDone() {
        return this.done ;
    }

    public final BacklogItem setDone(boolean done) {
        this.done = done ;
        return (BacklogItem)this;
    }

    // --- description ---

    private java.lang.String description ;

    public final java.lang.String getDescription() {
        return this.description ;
    }

    public final BacklogItem setDescription(java.lang.String description) {
        this.description = description ;
        return (BacklogItem)this;
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final BacklogItem setLabel(java.lang.String label) {
        this.label = label ;
        return (BacklogItem)this;
    }

    // --- project ---

    private String projectId;

    // --- effort ---

    private java.lang.Integer effort ;

    public final java.lang.Integer getEffort() {
        return this.effort ;
    }

    public final BacklogItem setEffort(java.lang.Integer effort) {
        this.effort = effort ;
        return (BacklogItem)this;
    }

    // --- testDescription ---

    private java.lang.String testDescription ;

    public final java.lang.String getTestDescription() {
        return this.testDescription ;
    }

    public final BacklogItem setTestDescription(java.lang.String testDescription) {
        this.testDescription = testDescription ;
        return (BacklogItem)this;
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        done  = (Boolean) props.get("done");
        description  = (java.lang.String) props.get("description");
        label  = (java.lang.String) props.get("label");
        projectId = (String) props.get("id");
        effort  = (java.lang.Integer) props.get("effort");
        testDescription  = (java.lang.String) props.get("testDescription");
    }

}
