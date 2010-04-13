package scrum.client.admin;

import scrum.client.DataTransferObject;
import scrum.client.ScrumScopeManager;
import scrum.client.ServerDataReceivedListener;

public class Auth extends GAuth implements ServerDataReceivedListener {

	private User user;

	public void onServerDataReceived(DataTransferObject data) {
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
