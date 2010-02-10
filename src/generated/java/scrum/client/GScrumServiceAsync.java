// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtServiceAsyncInterfaceGenerator










package scrum.client;

import java.util.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GScrumServiceAsync {

    void ping(AsyncCallback<DataTransferObject> callback);

    void login(java.lang.String username, java.lang.String password, AsyncCallback<DataTransferObject> callback);

    void logout(AsyncCallback<DataTransferObject> callback);

    void changePassword(java.lang.String oldPassword, java.lang.String newPassword, AsyncCallback<DataTransferObject> callback);

    void resetPassword(java.lang.String userId, AsyncCallback<DataTransferObject> callback);

    void selectProject(java.lang.String projectId, AsyncCallback<DataTransferObject> callback);

    void closeProject(AsyncCallback<DataTransferObject> callback);

    void switchToNextSprint(AsyncCallback<DataTransferObject> callback);

    void requestImpediments(AsyncCallback<DataTransferObject> callback);

    void requestIssues(AsyncCallback<DataTransferObject> callback);

    void requestRisks(AsyncCallback<DataTransferObject> callback);

    void requestRequirementEstimationVotes(java.lang.String requirementId, AsyncCallback<DataTransferObject> callback);

    void requestComments(java.lang.String parentId, AsyncCallback<DataTransferObject> callback);

    void changeProperties(java.lang.String entityId, java.util.Map properties, AsyncCallback<DataTransferObject> callback);

    void createEntity(java.lang.String type, java.util.Map properties, AsyncCallback<DataTransferObject> callback);

    void deleteEntity(java.lang.String entityId, AsyncCallback<DataTransferObject> callback);

    void requestEntityByReference(java.lang.String reference, AsyncCallback<DataTransferObject> callback);

    void setSelectedEntitysIds(java.util.Set ids, AsyncCallback<DataTransferObject> callback);

    void sleep(long millis, AsyncCallback<DataTransferObject> callback);

    void updateSystemMessage(scrum.client.admin.SystemMessage systemMessage, AsyncCallback<DataTransferObject> callback);

    void search(java.lang.String text, AsyncCallback<DataTransferObject> callback);

    void activateRequirementEstimationVoting(java.lang.String requirementId, AsyncCallback<DataTransferObject> callback);

    void requestForum(AsyncCallback<DataTransferObject> callback);

}