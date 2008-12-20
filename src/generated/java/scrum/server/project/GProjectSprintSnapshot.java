









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.EntityGenerator










package scrum.server.project;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.logging.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.persistence.*;

public abstract class GProjectSprintSnapshot
            extends AEntity
            implements java.lang.Comparable<ProjectSprintSnapshot> {

    // --- AEntity ---

    public final ProjectSprintSnapshotDao getDao() {
        return projectSprintSnapshotDao;
    }

    protected void repairDeadValueObject(AValueObject valueObject) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("burndown", this.burndown);
        properties.put("effort", this.effort);
        properties.put("projectId", this.projectId);
        properties.put("dateCrap", this.dateCrap);
    }

    public int compareTo(ProjectSprintSnapshot other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final Logger LOG = Logger.get(GProjectSprintSnapshot.class);

    public static final String TYPE = "projectSprintSnapshot";

    // --- copy constructor ---
    public GProjectSprintSnapshot(GProjectSprintSnapshot template) {
        super(template);
        if (template==null) return;

        setBurndown(template.getBurndown());
        setEffort(template.getEffort());
        setProject(template.getProject());
        setDateCrap(template.getDateCrap());
    }

    // -----------------------------------------------------------
    // - burndown
    // -----------------------------------------------------------

    private int burndown;

    public final int getBurndown() {
        return burndown;
    }

    public final void setBurndown(int burndown) {
        burndown = prepareBurndown(burndown);
        if (isBurndown(burndown)) return;
        this.burndown = burndown;
        entityModified();
    }

    protected int prepareBurndown(int burndown) {
        return burndown;
    }

    public final boolean isBurndown(int burndown) {
        return this.burndown == burndown;
    }

    // -----------------------------------------------------------
    // - effort
    // -----------------------------------------------------------

    private int effort;

    public final int getEffort() {
        return effort;
    }

    public final void setEffort(int effort) {
        effort = prepareEffort(effort);
        if (isEffort(effort)) return;
        this.effort = effort;
        entityModified();
    }

    protected int prepareEffort(int effort) {
        return effort;
    }

    public final boolean isEffort(int effort) {
        return this.effort == effort;
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
            this.projectId = null;
            entityModified();
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
    // - dateCrap
    // -----------------------------------------------------------

    private java.util.Date dateCrap;

    public final java.util.Date getDateCrap() {
        return dateCrap;
    }

    public final void setDateCrap(java.util.Date dateCrap) {
        dateCrap = prepareDateCrap(dateCrap);
        if (isDateCrap(dateCrap)) return;
        this.dateCrap = dateCrap;
        entityModified();
    }

    protected java.util.Date prepareDateCrap(java.util.Date dateCrap) {
        return dateCrap;
    }

    public final boolean isDateCrapSet() {
        return this.dateCrap != null;
    }

    public final boolean isDateCrap(java.util.Date dateCrap) {
        if (this.dateCrap == null && dateCrap == null) return true;
        return this.dateCrap != null && this.dateCrap.equals(dateCrap);
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadProjectReference(entityId);
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
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
        GProjectSprintSnapshot.projectDao = projectDao;
    }

    protected static ProjectSprintSnapshotDao projectSprintSnapshotDao;

    public static final void setProjectSprintSnapshotDao(ProjectSprintSnapshotDao projectSprintSnapshotDao) {
        GProjectSprintSnapshot.projectSprintSnapshotDao = projectSprintSnapshotDao;
    }

}
