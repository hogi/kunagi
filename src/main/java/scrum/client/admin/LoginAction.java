package scrum.client.admin;

import ilarkesto.core.logging.Log;
import ilarkesto.core.scope.Scope;
import scrum.client.ScrumScopeManager;
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
		final Ui ui = Scope.get().getComponent(Ui.class);
		ui.getWorkspace().lock("Checking login data...");
		cm.getApp().callLogin(loginData.getUsername(), loginData.getPassword(), new Runnable() {

			public void run() {
				Log.DEBUG("Login response received");
				if (!cm.getAuth().isUserLoggedIn()) {
					log.info("Login failed.");
					ui.unlock();
					loginData.setFailed();
				} else {
					User user = getCurrentUser();
					Log.DEBUG("Login succeded:", user);
					Project project = user.getCurrentProject();
					if (project == null || user.isAdmin()) {
						cm.getHomeContext().activate();
						ScrumScopeManager.createUserScope(user);
					} else {
						ScrumScopeManager.createUserScope(user);
						ScrumScopeManager.createProjectScope(project);
					}
				}
			}
		});
	}

}