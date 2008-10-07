package scrum.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ScrumServiceAsync {

	void getProject(String projectId, AsyncCallback<ProjectData> callback);

}
