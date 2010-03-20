// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtServiceInterfaceGenerator










package scrum.client;

import java.util.*;

public interface GScrumService
            extends com.google.gwt.user.client.rpc.RemoteService {

    DataTransferObject ping();

    DataTransferObject login(java.lang.String username, java.lang.String password);

    DataTransferObject logout();

    DataTransferObject changePassword(java.lang.String oldPassword, java.lang.String newPassword);

    DataTransferObject resetPassword(java.lang.String userId);

    DataTransferObject selectProject(java.lang.String projectId);

    DataTransferObject closeProject();

    DataTransferObject switchToNextSprint();

    DataTransferObject requestImpediments();

    DataTransferObject requestAcceptedIssues();

    DataTransferObject requestClosedIssues();

    DataTransferObject requestRisks();

    DataTransferObject requestRequirementEstimationVotes(java.lang.String requirementId);

    DataTransferObject requestComments(java.lang.String parentId);

    DataTransferObject requestChanges(java.lang.String parentId);

    DataTransferObject changeProperties(java.lang.String entityId, java.util.Map properties);

    DataTransferObject createEntity(java.lang.String type, java.util.Map properties);

    DataTransferObject deleteEntity(java.lang.String entityId);

    DataTransferObject requestEntityByReference(java.lang.String reference);

    DataTransferObject setSelectedEntitysIds(java.util.Set ids);

    DataTransferObject sleep(long millis);

    DataTransferObject updateSystemMessage(scrum.client.admin.SystemMessage systemMessage);

    DataTransferObject search(java.lang.String text);

    DataTransferObject activateRequirementEstimationVoting(java.lang.String requirementId);

    DataTransferObject requestForum();

}