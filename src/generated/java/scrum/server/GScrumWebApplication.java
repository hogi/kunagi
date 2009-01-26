









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.ApplicationGenerator










package scrum.server;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.logging.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.persistence.*;

public abstract class GScrumWebApplication
            extends ilarkesto.ui.web.AWebApplication {

    private static final Logger LOG = Logger.get(GScrumWebApplication.class);

    public static final String TYPE = "scrumWebApplication";

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
    }


    // -----------------------------------------------------------
    // - composites
    // -----------------------------------------------------------

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
        Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetSprintDaySnapshotDao() {
        sprintDaySnapshotDao = null;
    }

    // --- storyDao ---

    private scrum.server.project.StoryDao storyDao;

    public final scrum.server.project.StoryDao getStoryDao() {
        if (storyDao == null) {
            storyDao = createStoryDao();
            initializeStoryDao(storyDao);
        }
        return storyDao;
    }

    protected scrum.server.project.StoryDao createStoryDao() {
        return storyDao = new scrum.server.project.StoryDao();
    }

    protected void initializeStoryDao(scrum.server.project.StoryDao bean) {
        autowire(bean);
        Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetStoryDao() {
        storyDao = null;
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
        Reflect.invokeInitializeIfThere(bean);
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
        Reflect.invokeInitializeIfThere(bean);
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
        Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetProjectDao() {
        projectDao = null;
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
        return impedimentDao = new scrum.server.impediments.ImpedimentDao();
    }

    protected void initializeImpedimentDao(scrum.server.impediments.ImpedimentDao bean) {
        autowire(bean);
        Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetImpedimentDao() {
        impedimentDao = null;
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
        Reflect.invokeInitializeIfThere(bean);
    }

    public final void resetTaskDao() {
        taskDao = null;
    }

    // --- dependencies ---

    // --- scrum ---
    public abstract void onPing(SessionData session);
    public abstract void onLogin(SessionData session, java.lang.String username, java.lang.String password);
    public abstract void onSelectProject(SessionData session, java.lang.String projectId);
    public abstract void onRequestImpediments(SessionData session);
    public abstract void onRequestStorys(SessionData session);
    public abstract void onRequestCurrentSprint(SessionData session);
    public abstract void onChangeProperties(SessionData session, java.lang.String entityId, java.util.Map properties);
    public abstract void onCreateEntity(SessionData session, java.lang.String type, java.util.Map properties);
    public abstract void onDeleteEntity(SessionData session, java.lang.String entityId);
    public abstract void onSleep(SessionData session, long millis);

}
