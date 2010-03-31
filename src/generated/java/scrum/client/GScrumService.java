// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtServiceInterfaceGenerator










package scrum.client;

import java.util.*;

public interface GScrumService
            extends com.google.gwt.user.client.rpc.RemoteService {

    DataTransferObject ping(int conversationNumber);

    DataTransferObject login(int conversationNumber, java.lang.String username, java.lang.String password);

    DataTransferObject logout(int conversationNumber);

    DataTransferObject changePassword(int conversationNumber, java.lang.String oldPassword, java.lang.String newPassword);

    DataTransferObject resetPassword(int conversationNumber, java.lang.String userId);

    DataTransferObject selectProject(int conversationNumber, java.lang.String projectId);

    DataTransferObject closeProject(int conversationNumber);

    DataTransferObject switchToNextSprint(int conversationNumber);

    DataTransferObject requestImpediments(int conversationNumber);

    DataTransferObject requestAcceptedIssues(int conversationNumber);

    DataTransferObject requestClosedIssues(int conversationNumber);

    DataTransferObject requestRisks(int conversationNumber);

    DataTransferObject requestRequirementEstimationVotes(int conversationNumber, java.lang.String requirementId);

    DataTransferObject requestComments(int conversationNumber, java.lang.String parentId);

    DataTransferObject requestChanges(int conversationNumber, java.lang.String parentId);

    DataTransferObject changeProperties(int conversationNumber, java.lang.String entityId, java.util.Map properties);

    DataTransferObject createEntity(int conversationNumber, java.lang.String type, java.util.Map properties);

    DataTransferObject deleteEntity(int conversationNumber, java.lang.String entityId);

    DataTransferObject requestEntityByReference(int conversationNumber, java.lang.String reference);

    DataTransferObject setSelectedEntitysIds(int conversationNumber, java.util.Set ids);

    DataTransferObject sleep(int conversationNumber, long millis);

    DataTransferObject updateSystemMessage(int conversationNumber, scrum.client.admin.SystemMessage systemMessage);

    DataTransferObject search(int conversationNumber, java.lang.String text);

    DataTransferObject activateRequirementEstimationVoting(int conversationNumber, java.lang.String requirementId);

    DataTransferObject requestForum(int conversationNumber);

}