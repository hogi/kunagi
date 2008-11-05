package scrum.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ScrumServiceAsync {

	void getProject(String projectId, AsyncCallback<ServerData> callback);

	void getImpediments(AsyncCallback<ServerData> callback);

	void getBacklogItems(AsyncCallback<ServerData> callback);

}
