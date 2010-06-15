// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client;

public interface ScrumServiceAsync {

    void changePassword(int conversationNumber, String newPassword, String oldPassword, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void login(int conversationNumber, String username, String password, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void logout(int conversationNumber, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void resetPassword(int conversationNumber, String userId, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void updateSystemMessage(int conversationNumber, scrum.client.admin.SystemMessage systemMessage, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void requestComments(int conversationNumber, String parentId, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void requestForum(int conversationNumber, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void setSelectedEntitysIds(int conversationNumber, java.util.Set ids, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void ping(int conversationNumber, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void startConversation(int conversationNumber, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void changeProperties(int conversationNumber, String entityId, java.util.Map properties, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void createEntity(int conversationNumber, String type, java.util.Map properties, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void deleteEntity(int conversationNumber, String entityId, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void requestEntity(int conversationNumber, String entityId, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void requestEntityByReference(int conversationNumber, String reference, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void sleep(int conversationNumber, long millis, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void activateRequirementEstimationVoting(int conversationNumber, String requirementId, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void requestRequirementEstimationVotes(int conversationNumber, String requirementId, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void requestImpediments(int conversationNumber, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void requestAcceptedIssues(int conversationNumber, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void requestClosedIssues(int conversationNumber, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void requestReleaseIssues(int conversationNumber, String releaseId, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void requestChanges(int conversationNumber, String parentId, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void closeProject(int conversationNumber, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void createExampleProject(int conversationNumber, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void selectProject(int conversationNumber, String projectId, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void requestRisks(int conversationNumber, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void search(int conversationNumber, String text, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

    void switchToNextSprint(int conversationNumber, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback);

}

