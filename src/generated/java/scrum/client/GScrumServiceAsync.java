// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtServiceAsyncInterfaceGenerator










package scrum.client;

import java.util.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GScrumServiceAsync {

    void ping(int conversationNumber, AsyncCallback<DataTransferObject> callback);

    void login(int conversationNumber, java.lang.String username, java.lang.String password, AsyncCallback<DataTransferObject> callback);

    void logout(int conversationNumber, AsyncCallback<DataTransferObject> callback);

    void changePassword(int conversationNumber, java.lang.String oldPassword, java.lang.String newPassword, AsyncCallback<DataTransferObject> callback);

    void resetPassword(int conversationNumber, java.lang.String userId, AsyncCallback<DataTransferObject> callback);

    void selectProject(int conversationNumber, java.lang.String projectId, AsyncCallback<DataTransferObject> callback);

    void closeProject(int conversationNumber, AsyncCallback<DataTransferObject> callback);

    void switchToNextSprint(int conversationNumber, AsyncCallback<DataTransferObject> callback);

    void requestImpediments(int conversationNumber, AsyncCallback<DataTransferObject> callback);

    void requestAcceptedIssues(int conversationNumber, AsyncCallback<DataTransferObject> callback);

    void requestClosedIssues(int conversationNumber, AsyncCallback<DataTransferObject> callback);

    void requestRisks(int conversationNumber, AsyncCallback<DataTransferObject> callback);

    void requestRequirementEstimationVotes(int conversationNumber, java.lang.String requirementId, AsyncCallback<DataTransferObject> callback);

    void requestComments(int conversationNumber, java.lang.String parentId, AsyncCallback<DataTransferObject> callback);

    void requestChanges(int conversationNumber, java.lang.String parentId, AsyncCallback<DataTransferObject> callback);

    void changeProperties(int conversationNumber, java.lang.String entityId, java.util.Map properties, AsyncCallback<DataTransferObject> callback);

    void createEntity(int conversationNumber, java.lang.String type, java.util.Map properties, AsyncCallback<DataTransferObject> callback);

    void deleteEntity(int conversationNumber, java.lang.String entityId, AsyncCallback<DataTransferObject> callback);

    void requestEntityByReference(int conversationNumber, java.lang.String reference, AsyncCallback<DataTransferObject> callback);

    void requestEntity(int conversationNumber, java.lang.String entityId, AsyncCallback<DataTransferObject> callback);

    void setSelectedEntitysIds(int conversationNumber, java.util.Set ids, AsyncCallback<DataTransferObject> callback);

    void sleep(int conversationNumber, long millis, AsyncCallback<DataTransferObject> callback);

    void updateSystemMessage(int conversationNumber, scrum.client.admin.SystemMessage systemMessage, AsyncCallback<DataTransferObject> callback);

    void search(int conversationNumber, java.lang.String text, AsyncCallback<DataTransferObject> callback);

    void activateRequirementEstimationVoting(int conversationNumber, java.lang.String requirementId, AsyncCallback<DataTransferObject> callback);

    void requestForum(int conversationNumber, AsyncCallback<DataTransferObject> callback);

}