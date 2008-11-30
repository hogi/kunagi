









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.EntityGenerator










package scrum.server.sprint;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.logging.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.persistence.*;

public abstract class GTask
            extends AEntity
            implements java.lang.Comparable<Task> {

    // --- AEntity ---

    public final TaskDao getDao() {
        return taskDao;
    }

    protected void repairDeadValueObject(AValueObject valueObject) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("effort", this.effort);
        properties.put("storyId", this.storyId);
        properties.put("notice", this.notice);
        properties.put("label", this.label);
    }

    public int compareTo(Task other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final Logger LOG = Logger.get(GTask.class);

    public static final String TYPE = "task";

    // --- copy constructor ---
    public GTask(GTask template) {
        super(template);
        if (template==null) return;

        setEffort(template.getEffort());
        setStory(template.getStory());
        setNotice(template.getNotice());
        setLabel(template.getLabel());
    }

    // -----------------------------------------------------------
    // - effort
    // -----------------------------------------------------------

    private java.lang.Integer effort;

    public final java.lang.Integer getEffort() {
        return effort;
    }

    public final void setEffort(java.lang.Integer effort) {
        effort = prepareEffort(effort);
        if (isEffort(effort)) return;
        this.effort = effort;
        entityModified();
    }

    protected java.lang.Integer prepareEffort(java.lang.Integer effort) {
        return effort;
    }

    public final boolean isEffortSet() {
        return this.effort != null;
    }

    public final boolean isEffort(java.lang.Integer effort) {
        if (this.effort == null && effort == null) return true;
        return this.effort != null && this.effort.equals(effort);
    }

    // -----------------------------------------------------------
    // - story
    // -----------------------------------------------------------

    private String storyId;

    public final scrum.server.project.Story getStory() {
        if (this.storyId == null) return null;
        return (scrum.server.project.Story)storyDao.getById(this.storyId);
    }

    public final void setStory(scrum.server.project.Story story) {
        story = prepareStory(story);
        if (isStory(story)) return;
        this.storyId = story == null ? null : story.getId();
        entityModified();
    }

    protected scrum.server.project.Story prepareStory(scrum.server.project.Story story) {
        return story;
    }

    protected void repairDeadStoryReference(String entityId) {
        if (entityId.equals(this.storyId)) {
            repairMissingMaster();
        }
    }

    public final boolean isStorySet() {
        return this.storyId != null;
    }

    public final boolean isStory(scrum.server.project.Story story) {
        if (this.storyId == null && story == null) return true;
        return story != null && story.getId().equals(this.storyId);
    }

    // -----------------------------------------------------------
    // - notice
    // -----------------------------------------------------------

    private java.lang.String notice;

    public final java.lang.String getNotice() {
        return notice;
    }

    public final void setNotice(java.lang.String notice) {
        notice = prepareNotice(notice);
        if (isNotice(notice)) return;
        this.notice = notice;
        entityModified();
    }

    protected java.lang.String prepareNotice(java.lang.String notice) {
        notice = Str.removeUnreadableChars(notice);
        return notice;
    }

    public final boolean isNoticeSet() {
        return this.notice != null;
    }

    public final boolean isNotice(java.lang.String notice) {
        if (this.notice == null && notice == null) return true;
        return this.notice != null && this.notice.equals(notice);
    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private java.lang.String label;

    public final java.lang.String getLabel() {
        return label;
    }

    public final void setLabel(java.lang.String label) {
        label = prepareLabel(label);
        if (isLabel(label)) return;
        this.label = label;
        entityModified();
    }

    protected java.lang.String prepareLabel(java.lang.String label) {
        label = Str.removeUnreadableChars(label);
        return label;
    }

    public final boolean isLabelSet() {
        return this.label != null;
    }

    public final boolean isLabel(java.lang.String label) {
        if (this.label == null && label == null) return true;
        return this.label != null && this.label.equals(label);
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadStoryReference(entityId);
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
        if (!isStorySet()) {
            repairMissingMaster();
            return;
        }
        try {
            getStory();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead story reference");
            repairDeadStoryReference(this.storyId);
        }
    }


    // -----------------------------------------------------------
    // - composites
    // -----------------------------------------------------------

    // --- dependencies ---

    protected static scrum.server.project.StoryDao storyDao;

    public static final void setStoryDao(scrum.server.project.StoryDao storyDao) {
        GTask.storyDao = storyDao;
    }

    protected static TaskDao taskDao;

    public static final void setTaskDao(TaskDao taskDao) {
        GTask.taskDao = taskDao;
    }

}
