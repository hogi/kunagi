package scrum.client.admin;

import scrum.client.DataTransferObject;
import scrum.client.ScrumScopeManager;
import scrum.client.ServerDataReceivedListener;

import com.google.gwt.user.client.Window;

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
		log.info("Logging out");
		ui.lock("Logging out...");

		pinger.shutdown();
		app.callLogout();

		// user = null;
		// ScrumScopeManager.destroyUserScope();
		// app.resetConversation();
		// dao.clearAllEntities();

		Window.Location.reload();
	}

	public boolean isUserLoggedIn() {
		return user != null;
	}

	public User getUser() {
		return user;
	}

}
