









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtServiceAsyncInterfaceGenerator










package scrum.client;

import java.util.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GScrumServiceAsync {

    void ping(AsyncCallback<DataTransferObject> callback);

    void login(java.lang.String username, java.lang.String password, AsyncCallback<DataTransferObject> callback);

    void selectProject(java.lang.String projectId, AsyncCallback<DataTransferObject> callback);

    void requestImpediments(AsyncCallback<DataTransferObject> callback);

    void requestRequirements(AsyncCallback<DataTransferObject> callback);

    void requestCurrentSprint(AsyncCallback<DataTransferObject> callback);

    void changeProperties(java.lang.String entityId, java.util.Map properties, AsyncCallback<DataTransferObject> callback);

    void createEntity(java.lang.String type, java.util.Map properties, AsyncCallback<DataTransferObject> callback);

    void deleteEntity(java.lang.String entityId, AsyncCallback<DataTransferObject> callback);

    void sleep(long millis, AsyncCallback<DataTransferObject> callback);

}
