package scrum.client.admin;

import ilarkesto.gwt.client.GwtLogger;
import scrum.client.project.Project;

public class LoginAction extends GLoginAction {

	private LoginDataProvider loginData;

	public LoginAction(LoginDataProvider loginData) {
		this.loginData = loginData;
	}

	@Override
	public String getLabel() {
		return "Login";
	}

	@Override
	protected void onExecute() {
		cm.getUi().getWorkspace().lock("Checking login data...");
		cm.getApp().callLogin(loginData.getUsername(), loginData.getPassword(), new Runnable() {

			public void run() {
				GwtLogger.DEBUG("Login response received");
				if (!cm.getAuth().isUserLoggedIn()) {
					log.info("Login failed.");
					cm.getUi().unlock();
					loginData.setFailed();
				} else {
					User user = getCurrentUser();
					GwtLogger.DEBUG("Login succeded:", user);
					Project project = user.getCurrentProject();
					if (project == null || user.isAdmin()) {
						cm.getHomeContext().activate();
					} else {
						cm.getProjectContext().openProject(project);
					}
				}
			}
		});
	}

}