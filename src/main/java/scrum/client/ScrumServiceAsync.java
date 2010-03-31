package scrum.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ScrumServiceAsync extends GScrumServiceAsync {

	void startConversation(AsyncCallback<DataTransferObject> callback);

}
