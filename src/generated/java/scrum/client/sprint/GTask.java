









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

    // --- story ---

    private String storyId;

    public final scrum.client.project.Story getStory() {
        return getDao().getStory(this.storyId);
    }

    public final Task setStory(scrum.client.project.Story story) {
        String id = story == null ? null : story.getId();
        if (equals(this.storyId, id)) return (Task) this;
        this.storyId = id;
        propertyChanged("story", this.storyId);
        return (Task)this;
    }

    public final boolean isStory(scrum.client.project.Story story) {
        return equals(this.storyId, story);
    }

    // --- notice ---

    private java.lang.String notice ;

    public final java.lang.String getNotice() {
        return this.notice ;
    }

    public final Task setNotice(java.lang.String notice) {
        this.notice = notice ;
        propertyChanged("notice", this.notice);
        return (Task)this;
    }

    public final boolean isNotice(java.lang.String notice) {
        return equals(this.notice, notice);
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

    // --- update properties by map ---

    public void updateProperties(Map props) {
        effort  = (java.lang.Integer) props.get("effort");
        storyId = (String) props.get("storyId");
        notice  = (java.lang.String) props.get("notice");
        label  = (java.lang.String) props.get("label");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("effort", this.effort);
        properties.put("storyId", this.storyId);
        properties.put("notice", this.notice);
        properties.put("label", this.label);
    }

}
