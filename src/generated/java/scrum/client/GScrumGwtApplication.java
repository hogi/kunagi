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

    public final void callPing( Runnable callback) {
        getScrumService().ping( new DefaultCallback(callback));
    }

    public final void callPing() {
        callPing( null);
    }

    public final void callLogin(java.lang.String username, java.lang.String password,  Runnable callback) {
        getScrumService().login(username, password,  new DefaultCallback(callback));
    }

    public final void callLogin(java.lang.String username, java.lang.String password) {
        callLogin(username, password,  null);
    }

    public final void callSelectProject(java.lang.String projectId,  Runnable callback) {
        getScrumService().selectProject(projectId,  new DefaultCallback(callback));
    }

    public final void callSelectProject(java.lang.String projectId) {
        callSelectProject(projectId,  null);
    }

    public final void callSwitchToNextSprint( Runnable callback) {
        getScrumService().switchToNextSprint( new DefaultCallback(callback));
    }

    public final void callSwitchToNextSprint() {
        callSwitchToNextSprint( null);
    }

    public final void callRequestImpediments( Runnable callback) {
        getScrumService().requestImpediments( new DefaultCallback(callback));
    }

    public final void callRequestImpediments() {
        callRequestImpediments( null);
    }

    public final void callRequestRisks( Runnable callback) {
        getScrumService().requestRisks( new DefaultCallback(callback));
    }

    public final void callRequestRisks() {
        callRequestRisks( null);
    }

    public final void callRequestRequirements( Runnable callback) {
        getScrumService().requestRequirements( new DefaultCallback(callback));
    }

    public final void callRequestRequirements() {
        callRequestRequirements( null);
    }

    public final void callRequestQualitys( Runnable callback) {
        getScrumService().requestQualitys( new DefaultCallback(callback));
    }

    public final void callRequestQualitys() {
        callRequestQualitys( null);
    }

    public final void callRequestCurrentSprint( Runnable callback) {
        getScrumService().requestCurrentSprint( new DefaultCallback(callback));
    }

    public final void callRequestCurrentSprint() {
        callRequestCurrentSprint( null);
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

    public final void callDeleteEntity(java.lang.String entityId,  Runnable callback) {
        getScrumService().deleteEntity(entityId,  new DefaultCallback(callback));
    }

    public final void callDeleteEntity(java.lang.String entityId) {
        callDeleteEntity(entityId,  null);
    }

    public final void callSleep(long millis,  Runnable callback) {
        getScrumService().sleep(millis,  new DefaultCallback(callback));
    }

    public final void callSleep(long millis) {
        callSleep(millis,  null);
    }

}