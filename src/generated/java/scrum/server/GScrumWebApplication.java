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


    // -----------------------------------------------------------
    // - composites
    // -----------------------------------------------------------

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
        return requirementDao = ilarkesto.base.Reflect.newInstance(scrum.server.project.RequirementDao.class);
    }

    protected void initializeRequirementDao(scrum.server.project.RequirementDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetRequirementDao() {
        requirementDao = null;
    }

    // --- qualityDao ---

    private scrum.server.project.QualityDao qualityDao;

    public final scrum.server.project.QualityDao getQualityDao() {
        if (qualityDao == null) {
            qualityDao = createQualityDao();
            initializeQualityDao(qualityDao);
        }
        return qualityDao;
    }

    protected scrum.server.project.QualityDao createQualityDao() {
        return qualityDao = ilarkesto.base.Reflect.newInstance(scrum.server.project.QualityDao.class);
    }

    protected void initializeQualityDao(scrum.server.project.QualityDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetQualityDao() {
        qualityDao = null;
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
        return taskDao = ilarkesto.base.Reflect.newInstance(scrum.server.sprint.TaskDao.class);
    }

    protected void initializeTaskDao(scrum.server.sprint.TaskDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetTaskDao() {
        taskDao = null;
    }

    // --- chatMessageDao ---

    private scrum.server.collaboration.ChatMessageDao chatMessageDao;

    public final scrum.server.collaboration.ChatMessageDao getChatMessageDao() {
        if (chatMessageDao == null) {
            chatMessageDao = createChatMessageDao();
            initializeChatMessageDao(chatMessageDao);
        }
        return chatMessageDao;
    }

    protected scrum.server.collaboration.ChatMessageDao createChatMessageDao() {
        return chatMessageDao = ilarkesto.base.Reflect.newInstance(scrum.server.collaboration.ChatMessageDao.class);
    }

    protected void initializeChatMessageDao(scrum.server.collaboration.ChatMessageDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetChatMessageDao() {
        chatMessageDao = null;
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
        return sprintDao = ilarkesto.base.Reflect.newInstance(scrum.server.sprint.SprintDao.class);
    }

    protected void initializeSprintDao(scrum.server.sprint.SprintDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetSprintDao() {
        sprintDao = null;
    }

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
        return impedimentDao = ilarkesto.base.Reflect.newInstance(scrum.server.impediments.ImpedimentDao.class);
    }

    protected void initializeImpedimentDao(scrum.server.impediments.ImpedimentDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetImpedimentDao() {
        impedimentDao = null;
    }

    // --- issueDao ---

    private scrum.server.issues.IssueDao issueDao;

    public final scrum.server.issues.IssueDao getIssueDao() {
        if (issueDao == null) {
            issueDao = createIssueDao();
            initializeIssueDao(issueDao);
        }
        return issueDao;
    }

    protected scrum.server.issues.IssueDao createIssueDao() {
        return issueDao = ilarkesto.base.Reflect.newInstance(scrum.server.issues.IssueDao.class);
    }

    protected void initializeIssueDao(scrum.server.issues.IssueDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetIssueDao() {
        issueDao = null;
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
        return sprintDaySnapshotDao = ilarkesto.base.Reflect.newInstance(scrum.server.sprint.SprintDaySnapshotDao.class);
    }

    protected void initializeSprintDaySnapshotDao(scrum.server.sprint.SprintDaySnapshotDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetSprintDaySnapshotDao() {
        sprintDaySnapshotDao = null;
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
        return projectDao = ilarkesto.base.Reflect.newInstance(scrum.server.project.ProjectDao.class);
    }

    protected void initializeProjectDao(scrum.server.project.ProjectDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetProjectDao() {
        projectDao = null;
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
        return projectSprintSnapshotDao = ilarkesto.base.Reflect.newInstance(scrum.server.project.ProjectSprintSnapshotDao.class);
    }

    protected void initializeProjectSprintSnapshotDao(scrum.server.project.ProjectSprintSnapshotDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetProjectSprintSnapshotDao() {
        projectSprintSnapshotDao = null;
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
        return riskDao = ilarkesto.base.Reflect.newInstance(scrum.server.risks.RiskDao.class);
    }

    protected void initializeRiskDao(scrum.server.risks.RiskDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetRiskDao() {
        riskDao = null;
    }

}