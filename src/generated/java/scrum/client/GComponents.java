// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtComponentsGenerator










package scrum.client;

import java.util.*;

public abstract class GComponents
            extends ilarkesto.gwt.client.AComponents<EventBus, Dao> {

    GComponents(EventBus eventBus, Dao dao) {
        super(eventBus, dao);
    }

    // --- pinger ---

    private Pinger pinger;

    public final Pinger getPinger() {
        if (pinger == null) {
            pinger = new Pinger();
            initializePinger(pinger);
            initialize(pinger);
        }
        return pinger;
    }

    protected void initializePinger(Pinger pinger) {
    }

    public final void destroyPinger() {
        destroy(pinger);
        pinger = null;
    }

    // --- auth ---

    private Auth auth;

    public final Auth getAuth() {
        if (auth == null) {
            auth = new Auth();
            initializeAuth(auth);
            initialize(auth);
        }
        return auth;
    }

    protected void initializeAuth(Auth auth) {
    }

    public final void destroyAuth() {
        destroy(auth);
        auth = null;
    }

    // --- projectContext ---

    private ProjectContext projectContext;

    public final ProjectContext getProjectContext() {
        if (projectContext == null) {
            projectContext = new ProjectContext();
            initializeProjectContext(projectContext);
            initialize(projectContext);
        }
        return projectContext;
    }

    protected void initializeProjectContext(ProjectContext projectContext) {
    }

    public final void destroyProjectContext() {
        destroy(projectContext);
        projectContext = null;
    }

    // --- ui ---

    private Ui ui;

    public final Ui getUi() {
        if (ui == null) {
            ui = new Ui();
            initializeUi(ui);
            initialize(ui);
        }
        return ui;
    }

    protected void initializeUi(Ui ui) {
    }

    public final void destroyUi() {
        destroy(ui);
        ui = null;
    }

    // --- dndManager ---

    private DndManager dndManager;

    public final DndManager getDndManager() {
        if (dndManager == null) {
            dndManager = new DndManager();
            initializeDndManager(dndManager);
            initialize(dndManager);
        }
        return dndManager;
    }

    protected void initializeDndManager(DndManager dndManager) {
    }

    public final void destroyDndManager() {
        destroy(dndManager);
        dndManager = null;
    }

    // --- chat ---

    private Chat chat;

    public final Chat getChat() {
        if (chat == null) {
            chat = new Chat();
            initializeChat(chat);
            initialize(chat);
        }
        return chat;
    }

    protected void initializeChat(Chat chat) {
    }

    public final void destroyChat() {
        destroy(chat);
        chat = null;
    }

    // --- usersStatus ---

    private UsersStatus usersStatus;

    public final UsersStatus getUsersStatus() {
        if (usersStatus == null) {
            usersStatus = new UsersStatus();
            initializeUsersStatus(usersStatus);
            initialize(usersStatus);
        }
        return usersStatus;
    }

    protected void initializeUsersStatus(UsersStatus usersStatus) {
    }

    public final void destroyUsersStatus() {
        destroy(usersStatus);
        usersStatus = null;
    }

}