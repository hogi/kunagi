package scrum.client.admin;

import ilarkesto.gwt.client.GwtLogger;
import scrum.client.ScrumGwtApplication;
import scrum.client.project.Project;
import scrum.client.workspace.Ui;

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
		ScrumGwtApplication.get().getUi().lock("Checking login data...");
		ScrumGwtApplication.get().callLogin(loginData.getUsername(), loginData.getPassword(), new Runnable() {

			public void run() {
				GwtLogger.DEBUG("Login response received");
				User user = ScrumGwtApplication.get().getUser();
				if (user == null) {
					GwtLogger.DEBUG("LOGIN FAILED!");
					ScrumGwtApplication.get().getUi().unlock();
					Ui.get().showError("Login failed.");
				} else {
					GwtLogger.DEBUG("Login succeded:", ScrumGwtApplication.get().getUi());
					Project project = user.getCurrentProject();
					if (project == null || user.isAdmin()) {
						Ui.get().activateStartView();
					} else {
						ScrumGwtApplication.get().openProject(project);
					}
				}
			}
		});
	}

}