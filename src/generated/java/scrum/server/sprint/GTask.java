









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
        properties.put("requirementId", this.requirementId);
        properties.put("notice", this.notice);
        properties.put("label", this.label);
        properties.put("burnedWork", this.burnedWork);
        properties.put("remainingWork", this.remainingWork);
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

        setRequirement(template.getRequirement());
        setNotice(template.getNotice());
        setLabel(template.getLabel());
        setBurnedWork(template.getBurnedWork());
        setRemainingWork(template.getRemainingWork());
    }

    // -----------------------------------------------------------
    // - requirement
    // -----------------------------------------------------------

    private String requirementId;

    public final scrum.server.project.Requirement getRequirement() {
        if (this.requirementId == null) return null;
        return (scrum.server.project.Requirement)requirementDao.getById(this.requirementId);
    }

    public final void setRequirement(scrum.server.project.Requirement requirement) {
        requirement = prepareRequirement(requirement);
        if (isRequirement(requirement)) return;
        this.requirementId = requirement == null ? null : requirement.getId();
        entityModified();
    }

    protected scrum.server.project.Requirement prepareRequirement(scrum.server.project.Requirement requirement) {
        return requirement;
    }

    protected void repairDeadRequirementReference(String entityId) {
        if (entityId.equals(this.requirementId)) {
            repairMissingMaster();
        }
    }

    public final boolean isRequirementSet() {
        return this.requirementId != null;
    }

    public final boolean isRequirement(scrum.server.project.Requirement requirement) {
        if (this.requirementId == null && requirement == null) return true;
        return requirement != null && requirement.getId().equals(this.requirementId);
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

    // -----------------------------------------------------------
    // - burnedWork
    // -----------------------------------------------------------

    private int burnedWork;

    public final int getBurnedWork() {
        return burnedWork;
    }

    public final void setBurnedWork(int burnedWork) {
        burnedWork = prepareBurnedWork(burnedWork);
        if (isBurnedWork(burnedWork)) return;
        this.burnedWork = burnedWork;
        entityModified();
    }

    protected int prepareBurnedWork(int burnedWork) {
        return burnedWork;
    }

    public final boolean isBurnedWork(int burnedWork) {
        return this.burnedWork == burnedWork;
    }

    // -----------------------------------------------------------
    // - remainingWork
    // -----------------------------------------------------------

    private int remainingWork;

    public final int getRemainingWork() {
        return remainingWork;
    }

    public final void setRemainingWork(int remainingWork) {
        remainingWork = prepareRemainingWork(remainingWork);
        if (isRemainingWork(remainingWork)) return;
        this.remainingWork = remainingWork;
        entityModified();
    }

    protected int prepareRemainingWork(int remainingWork) {
        return remainingWork;
    }

    public final boolean isRemainingWork(int remainingWork) {
        return this.remainingWork == remainingWork;
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadRequirementReference(entityId);
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
        if (!isRequirementSet()) {
            repairMissingMaster();
            return;
        }
        try {
            getRequirement();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead requirement reference");
            repairDeadRequirementReference(this.requirementId);
        }
    }


    // -----------------------------------------------------------
    // - composites
    // -----------------------------------------------------------

    // --- dependencies ---

    protected static scrum.server.project.RequirementDao requirementDao;

    public static final void setRequirementDao(scrum.server.project.RequirementDao requirementDao) {
        GTask.requirementDao = requirementDao;
    }

    protected static TaskDao taskDao;

    public static final void setTaskDao(TaskDao taskDao) {
        GTask.taskDao = taskDao;
    }

}
