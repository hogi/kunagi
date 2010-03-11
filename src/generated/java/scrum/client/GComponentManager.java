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

}