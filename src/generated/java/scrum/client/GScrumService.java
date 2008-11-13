









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtServiceInterfaceGenerator










package scrum.client;

import java.util.*;

public interface GScrumService
            extends com.google.gwt.user.client.rpc.RemoteService {

    DataTransferObject getProject(java.lang.String projectId);

    DataTransferObject getImpediments();

    DataTransferObject getBacklogItems();

    DataTransferObject changeProperties(java.lang.String entityId, java.util.Map properties);

    DataTransferObject createEntity(java.lang.String type, java.util.Map properties);

    DataTransferObject sleep(long millis);

}
