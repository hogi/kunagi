









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtServiceAsyncInterfaceGenerator










package scrum.client;

import java.util.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GScrumServiceAsync {

    void getProject(java.lang.String projectId, AsyncCallback<DataTransferObject> callback);

    void getImpediments(AsyncCallback<DataTransferObject> callback);

    void getBacklogItems(AsyncCallback<DataTransferObject> callback);

    void changeProperty(java.lang.String entityId, java.lang.String property, java.lang.String value, AsyncCallback<DataTransferObject> callback);

    void sleep(long millis, AsyncCallback<DataTransferObject> callback);

}
