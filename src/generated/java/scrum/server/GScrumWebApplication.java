// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.ApplicationGenerator










package scrum.server;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GScrumWebApplication
            extends ilarkesto.webapp.AWebApplication {


    // -----------------------------------------------------------
    // - composites
    // -----------------------------------------------------------

    // --- changeDao ---

    private scrum.server.journal.ChangeDao changeDao;

    public final scrum.server.journal.ChangeDao getChangeDao() {
        if (changeDao == null) {
            changeDao = createChangeDao();
            initializeChangeDao(changeDao);
        }
        return changeDao;
    }

    protected scrum.server.journal.ChangeDao createChangeDao() {
        return changeDao = ilarkesto.base.Reflect.newInstance(scrum.server.journal.ChangeDao.class);
    }

    protected void initializeChangeDao(scrum.server.journal.ChangeDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetChangeDao() {
        changeDao = null;
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

    // --- commentDao ---

    private scrum.server.collaboration.CommentDao commentDao;

    public final scrum.server.collaboration.CommentDao getCommentDao() {
        if (commentDao == null) {
            commentDao = createCommentDao();
            initializeCommentDao(commentDao);
        }
        return commentDao;
    }

    protected scrum.server.collaboration.CommentDao createCommentDao() {
        return commentDao = ilarkesto.base.Reflect.newInstance(scrum.server.collaboration.CommentDao.class);
    }

    protected void initializeCommentDao(scrum.server.collaboration.CommentDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetCommentDao() {
        commentDao = null;
    }

    // --- emoticonDao ---

    private scrum.server.collaboration.EmoticonDao emoticonDao;

    public final scrum.server.collaboration.EmoticonDao getEmoticonDao() {
        if (emoticonDao == null) {
            emoticonDao = createEmoticonDao();
            initializeEmoticonDao(emoticonDao);
        }
        return emoticonDao;
    }

    protected scrum.server.collaboration.EmoticonDao createEmoticonDao() {
        return emoticonDao = ilarkesto.base.Reflect.newInstance(scrum.server.collaboration.EmoticonDao.class);
    }

    protected void initializeEmoticonDao(scrum.server.collaboration.EmoticonDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetEmoticonDao() {
        emoticonDao = null;
    }

    // --- fileDao ---

    private scrum.server.files.FileDao fileDao;

    public final scrum.server.files.FileDao getFileDao() {
        if (fileDao == null) {
            fileDao = createFileDao();
            initializeFileDao(fileDao);
        }
        return fileDao;
    }

    protected scrum.server.files.FileDao createFileDao() {
        return fileDao = ilarkesto.base.Reflect.newInstance(scrum.server.files.FileDao.class);
    }

    protected void initializeFileDao(scrum.server.files.FileDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetFileDao() {
        fileDao = null;
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

    // --- projectEventDao ---

    private scrum.server.journal.ProjectEventDao projectEventDao;

    public final scrum.server.journal.ProjectEventDao getProjectEventDao() {
        if (projectEventDao == null) {
            projectEventDao = createProjectEventDao();
            initializeProjectEventDao(projectEventDao);
        }
        return projectEventDao;
    }

    protected scrum.server.journal.ProjectEventDao createProjectEventDao() {
        return projectEventDao = ilarkesto.base.Reflect.newInstance(scrum.server.journal.ProjectEventDao.class);
    }

    protected void initializeProjectEventDao(scrum.server.journal.ProjectEventDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetProjectEventDao() {
        projectEventDao = null;
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

    // --- projectUserConfigDao ---

    private scrum.server.admin.ProjectUserConfigDao projectUserConfigDao;

    public final scrum.server.admin.ProjectUserConfigDao getProjectUserConfigDao() {
        if (projectUserConfigDao == null) {
            projectUserConfigDao = createProjectUserConfigDao();
            initializeProjectUserConfigDao(projectUserConfigDao);
        }
        return projectUserConfigDao;
    }

    protected scrum.server.admin.ProjectUserConfigDao createProjectUserConfigDao() {
        return projectUserConfigDao = ilarkesto.base.Reflect.newInstance(scrum.server.admin.ProjectUserConfigDao.class);
    }

    protected void initializeProjectUserConfigDao(scrum.server.admin.ProjectUserConfigDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetProjectUserConfigDao() {
        projectUserConfigDao = null;
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

    // --- releaseDao ---

    private scrum.server.release.ReleaseDao releaseDao;

    public final scrum.server.release.ReleaseDao getReleaseDao() {
        if (releaseDao == null) {
            releaseDao = createReleaseDao();
            initializeReleaseDao(releaseDao);
        }
        return releaseDao;
    }

    protected scrum.server.release.ReleaseDao createReleaseDao() {
        return releaseDao = ilarkesto.base.Reflect.newInstance(scrum.server.release.ReleaseDao.class);
    }

    protected void initializeReleaseDao(scrum.server.release.ReleaseDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetReleaseDao() {
        releaseDao = null;
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
        return requirementDao = ilarkesto.base.Reflect.newInstance(scrum.server.project.RequirementDao.class);
    }

    protected void initializeRequirementDao(scrum.server.project.RequirementDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetRequirementDao() {
        requirementDao = null;
    }

    // --- requirementEstimationVoteDao ---

    private scrum.server.estimation.RequirementEstimationVoteDao requirementEstimationVoteDao;

    public final scrum.server.estimation.RequirementEstimationVoteDao getRequirementEstimationVoteDao() {
        if (requirementEstimationVoteDao == null) {
            requirementEstimationVoteDao = createRequirementEstimationVoteDao();
            initializeRequirementEstimationVoteDao(requirementEstimationVoteDao);
        }
        return requirementEstimationVoteDao;
    }

    protected scrum.server.estimation.RequirementEstimationVoteDao createRequirementEstimationVoteDao() {
        return requirementEstimationVoteDao = ilarkesto.base.Reflect.newInstance(scrum.server.estimation.RequirementEstimationVoteDao.class);
    }

    protected void initializeRequirementEstimationVoteDao(scrum.server.estimation.RequirementEstimationVoteDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetRequirementEstimationVoteDao() {
        requirementEstimationVoteDao = null;
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

    // --- simpleEventDao ---

    private scrum.server.calendar.SimpleEventDao simpleEventDao;

    public final scrum.server.calendar.SimpleEventDao getSimpleEventDao() {
        if (simpleEventDao == null) {
            simpleEventDao = createSimpleEventDao();
            initializeSimpleEventDao(simpleEventDao);
        }
        return simpleEventDao;
    }

    protected scrum.server.calendar.SimpleEventDao createSimpleEventDao() {
        return simpleEventDao = ilarkesto.base.Reflect.newInstance(scrum.server.calendar.SimpleEventDao.class);
    }

    protected void initializeSimpleEventDao(scrum.server.calendar.SimpleEventDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetSimpleEventDao() {
        simpleEventDao = null;
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

    // --- subjectDao ---

    private scrum.server.collaboration.SubjectDao subjectDao;

    public final scrum.server.collaboration.SubjectDao getSubjectDao() {
        if (subjectDao == null) {
            subjectDao = createSubjectDao();
            initializeSubjectDao(subjectDao);
        }
        return subjectDao;
    }

    protected scrum.server.collaboration.SubjectDao createSubjectDao() {
        return subjectDao = ilarkesto.base.Reflect.newInstance(scrum.server.collaboration.SubjectDao.class);
    }

    protected void initializeSubjectDao(scrum.server.collaboration.SubjectDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetSubjectDao() {
        subjectDao = null;
    }

    // --- systemConfigDao ---

    private scrum.server.admin.SystemConfigDao systemConfigDao;

    public final scrum.server.admin.SystemConfigDao getSystemConfigDao() {
        if (systemConfigDao == null) {
            systemConfigDao = createSystemConfigDao();
            initializeSystemConfigDao(systemConfigDao);
        }
        return systemConfigDao;
    }

    protected scrum.server.admin.SystemConfigDao createSystemConfigDao() {
        return systemConfigDao = ilarkesto.base.Reflect.newInstance(scrum.server.admin.SystemConfigDao.class);
    }

    protected void initializeSystemConfigDao(scrum.server.admin.SystemConfigDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetSystemConfigDao() {
        systemConfigDao = null;
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

    // --- wikipageDao ---

    private scrum.server.collaboration.WikipageDao wikipageDao;

    public final scrum.server.collaboration.WikipageDao getWikipageDao() {
        if (wikipageDao == null) {
            wikipageDao = createWikipageDao();
            initializeWikipageDao(wikipageDao);
        }
        return wikipageDao;
    }

    protected scrum.server.collaboration.WikipageDao createWikipageDao() {
        return wikipageDao = ilarkesto.base.Reflect.newInstance(scrum.server.collaboration.WikipageDao.class);
    }

    protected void initializeWikipageDao(scrum.server.collaboration.WikipageDao bean) {
        autowire(bean);
        ilarkesto.base.Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetWikipageDao() {
        wikipageDao = null;
    }

}