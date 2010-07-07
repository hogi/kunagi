package scrum.server.admin;

import scrum.server.common.StartServlet;

public class SystemConfig extends GSystemConfig {

	@Override
	public boolean isVisibleFor(User user) {
		return user.isAdmin();
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		if (!isUrlSet()) setUrl(StartServlet.getWebappUrl());
		if (!getUrl().endsWith("/")) setUrl(getUrl() + "/");
	}

}