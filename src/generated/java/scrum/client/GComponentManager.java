// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtComponentManagerGenerator










package scrum.client;

import java.util.*;

public abstract class GComponentManager
            extends ilarkesto.gwt.client.AComponentManager<EventBus, Dao> {

    GComponentManager(EventBus eventBus, Dao dao) {
        super(eventBus, dao);
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

    // --- search ---

    private Search search;

    public final Search getSearch() {
        if (search == null) {
            search = new Search();
            initializeSearch(search);
            initialize(search);
        }
        return search;
    }

    protected void initializeSearch(Search search) {
    }

    public final void destroySearch() {
        destroy(search);
        search = null;
    }

    // --- uploader ---

    private Uploader uploader;

    public final Uploader getUploader() {
        if (uploader == null) {
            uploader = new Uploader();
            initializeUploader(uploader);
            initialize(uploader);
        }
        return uploader;
    }

    protected void initializeUploader(Uploader uploader) {
    }

    public final void destroyUploader() {
        destroy(uploader);
        uploader = null;
    }

}