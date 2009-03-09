// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.ApplicationGenerator










package scrum.server;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GScrumWebApplication
            extends ilarkesto.webapp.AWebApplication {

    // --- dependencies ---


    // -----------------------------------------------------------
    // - composites
    // -----------------------------------------------------------

    // --- impedimentDao ---

    private scrum.server.impediments.ImpedimentDao impedimentDao;

    public final scrum.server.impediments.ImpedimentDao getImpedimentDao() {
        if (impedimentDao == null) {
            impedimentDao = createImpedimentDao();
            initializeImpedimentDao(impedimentDao);
        }
        return impedimentDao;
    }

    protected scrum.server.impediments.ImpedimentDao createImpedimentDao() {
        return impedimentDao = new scrum.server.impediments.ImpedimentDao();
    }

    protected void initializeImpedimentDao(scrum.server.impediments.ImpedimentDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetImpedimentDao() {
        impedimentDao = null;
    }

    // --- sprintDaySnapshotDao ---

    private scrum.server.sprint.SprintDaySnapshotDao sprintDaySnapshotDao;

    public final scrum.server.sprint.SprintDaySnapshotDao getSprintDaySnapshotDao() {
        if (sprintDaySnapshotDao == null) {
            sprintDaySnapshotDao = createSprintDaySnapshotDao();
            initializeSprintDaySnapshotDao(sprintDaySnapshotDao);
        }
        return sprintDaySnapshotDao;
    }

    protected scrum.server.sprint.SprintDaySnapshotDao createSprintDaySnapshotDao() {
        return sprintDaySnapshotDao = new scrum.server.sprint.SprintDaySnapshotDao();
    }

    protected void initializeSprintDaySnapshotDao(scrum.server.sprint.SprintDaySnapshotDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetSprintDaySnapshotDao() {
        sprintDaySnapshotDao = null;
    }

    // --- attributeDao ---

    private scrum.server.project.AttributeDao attributeDao;

    public final scrum.server.project.AttributeDao getAttributeDao() {
        if (attributeDao == null) {
            attributeDao = createAttributeDao();
            initializeAttributeDao(attributeDao);
        }
        return attributeDao;
    }

    protected scrum.server.project.AttributeDao createAttributeDao() {
        return attributeDao = new scrum.server.project.AttributeDao();
    }

    protected void initializeAttributeDao(scrum.server.project.AttributeDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetAttributeDao() {
        attributeDao = null;
    }

    // --- taskDao ---

    private scrum.server.sprint.TaskDao taskDao;

    public final scrum.server.sprint.TaskDao getTaskDao() {
        if (taskDao == null) {
            taskDao = createTaskDao();
            initializeTaskDao(taskDao);
        }
        return taskDao;
    }

    protected scrum.server.sprint.TaskDao createTaskDao() {
        return taskDao = new scrum.server.sprint.TaskDao();
    }

    protected void initializeTaskDao(scrum.server.sprint.TaskDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetTaskDao() {
        taskDao = null;
    }

    // --- riskDao ---

    private scrum.server.risks.RiskDao riskDao;

    public final scrum.server.risks.RiskDao getRiskDao() {
        if (riskDao == null) {
            riskDao = createRiskDao();
            initializeRiskDao(riskDao);
        }
        return riskDao;
    }

    protected scrum.server.risks.RiskDao createRiskDao() {
        return riskDao = new scrum.server.risks.RiskDao();
    }

    protected void initializeRiskDao(scrum.server.risks.RiskDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetRiskDao() {
        riskDao = null;
    }

    // --- projectSprintSnapshotDao ---

    private scrum.server.project.ProjectSprintSnapshotDao projectSprintSnapshotDao;

    public final scrum.server.project.ProjectSprintSnapshotDao getProjectSprintSnapshotDao() {
        if (projectSprintSnapshotDao == null) {
            projectSprintSnapshotDao = createProjectSprintSnapshotDao();
            initializeProjectSprintSnapshotDao(projectSprintSnapshotDao);
        }
        return projectSprintSnapshotDao;
    }

    protected scrum.server.project.ProjectSprintSnapshotDao createProjectSprintSnapshotDao() {
        return projectSprintSnapshotDao = new scrum.server.project.ProjectSprintSnapshotDao();
    }

    protected void initializeProjectSprintSnapshotDao(scrum.server.project.ProjectSprintSnapshotDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetProjectSprintSnapshotDao() {
        projectSprintSnapshotDao = null;
    }

    // --- sprintDao ---

    private scrum.server.sprint.SprintDao sprintDao;

    public final scrum.server.sprint.SprintDao getSprintDao() {
        if (sprintDao == null) {
            sprintDao = createSprintDao();
            initializeSprintDao(sprintDao);
        }
        return sprintDao;
    }

    protected scrum.server.sprint.SprintDao createSprintDao() {
        return sprintDao = new scrum.server.sprint.SprintDao();
    }

    protected void initializeSprintDao(scrum.server.sprint.SprintDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetSprintDao() {
        sprintDao = null;
    }

    // --- projectDao ---

    private scrum.server.project.ProjectDao projectDao;

    public final scrum.server.project.ProjectDao getProjectDao() {
        if (projectDao == null) {
            projectDao = createProjectDao();
            initializeProjectDao(projectDao);
        }
        return projectDao;
    }

    protected scrum.server.project.ProjectDao createProjectDao() {
        return projectDao = new scrum.server.project.ProjectDao();
    }

    protected void initializeProjectDao(scrum.server.project.ProjectDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetProjectDao() {
        projectDao = null;
    }

    // --- requirementDao ---

    private scrum.server.project.RequirementDao requirementDao;

    public final scrum.server.project.RequirementDao getRequirementDao() {
        if (requirementDao == null) {
            requirementDao = createRequirementDao();
            initializeRequirementDao(requirementDao);
        }
        return requirementDao;
    }

    protected scrum.server.project.RequirementDao createRequirementDao() {
        return requirementDao = new scrum.server.project.RequirementDao();
    }

    protected void initializeRequirementDao(scrum.server.project.RequirementDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetRequirementDao() {
        requirementDao = null;
    }

}