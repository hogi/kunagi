// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtComponentManagerGenerator










package scrum.client;

import java.util.*;

public abstract class GComponentManager
            extends ilarkesto.gwt.client.AComponentManager<EventBus, Dao> {

    GComponentManager(EventBus eventBus, Dao dao) {
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

    // --- publicContext ---

    private PublicContext publicContext;

    public final PublicContext getPublicContext() {
        if (publicContext == null) {
            publicContext = new PublicContext();
            initializePublicContext(publicContext);
            initialize(publicContext);
        }
        return publicContext;
    }

    protected void initializePublicContext(PublicContext publicContext) {
    }

    public final void destroyPublicContext() {
        destroy(publicContext);
        publicContext = null;
    }

    // --- homeContext ---

    private HomeContext homeContext;

    public final HomeContext getHomeContext() {
        if (homeContext == null) {
            homeContext = new HomeContext();
            initializeHomeContext(homeContext);
            initialize(homeContext);
        }
        return homeContext;
    }

    protected void initializeHomeContext(HomeContext homeContext) {
    }

    public final void destroyHomeContext() {
        destroy(homeContext);
        homeContext = null;
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

    // --- systemMessageManager ---

    private SystemMessageManager systemMessageManager;

    public final SystemMessageManager getSystemMessageManager() {
        if (systemMessageManager == null) {
            systemMessageManager = new SystemMessageManager();
            initializeSystemMessageManager(systemMessageManager);
            initialize(systemMessageManager);
        }
        return systemMessageManager;
    }

    protected void initializeSystemMessageManager(SystemMessageManager systemMessageManager) {
    }

    public final void destroySystemMessageManager() {
        destroy(systemMessageManager);
        systemMessageManager = null;
    }

    // --- wiki ---

    private Wiki wiki;

    public final Wiki getWiki() {
        if (wiki == null) {
            wiki = new Wiki();
            initializeWiki(wiki);
            initialize(wiki);
        }
        return wiki;
    }

    protected void initializeWiki(Wiki wiki) {
    }

    public final void destroyWiki() {
        destroy(wiki);
        wiki = null;
    }

    // --- undo ---

    private Undo undo;

    public final Undo getUndo() {
        if (undo == null) {
            undo = new Undo();
            initializeUndo(undo);
            initialize(undo);
        }
        return undo;
    }

    protected void initializeUndo(Undo undo) {
    }

    public final void destroyUndo() {
        destroy(undo);
        undo = null;
    }

}