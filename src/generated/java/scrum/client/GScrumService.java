









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtServiceInterfaceGenerator










package scrum.client;

import java.util.*;

public interface GScrumService
            extends com.google.gwt.user.client.rpc.RemoteService {

    DataTransferObject ping();

    DataTransferObject login(java.lang.String username, java.lang.String password);

    DataTransferObject selectProject(java.lang.String projectId);

    DataTransferObject requestImpediments();

    DataTransferObject requestStorys();

    DataTransferObject requestCurrentSprint();

    DataTransferObject changeProperties(java.lang.String entityId, java.util.Map properties);

    DataTransferObject createEntity(java.lang.String type, java.util.Map properties);

    DataTransferObject deleteEntity(java.lang.String entityId);

    DataTransferObject sleep(long millis);

}
