package scrum.client.admin;

import scrum.client.DataTransferObject;
import scrum.client.ScrumScopeManager;
import scrum.client.communication.ServerDataReceivedEvent;
import scrum.client.communication.ServerDataReceivedHandler;

public class Auth extends GAuth implements ServerDataReceivedHandler {

	private User user;

	@Override
	public void onServerDataReceived(ServerDataReceivedEvent event) {
		DataTransferObject data = event.getData();
		if (data.isUserSet()) {
			user = dao.getUser(data.getUserId());
			log.info("User logged in:", user);
			ScrumScopeManager.createUserScope(user);
		}
	}

	public void logout() {
		user = null;
	}

	public boolean isUserLoggedIn() {
		return user != null;
	}

	public User getUser() {
		return user;
	}

}
