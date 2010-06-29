package scrum.server.admin;

public class SystemConfig extends GSystemConfig {

	public boolean isVisibleFor(User user) {
		return user.isAdmin();
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		if (!isUrlSet()) setUrl("http://localhost:8080/kunagi/");
		if (!getUrl().endsWith("/")) setUrl(getUrl() + "/");
	}

}