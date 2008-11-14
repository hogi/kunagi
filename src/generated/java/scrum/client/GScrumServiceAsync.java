









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtServiceAsyncInterfaceGenerator










package scrum.client;

import java.util.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GScrumServiceAsync {

    void getProject(java.lang.String projectId, AsyncCallback<DataTransferObject> callback);

    void getImpediments(AsyncCallback<DataTransferObject> callback);

    void getBacklogItems(AsyncCallback<DataTransferObject> callback);

    void getCurrentSprint(AsyncCallback<DataTransferObject> callback);

    void changeProperties(java.lang.String entityId, java.util.Map properties, AsyncCallback<DataTransferObject> callback);

    void createEntity(java.lang.String type, java.util.Map properties, AsyncCallback<DataTransferObject> callback);

    void deleteEntity(java.lang.String entityId, AsyncCallback<DataTransferObject> callback);

    void sleep(long millis, AsyncCallback<DataTransferObject> callback);

}
