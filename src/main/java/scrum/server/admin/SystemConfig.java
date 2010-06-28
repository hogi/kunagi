package scrum.server.admin;


public class SystemConfig extends GSystemConfig {

	public boolean isVisibleFor(User user) {
		return user.isAdmin();
	}

}