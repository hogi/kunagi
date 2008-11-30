









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
        properties.put("label", this.label);
        properties.put("backlogItemId", this.backlogItemId);
        properties.put("effort", this.effort);
        properties.put("notice", this.notice);
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

        setLabel(template.getLabel());
        setBacklogItem(template.getBacklogItem());
        setEffort(template.getEffort());
        setNotice(template.getNotice());
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

    // -----------------------------------------------------------
    // - backlogItem
    // -----------------------------------------------------------

    private String backlogItemId;

    public final scrum.server.project.BacklogItem getBacklogItem() {
        if (this.backlogItemId == null) return null;
        return (scrum.server.project.BacklogItem)backlogItemDao.getById(this.backlogItemId);
    }

    public final void setBacklogItem(scrum.server.project.BacklogItem backlogItem) {
        backlogItem = prepareBacklogItem(backlogItem);
        if (isBacklogItem(backlogItem)) return;
        this.backlogItemId = backlogItem == null ? null : backlogItem.getId();
        entityModified();
    }

    protected scrum.server.project.BacklogItem prepareBacklogItem(scrum.server.project.BacklogItem backlogItem) {
        return backlogItem;
    }

    protected void repairDeadBacklogItemReference(String entityId) {
        if (entityId.equals(this.backlogItemId)) {
            repairMissingMaster();
        }
    }

    public final boolean isBacklogItemSet() {
        return this.backlogItemId != null;
    }

    public final boolean isBacklogItem(scrum.server.project.BacklogItem backlogItem) {
        if (this.backlogItemId == null && backlogItem == null) return true;
        return backlogItem != null && backlogItem.getId().equals(this.backlogItemId);
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

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadBacklogItemReference(entityId);
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
        if (!isBacklogItemSet()) {
            repairMissingMaster();
            return;
        }
        try {
            getBacklogItem();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead backlogItem reference");
            repairDeadBacklogItemReference(this.backlogItemId);
        }
    }


    // -----------------------------------------------------------
    // - composites
    // -----------------------------------------------------------

    // --- dependencies ---

    protected static scrum.server.project.BacklogItemDao backlogItemDao;

    public static final void setBacklogItemDao(scrum.server.project.BacklogItemDao backlogItemDao) {
        GTask.backlogItemDao = backlogItemDao;
    }

    protected static TaskDao taskDao;

    public static final void setTaskDao(TaskDao taskDao) {
        GTask.taskDao = taskDao;
    }

}
