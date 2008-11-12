









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtServiceInterfaceGenerator










package scrum.client;

import java.util.*;

public interface GScrumService
            extends com.google.gwt.user.client.rpc.RemoteService {

    DataTransferObject getProject(java.lang.String projectId);

    DataTransferObject getImpediments();

    DataTransferObject getBacklogItems();

    DataTransferObject changeProperty(java.lang.String entityId, java.lang.String property, java.lang.String value);

    DataTransferObject sleep(long millis);

}
