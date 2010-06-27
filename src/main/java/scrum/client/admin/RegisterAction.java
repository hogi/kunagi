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
				registrationData.setSuccessful("Thank you, " + registrationData.getUsername()
						+ "! You account has been created. You may now use it to login to the application.");
				registrationData.clear();
				ui.unlock();
			}
		});
	}

}