









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtEntityGenerator










package scrum.client.sprint;

import java.util.*;

public abstract class GTask
            extends scrum.client.common.AGwtEntity {

    public GTask() {
    }

    public GTask(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "task";

    @Override
    public final String getEntityType() {
        return ENTITY_TYPE;
    }

    // --- effort ---

    private java.lang.Integer effort ;

    public final java.lang.Integer getEffort() {
        return this.effort ;
    }

    public final Task setEffort(java.lang.Integer effort) {
        this.effort = effort ;
        propertyChanged("effort", this.effort);
        return (Task)this;
    }

    public final boolean isEffort(java.lang.Integer effort) {
        return equals(this.effort, effort);
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Task setLabel(java.lang.String label) {
        this.label = label ;
        propertyChanged("label", this.label);
        return (Task)this;
    }

    public final boolean isLabel(java.lang.String label) {
        return equals(this.label, label);
    }

    // --- backlogItem ---

    private String backlogItemId;

    public final scrum.client.project.BacklogItem getBacklogItem() {
        return getDao().getBacklogItem(this.backlogItemId);
    }

    public final Task setBacklogItem(scrum.client.project.BacklogItem backlogItem) {
        this.backlogItemId = backlogItem.getId();
        propertyChanged("backlogItem", this.backlogItemId);
        return (Task)this;
    }

    public final boolean isBacklogItem(scrum.client.project.BacklogItem backlogItem) {
        return equals(this.backlogItemId, backlogItem);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        effort  = (java.lang.Integer) props.get("effort");
        label  = (java.lang.String) props.get("label");
        backlogItemId = (String) props.get("backlogItemId");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("effort", this.effort);
        properties.put("label", this.label);
        properties.put("backlogItem", this.backlogItemId);
    }

}
