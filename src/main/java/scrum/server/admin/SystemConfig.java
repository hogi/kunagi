package scrum.server.admin;

import scrum.server.common.StartServlet;

public class SystemConfig extends GSystemConfig {

	@Override
	public boolean isVisibleFor(User user) {
		return true;
	}

	@Override
	public boolean isEditableBy(User user) {
		if (user == null) return false;
		return user.isAdmin();
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		if (!isUrlSet()) setUrl(StartServlet.getWebappUrl());
		if (!getUrl().endsWith("/")) setUrl(getUrl() + "/");
	}

}