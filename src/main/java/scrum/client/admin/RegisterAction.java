package scrum.client.admin;

import ilarkesto.core.base.Str;
import ilarkesto.core.scope.Scope;
import scrum.client.communication.ServerErrorManager;
import scrum.client.workspace.Ui;

public class RegisterAction extends GLoginAction {

	private RegisterDataProvider registrationData;

	public RegisterAction(RegisterDataProvider registrationData) {
		this.registrationData = registrationData;
	}

	@Override
	public String getLabel() {
		return "Register";
	}

	@Override
	protected void onExecute() {

		String username = registrationData.getUsername();
		String email = registrationData.getEmail();
		String password = registrationData.getPassword();

		if (Str.isBlanc(username)) {
			registrationData.setFailed("Username required.");
			return;
		}

		if (Str.isBlanc(email)) {
			registrationData.setFailed("Email required.");
			return;
		}

		if (Str.isBlanc(password)) {
			registrationData.setFailed("Password required.");
			return;
		}

		final Ui ui = Scope.get().getComponent(Ui.class);
		ui.getWorkspace().lock("Process registration...");
		new RegisterServiceCall(username, email, password).execute(new Runnable() {

			public void run() {
				if (!getAuth().isUserLoggedIn()) {
					String error = Scope.get().getComponent(ServerErrorManager.class).popErrorsAsString();
					if (error == null) {
						error = "Registration failed.";
					}
					registrationData.setFailed(error);
					ui.unlock();
				} else {
					// TODO show message that e-mail verification is required
					registrationData.clear();
				}
			}
		});
	}

}