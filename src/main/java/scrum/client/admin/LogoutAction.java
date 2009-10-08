package scrum.client.admin;


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
		getComponents().getAuth().logout();
	}

}