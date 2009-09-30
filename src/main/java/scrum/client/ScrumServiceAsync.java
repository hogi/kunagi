package scrum.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ScrumServiceAsync extends GScrumServiceAsync {

	void startSession(AsyncCallback<DataTransferObject> callback);

}
