









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtServiceInterfaceGenerator










package scrum.client;

import java.util.*;

public interface GScrumService
            extends com.google.gwt.user.client.rpc.RemoteService {

    ilarkesto.gwt.client.DataTransferObject ping();

    ilarkesto.gwt.client.DataTransferObject login(java.lang.String username, java.lang.String password);

    ilarkesto.gwt.client.DataTransferObject selectProject(java.lang.String projectId);

    ilarkesto.gwt.client.DataTransferObject requestImpediments();

    ilarkesto.gwt.client.DataTransferObject requestRequirements();

    ilarkesto.gwt.client.DataTransferObject requestCurrentSprint();

    ilarkesto.gwt.client.DataTransferObject changeProperties(java.lang.String entityId, java.util.Map properties);

    ilarkesto.gwt.client.DataTransferObject createEntity(java.lang.String type, java.util.Map properties);

    ilarkesto.gwt.client.DataTransferObject deleteEntity(java.lang.String entityId);

    ilarkesto.gwt.client.DataTransferObject sleep(long millis);

}
