









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.EntityGenerator










package scrum.server.project;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.logging.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.persistence.*;

public abstract class GBacklogItem
            extends AEntity {

    // --- AEntity ---

    public final BacklogItemDao getDao() {
        return backlogItemDao;
    }

    protected void repairDeadValueObject(AValueObject valueObject) {
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

    private static final Logger LOG = Logger.get(GBacklogItem.class);

    public static final String TYPE = "backlogItem";

    // --- copy constructor ---
    public GBacklogItem(GBacklogItem template) {
        super(template);
        if (template==null) return;

        setDescription(template.getDescription());
        setDone(template.isDone());
        setTestDescription(template.getTestDescription());
        setProject(template.getProject());
        setLabel(template.getLabel());
        setEffort(template.getEffort());
    }

    // -----------------------------------------------------------
    // - description
    // -----------------------------------------------------------

    private java.lang.String description;

    public final java.lang.String getDescription() {
        return description;
    }

    public final void setDescription(java.lang.String description) {
        description = prepareDescription(description);
        if (isDescription(description)) return;
        this.description = description;
        entityModified();
    }

    protected java.lang.String prepareDescription(java.lang.String description) {
        description = Str.removeUnreadableChars(description);
        return description;
    }

    public final boolean isDescriptionSet() {
        return this.description != null;
    }

    public final boolean isDescription(java.lang.String description) {
        if (this.description == null && description == null) return true;
        return this.description != null && this.description.equals(description);
    }

    // -----------------------------------------------------------
    // - done
    // -----------------------------------------------------------

    private boolean done;

    public final boolean isDone() {
        return done;
    }

    public final void setDone(boolean done) {
        done = prepareDone(done);
        if (isDone(done)) return;
        this.done = done;
        entityModified();
    }

    protected boolean prepareDone(boolean done) {
        return done;
    }

    public final boolean isDone(boolean done) {
        return this.done == done;
    }

    // -----------------------------------------------------------
    // - testDescription
    // -----------------------------------------------------------

    private java.lang.String testDescription;

    public final java.lang.String getTestDescription() {
        return testDescription;
    }

    public final void setTestDescription(java.lang.String testDescription) {
        testDescription = prepareTestDescription(testDescription);
        if (isTestDescription(testDescription)) return;
        this.testDescription = testDescription;
        entityModified();
    }

    protected java.lang.String prepareTestDescription(java.lang.String testDescription) {
        testDescription = Str.removeUnreadableChars(testDescription);
        return testDescription;
    }

    public final boolean isTestDescriptionSet() {
        return this.testDescription != null;
    }

    public final boolean isTestDescription(java.lang.String testDescription) {
        if (this.testDescription == null && testDescription == null) return true;
        return this.testDescription != null && this.testDescription.equals(testDescription);
    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private String projectId;

    public final scrum.server.project.Project getProject() {
        if (this.projectId == null) return null;
        return (scrum.server.project.Project)projectDao.getById(this.projectId);
    }

    public final void setProject(scrum.server.project.Project project) {
        project = prepareProject(project);
        if (isProject(project)) return;
        this.projectId = project == null ? null : project.getId();
        entityModified();
    }

    protected scrum.server.project.Project prepareProject(scrum.server.project.Project project) {
        return project;
    }

    protected void repairDeadProjectReference(String entityId) {
        if (entityId.equals(this.projectId)) {
            repairMissingMaster();
        }
    }

    public final boolean isProjectSet() {
        return this.projectId != null;
    }

    public final boolean isProject(scrum.server.project.Project project) {
        if (this.projectId == null && project == null) return true;
        return project != null && project.getId().equals(this.projectId);
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

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadProjectReference(entityId);
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
        if (!isProjectSet()) {
            repairMissingMaster();
            return;
        }
        try {
            getProject();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead project reference");
            repairDeadProjectReference(this.projectId);
        }
    }


    // -----------------------------------------------------------
    // - composites
    // -----------------------------------------------------------

    // --- dependencies ---

    protected static scrum.server.project.ProjectDao projectDao;

    public static final void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        GBacklogItem.projectDao = projectDao;
    }

    protected static BacklogItemDao backlogItemDao;

    public static final void setBacklogItemDao(BacklogItemDao backlogItemDao) {
        GBacklogItem.backlogItemDao = backlogItemDao;
    }

}
