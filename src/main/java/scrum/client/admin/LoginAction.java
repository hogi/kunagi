package scrum.client.admin;

import ilarkesto.core.scope.Scope;
import scrum.client.communication.ServerErrorManager;
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
		new LoginServiceCall(loginData.getUsername(), loginData.getPassword()).execute(new Runnable() {

			public void run() {
				if (!getAuth().isUserLoggedIn()) {
					log.info("Login failed.");
					String error = Scope.get().getComponent(ServerErrorManager.class).popErrorsAsString();
					if (error == null) error = "Login failed.";
					loginData.setFailed(error);
					ui.unlock();
				} else {
					loginData.clear();
				}
			}
		});
	}
}