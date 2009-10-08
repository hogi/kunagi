package scrum.client.admin;

import ilarkesto.gwt.client.GwtLogger;
import scrum.client.ScrumGwtApplication;
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
		getUi().getWorkspace().lock("Checking login data...");
		ScrumGwtApplication.get().callLogin(loginData.getUsername(), loginData.getPassword(), new Runnable() {

			public void run() {
				GwtLogger.DEBUG("Login response received");
				User user = ScrumGwtApplication.get().getUser();
				if (user == null) {
					GwtLogger.DEBUG("Login failed!");
					getUi().getWorkspace().unlock();
					loginData.setFailed();
				} else {
					GwtLogger.DEBUG("Login succeded:", user);
					Project project = user.getCurrentProject();
					if (project == null || user.isAdmin()) {
						getUi().getWorkspace().activateStartView();
					} else {
						getProjectContext().openProject(project);
					}
				}
			}
		});
	}

}