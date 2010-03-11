// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtComponentManagerGenerator










package scrum.client;

import java.util.*;

public abstract class GComponentManager
            extends ilarkesto.gwt.client.AComponentManager<EventBus, Dao> {

    GComponentManager(EventBus eventBus, Dao dao) {
        super(eventBus, dao);
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

}