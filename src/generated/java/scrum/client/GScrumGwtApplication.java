// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtApplicationGenerator










package scrum.client;

import java.util.*;

public abstract class GScrumGwtApplication
            extends ilarkesto.gwt.client.AGwtApplication {

    protected abstract void onServerData(DataTransferObject dto);

    @Override
    protected final void handleDataFromServer(ilarkesto.gwt.client.ADataTransferObject data) {
        super.handleDataFromServer(data);
        onServerData((DataTransferObject)data);
    }

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
        onServiceCall();
        getScrumService().ping( new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callPing() {
        callPing( null);
    }

    public final void callLogin(java.lang.String username, java.lang.String password,  Runnable callback) {
        onServiceCall();
        getScrumService().login(username, password,  new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callLogin(java.lang.String username, java.lang.String password) {
        callLogin(username, password,  null);
    }

    public final void callLogout( Runnable callback) {
        onServiceCall();
        getScrumService().logout( new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callLogout() {
        callLogout( null);
    }

    public final void callChangePassword(java.lang.String oldPassword, java.lang.String newPassword,  Runnable callback) {
        onServiceCall();
        getScrumService().changePassword(oldPassword, newPassword,  new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callChangePassword(java.lang.String oldPassword, java.lang.String newPassword) {
        callChangePassword(oldPassword, newPassword,  null);
    }

    public final void callResetPassword(java.lang.String userId,  Runnable callback) {
        onServiceCall();
        getScrumService().resetPassword(userId,  new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callResetPassword(java.lang.String userId) {
        callResetPassword(userId,  null);
    }

    public final void callSelectProject(java.lang.String projectId,  Runnable callback) {
        onServiceCall();
        getScrumService().selectProject(projectId,  new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callSelectProject(java.lang.String projectId) {
        callSelectProject(projectId,  null);
    }

    public final void callCloseProject( Runnable callback) {
        onServiceCall();
        getScrumService().closeProject( new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callCloseProject() {
        callCloseProject( null);
    }

    public final void callSwitchToNextSprint( Runnable callback) {
        onServiceCall();
        getScrumService().switchToNextSprint( new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callSwitchToNextSprint() {
        callSwitchToNextSprint( null);
    }

    public final void callRequestImpediments( Runnable callback) {
        onServiceCall();
        getScrumService().requestImpediments( new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callRequestImpediments() {
        callRequestImpediments( null);
    }

    public final void callRequestAcceptedIssues( Runnable callback) {
        onServiceCall();
        getScrumService().requestAcceptedIssues( new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callRequestAcceptedIssues() {
        callRequestAcceptedIssues( null);
    }

    public final void callRequestClosedIssues( Runnable callback) {
        onServiceCall();
        getScrumService().requestClosedIssues( new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callRequestClosedIssues() {
        callRequestClosedIssues( null);
    }

    public final void callRequestRisks( Runnable callback) {
        onServiceCall();
        getScrumService().requestRisks( new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callRequestRisks() {
        callRequestRisks( null);
    }

    public final void callRequestRequirementEstimationVotes(java.lang.String requirementId,  Runnable callback) {
        onServiceCall();
        getScrumService().requestRequirementEstimationVotes(requirementId,  new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callRequestRequirementEstimationVotes(java.lang.String requirementId) {
        callRequestRequirementEstimationVotes(requirementId,  null);
    }

    public final void callRequestComments(java.lang.String parentId,  Runnable callback) {
        onServiceCall();
        getScrumService().requestComments(parentId,  new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callRequestComments(java.lang.String parentId) {
        callRequestComments(parentId,  null);
    }

    public final void callRequestChanges(java.lang.String parentId,  Runnable callback) {
        onServiceCall();
        getScrumService().requestChanges(parentId,  new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callRequestChanges(java.lang.String parentId) {
        callRequestChanges(parentId,  null);
    }

    public final void callChangeProperties(java.lang.String entityId, java.util.Map properties,  Runnable callback) {
        onServiceCall();
        getScrumService().changeProperties(entityId, properties,  new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callChangeProperties(java.lang.String entityId, java.util.Map properties) {
        callChangeProperties(entityId, properties,  null);
    }

    public final void callCreateEntity(java.lang.String type, java.util.Map properties,  Runnable callback) {
        onServiceCall();
        getScrumService().createEntity(type, properties,  new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callCreateEntity(java.lang.String type, java.util.Map properties) {
        callCreateEntity(type, properties,  null);
    }

    public final void callDeleteEntity(java.lang.String entityId,  Runnable callback) {
        onServiceCall();
        getScrumService().deleteEntity(entityId,  new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callDeleteEntity(java.lang.String entityId) {
        callDeleteEntity(entityId,  null);
    }

    public final void callRequestEntityByReference(java.lang.String reference,  Runnable callback) {
        onServiceCall();
        getScrumService().requestEntityByReference(reference,  new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callRequestEntityByReference(java.lang.String reference) {
        callRequestEntityByReference(reference,  null);
    }

    public final void callSetSelectedEntitysIds(java.util.Set ids,  Runnable callback) {
        onServiceCall();
        getScrumService().setSelectedEntitysIds(ids,  new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callSetSelectedEntitysIds(java.util.Set ids) {
        callSetSelectedEntitysIds(ids,  null);
    }

    public final void callSleep(long millis,  Runnable callback) {
        onServiceCall();
        getScrumService().sleep(millis,  new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callSleep(long millis) {
        callSleep(millis,  null);
    }

    public final void callUpdateSystemMessage(scrum.client.admin.SystemMessage systemMessage,  Runnable callback) {
        onServiceCall();
        getScrumService().updateSystemMessage(systemMessage,  new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callUpdateSystemMessage(scrum.client.admin.SystemMessage systemMessage) {
        callUpdateSystemMessage(systemMessage,  null);
    }

    public final void callSearch(java.lang.String text,  Runnable callback) {
        onServiceCall();
        getScrumService().search(text,  new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callSearch(java.lang.String text) {
        callSearch(text,  null);
    }

    public final void callActivateRequirementEstimationVoting(java.lang.String requirementId,  Runnable callback) {
        onServiceCall();
        getScrumService().activateRequirementEstimationVoting(requirementId,  new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callActivateRequirementEstimationVoting(java.lang.String requirementId) {
        callActivateRequirementEstimationVoting(requirementId,  null);
    }

    public final void callRequestForum( Runnable callback) {
        onServiceCall();
        getScrumService().requestForum( new DefaultCallback<DataTransferObject>(callback));
    }

    public final void callRequestForum() {
        callRequestForum( null);
    }

}