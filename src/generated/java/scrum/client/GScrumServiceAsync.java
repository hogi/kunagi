// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtServiceAsyncInterfaceGenerator










package scrum.client;

import java.util.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GScrumServiceAsync {

    void ping(AsyncCallback<ilarkesto.gwt.client.DataTransferObject> callback);

    void login(java.lang.String username, java.lang.String password, AsyncCallback<ilarkesto.gwt.client.DataTransferObject> callback);

    void selectProject(java.lang.String projectId, AsyncCallback<ilarkesto.gwt.client.DataTransferObject> callback);

    void requestImpediments(AsyncCallback<ilarkesto.gwt.client.DataTransferObject> callback);

    void requestRisks(AsyncCallback<ilarkesto.gwt.client.DataTransferObject> callback);

    void requestRequirements(AsyncCallback<ilarkesto.gwt.client.DataTransferObject> callback);

    void requestAttributes(AsyncCallback<ilarkesto.gwt.client.DataTransferObject> callback);

    void requestCurrentSprint(AsyncCallback<ilarkesto.gwt.client.DataTransferObject> callback);

    void changeProperties(java.lang.String entityId, java.util.Map properties, AsyncCallback<ilarkesto.gwt.client.DataTransferObject> callback);

    void createEntity(java.lang.String type, java.util.Map properties, AsyncCallback<ilarkesto.gwt.client.DataTransferObject> callback);

    void deleteEntity(java.lang.String entityId, AsyncCallback<ilarkesto.gwt.client.DataTransferObject> callback);

    void sleep(long millis, AsyncCallback<ilarkesto.gwt.client.DataTransferObject> callback);

}