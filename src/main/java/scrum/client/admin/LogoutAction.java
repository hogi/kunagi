package scrum.client.admin;

import scrum.client.ScrumGwtApplication;

public class LogoutAction extends GLogoutAction {

	@Override
	public String getLabel() {
		return "Logout";
	}

	@Override
	public boolean isExecutable() {
		return getUser() != null;
	}

	@Override
	protected void onExecute() {
		ScrumGwtApplication.get().logout();
	}

}