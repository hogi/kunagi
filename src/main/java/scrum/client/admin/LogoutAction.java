package scrum.client.admin;


public class LogoutAction extends GLogoutAction {

	@Override
	public String getLabel() {
		return "Logout";
	}

	@Override
	public boolean isExecutable() {
		return getAuth().isUserLoggedIn();
	}

	@Override
	protected void onExecute() {
		getApp().logout();
	}

}