// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.EntityGenerator










package scrum.server.project;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GRequirement
            extends AEntity
            implements java.lang.Comparable<Requirement> {

    // --- AEntity ---

    public final RequirementDao getDao() {
        return requirementDao;
    }

    protected void repairDeadDatob(ADatob datob) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("sprintId", this.sprintId);
        properties.put("attributesIds", this.attributesIds);
        properties.put("label", this.label);
        properties.put("description", this.description);
        properties.put("testDescription", this.testDescription);
        properties.put("estimatedWork", this.estimatedWork);
        properties.put("closed", this.closed);
    }

    public int compareTo(Requirement other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final Logger LOG = Logger.get(GRequirement.class);

    public static final String TYPE = "requirement";

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
        fireModified();
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
    // - sprint
    // -----------------------------------------------------------

    private String sprintId;

    public final scrum.server.sprint.Sprint getSprint() {
        if (this.sprintId == null) return null;
        return (scrum.server.sprint.Sprint)sprintDao.getById(this.sprintId);
    }

    public final void setSprint(scrum.server.sprint.Sprint sprint) {
        sprint = prepareSprint(sprint);
        if (isSprint(sprint)) return;
        this.sprintId = sprint == null ? null : sprint.getId();
        fireModified();
    }

    protected scrum.server.sprint.Sprint prepareSprint(scrum.server.sprint.Sprint sprint) {
        return sprint;
    }

    protected void repairDeadSprintReference(String entityId) {
        if (entityId.equals(this.sprintId)) {
            this.sprintId = null;
            fireModified();
        }
    }

    public final boolean isSprintSet() {
        return this.sprintId != null;
    }

    public final boolean isSprint(scrum.server.sprint.Sprint sprint) {
        if (this.sprintId == null && sprint == null) return true;
        return sprint != null && sprint.getId().equals(this.sprintId);
    }

    // -----------------------------------------------------------
    // - attributes
    // -----------------------------------------------------------

    private java.util.Set<String> attributesIds = new java.util.HashSet<String>();

    public final java.util.Set<scrum.server.project.Attribute> getAttributes() {
        return (java.util.Set) attributeDao.getByIdsAsSet(this.attributesIds);
    }

    public final void setAttributes(java.util.Set<scrum.server.project.Attribute> attributes) {
        attributes = prepareAttributes(attributes);
        if (attributes == null) throw new IllegalArgumentException("null is not allowed");
        java.util.Set<String> ids = getIdsAsSet(attributes);
        if (this.attributesIds.equals(ids)) return;
        this.attributesIds = ids;
        fireModified();
    }

    protected java.util.Set<scrum.server.project.Attribute> prepareAttributes(java.util.Set<scrum.server.project.Attribute> attributes) {
        return attributes;
    }

    protected void repairDeadAttributeReference(String entityId) {
        if (this.attributesIds.remove(entityId)) fireModified();
    }

    public final boolean containsAttribute(scrum.server.project.Attribute attribute) {
        if (attribute == null) return false;
        return this.attributesIds.contains(attribute.getId());
    }

    public final int getAttributesCount() {
        return this.attributesIds.size();
    }

    public final boolean isAttributesEmpty() {
        return this.attributesIds.isEmpty();
    }

    public final boolean addAttribute(scrum.server.project.Attribute attribute) {
        if (attribute == null) throw new IllegalArgumentException("attribute == null");
        boolean added = this.attributesIds.add(attribute.getId());
        if (added) fireModified();
        return added;
    }

    public final boolean addAttributes(Collection<scrum.server.project.Attribute> attributes) {
        if (attributes == null) throw new IllegalArgumentException("attributes == null");
        boolean added = false;
        for (scrum.server.project.Attribute attribute : attributes) {
            added = added | this.attributesIds.add(attribute.getId());
        }
        if (added) fireModified();
        return added;
    }

    public final boolean removeAttribute(scrum.server.project.Attribute attribute) {
        if (attribute == null) throw new IllegalArgumentException("attribute == null");
        if (this.attributesIds == null) return false;
        boolean removed = this.attributesIds.remove(attribute.getId());
        if (removed) fireModified();
        return removed;
    }

    public final boolean removeAttributes(Collection<scrum.server.project.Attribute> attributes) {
        if (attributes == null) return false;
        if (attributes.isEmpty()) return false;
        boolean removed = false;
        for (scrum.server.project.Attribute _element: attributes) {
            removed = removed | removeAttribute(_element);
        }
        return removed;
    }

    public final boolean clearAttributes() {
        if (this.attributesIds.isEmpty()) return false;
        this.attributesIds.clear();
        fireModified();
        return true;
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
        fireModified();
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
        fireModified();
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
        fireModified();
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
    // - estimatedWork
    // -----------------------------------------------------------

    private java.lang.Integer estimatedWork;

    public final java.lang.Integer getEstimatedWork() {
        return estimatedWork;
    }

    public final void setEstimatedWork(java.lang.Integer estimatedWork) {
        estimatedWork = prepareEstimatedWork(estimatedWork);
        if (isEstimatedWork(estimatedWork)) return;
        this.estimatedWork = estimatedWork;
        fireModified();
    }

    protected java.lang.Integer prepareEstimatedWork(java.lang.Integer estimatedWork) {
        return estimatedWork;
    }

    public final boolean isEstimatedWorkSet() {
        return this.estimatedWork != null;
    }

    public final boolean isEstimatedWork(java.lang.Integer estimatedWork) {
        if (this.estimatedWork == null && estimatedWork == null) return true;
        return this.estimatedWork != null && this.estimatedWork.equals(estimatedWork);
    }

    // -----------------------------------------------------------
    // - closed
    // -----------------------------------------------------------

    private boolean closed;

    public final boolean isClosed() {
        return closed;
    }

    public final void setClosed(boolean closed) {
        closed = prepareClosed(closed);
        if (isClosed(closed)) return;
        this.closed = closed;
        fireModified();
    }

    protected boolean prepareClosed(boolean closed) {
        return closed;
    }

    public final boolean isClosed(boolean closed) {
        return this.closed == closed;
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadProjectReference(entityId);
        repairDeadSprintReference(entityId);
        if (this.attributesIds == null) this.attributesIds = new java.util.HashSet<String>();
        repairDeadAttributeReference(entityId);
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
        try {
            getSprint();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead sprint reference");
            repairDeadSprintReference(this.sprintId);
        }
        if (this.attributesIds == null) this.attributesIds = new java.util.HashSet<String>();
        Set<String> attributes = new HashSet<String>(this.attributesIds);
        for (String entityId : attributes) {
            try {
                attributeDao.getById(entityId);
            } catch (EntityDoesNotExistException ex) {
                LOG.info("Repairing dead attribute reference");
                repairDeadAttributeReference(entityId);
            }
        }
    }

    // --- dependencies ---

    protected static scrum.server.project.ProjectDao projectDao;

    public static final void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        GRequirement.projectDao = projectDao;
    }

    protected static scrum.server.sprint.SprintDao sprintDao;

    public static final void setSprintDao(scrum.server.sprint.SprintDao sprintDao) {
        GRequirement.sprintDao = sprintDao;
    }

    protected static scrum.server.project.AttributeDao attributeDao;

    public static final void setAttributeDao(scrum.server.project.AttributeDao attributeDao) {
        GRequirement.attributeDao = attributeDao;
    }

    protected static RequirementDao requirementDao;

    public static final void setRequirementDao(RequirementDao requirementDao) {
        GRequirement.requirementDao = requirementDao;
    }


    // -----------------------------------------------------------
    // - composites
    // -----------------------------------------------------------

}