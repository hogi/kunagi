









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtApplicationGenerator










package scrum.client;

import java.util.*;

public abstract class GScrumGwtApplication
            extends scrum.client.AGwtApplication {

    // --- scrum service ---

    private ScrumServiceAsync scrumService;

    public final ScrumServiceAsync getScrumService() {
        if (scrumService == null) {
            scrumService = com.google.gwt.core.client.GWT.create(ScrumService.class);
            ((com.google.gwt.user.client.rpc.ServiceDefTarget) scrumService).setServiceEntryPoint(com.google.gwt.core.client.GWT.getModuleBaseURL() + "scrum");
        }
        return scrumService;
    }

    public final void callGetProject(java.lang.String projectId,  Runnable callback) {
        getScrumService().getProject(projectId,  new DefaultCallback(callback));
    }

    public final void callGetProject(java.lang.String projectId) {
        callGetProject(projectId,  null);
    }

    public final void callGetImpediments( Runnable callback) {
        getScrumService().getImpediments( new DefaultCallback(callback));
    }

    public final void callGetImpediments() {
        callGetImpediments( null);
    }

    public final void callGetBacklogItems( Runnable callback) {
        getScrumService().getBacklogItems( new DefaultCallback(callback));
    }

    public final void callGetBacklogItems() {
        callGetBacklogItems( null);
    }

    public final void callChangeProperties(java.lang.String entityId, java.util.Map properties,  Runnable callback) {
        getScrumService().changeProperties(entityId, properties,  new DefaultCallback(callback));
    }

    public final void callChangeProperties(java.lang.String entityId, java.util.Map properties) {
        callChangeProperties(entityId, properties,  null);
    }

    public final void callCreateEntity(java.lang.String type, java.util.Map properties,  Runnable callback) {
        getScrumService().createEntity(type, properties,  new DefaultCallback(callback));
    }

    public final void callCreateEntity(java.lang.String type, java.util.Map properties) {
        callCreateEntity(type, properties,  null);
    }

    public final void callSleep(long millis,  Runnable callback) {
        getScrumService().sleep(millis,  new DefaultCallback(callback));
    }

    public final void callSleep(long millis) {
        callSleep(millis,  null);
    }

}
