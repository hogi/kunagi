









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.EntityGenerator










package scrum.server.sprint;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.logging.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.persistence.*;

public abstract class GSprint
            extends AEntity
            implements java.lang.Comparable<Sprint> {

    // --- AEntity ---

    public final SprintDao getDao() {
        return sprintDao;
    }

    protected void repairDeadValueObject(AValueObject valueObject) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("label", this.label);
        properties.put("projectId", this.projectId);
        properties.put("endCrap", this.endCrap);
        properties.put("beginCrap", this.beginCrap);
    }

    public int compareTo(Sprint other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final Logger LOG = Logger.get(GSprint.class);

    public static final String TYPE = "sprint";

    // --- copy constructor ---
    public GSprint(GSprint template) {
        super(template);
        if (template==null) return;

        setLabel(template.getLabel());
        setProject(template.getProject());
        setEndCrap(template.getEndCrap());
        setBeginCrap(template.getBeginCrap());
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
    // - endCrap
    // -----------------------------------------------------------

    private java.util.Date endCrap;

    public final java.util.Date getEndCrap() {
        return endCrap;
    }

    public final void setEndCrap(java.util.Date endCrap) {
        endCrap = prepareEndCrap(endCrap);
        if (isEndCrap(endCrap)) return;
        this.endCrap = endCrap;
        entityModified();
    }

    protected java.util.Date prepareEndCrap(java.util.Date endCrap) {
        return endCrap;
    }

    public final boolean isEndCrapSet() {
        return this.endCrap != null;
    }

    public final boolean isEndCrap(java.util.Date endCrap) {
        if (this.endCrap == null && endCrap == null) return true;
        return this.endCrap != null && this.endCrap.equals(endCrap);
    }

    // -----------------------------------------------------------
    // - beginCrap
    // -----------------------------------------------------------

    private java.util.Date beginCrap;

    public final java.util.Date getBeginCrap() {
        return beginCrap;
    }

    public final void setBeginCrap(java.util.Date beginCrap) {
        beginCrap = prepareBeginCrap(beginCrap);
        if (isBeginCrap(beginCrap)) return;
        this.beginCrap = beginCrap;
        entityModified();
    }

    protected java.util.Date prepareBeginCrap(java.util.Date beginCrap) {
        return beginCrap;
    }

    public final boolean isBeginCrapSet() {
        return this.beginCrap != null;
    }

    public final boolean isBeginCrap(java.util.Date beginCrap) {
        if (this.beginCrap == null && beginCrap == null) return true;
        return this.beginCrap != null && this.beginCrap.equals(beginCrap);
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
        GSprint.projectDao = projectDao;
    }

    protected static SprintDao sprintDao;

    public static final void setSprintDao(SprintDao sprintDao) {
        GSprint.sprintDao = sprintDao;
    }

}
