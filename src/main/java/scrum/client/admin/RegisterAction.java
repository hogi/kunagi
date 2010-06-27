package scrum.client.admin;

import ilarkesto.core.scope.Scope;
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
		final Ui ui = Scope.get().getComponent(Ui.class);
		ui.getWorkspace().lock("Process registration...");
		new RegisterServiceCall(registrationData.getUsername(), registrationData.getEmail(), registrationData
				.getPassword()).execute(new Runnable() {

			public void run() {
				if (!getAuth().isUserLoggedIn()) {
					// TODO proper information about why registration was unsuccessful
					registrationData.setFailed("Registration failed. Username or E-Mail already exist.");
					ui.unlock();
				} else {
					// TODO show message that e-mail verification is required
					registrationData.clear();
				}
			}
		});
	}

}