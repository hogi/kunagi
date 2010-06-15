package scrum.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ScrumServiceAsync extends GScrumServiceAsync {

	void startConversation(int conversationNumber, AsyncCallback<DataTransferObject> defaultCallback);

}
