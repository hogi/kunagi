package scrum.client;

import scrum.client.admin.User;
import scrum.client.common.AScrumComponent;

public class Auth extends AScrumComponent implements ServerDataReceivedListener {

	private User user;

	public void onServerDataReceived(DataTransferObject data) {
		if (data.isUserSet()) {
			user = cm.getDao().getUser(data.getUserId());
			log.info("User logged in:", user);
			cm.getEventBus().fireLogin();
		}
	}

	public void logout() {
		if (cm.getProjectContext().isProjectOpen()) cm.getProjectContext().closeProject(false);
		log.info("Logging out");
		cm.getUi().getWorkspace().lock("Logging out...");
		user = null;
		cm.getEventBus().fireLogout();
		ScrumGwtApplication.get().callLogout();
	}

	public boolean isUserLoggedIn() {
		return user != null;
	}

	public User getUser() {
		return user;
	}

}
