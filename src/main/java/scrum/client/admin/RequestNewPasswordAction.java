package scrum.client.admin;

import ilarkesto.core.base.Str;
import ilarkesto.core.scope.Scope;
import scrum.client.communication.ServerErrorManager;
import scrum.client.workspace.Ui;

public class RequestNewPasswordAction extends GRequestNewPasswordAction {

	private LoginWidget loginWidget;

	public RequestNewPasswordAction(LoginWidget loginWidget) {
		this.loginWidget = loginWidget;
	}

	@Override
	public String getLabel() {
		return "Request new password";
	}

	@Override
	protected void onExecute() {
		String login = loginWidget.getUsername();
		if (Str.isBlank(login)) {
			loginWidget.setFailed("Email required.");
			return;
		}
		Scope.get().getComponent(Ui.class).getWorkspace().lock("Process registration...");
		new RequestNewPasswordServiceCall(login).execute(new Runnable() {

			public void run() {
				String message = Scope.get().getComponent(ServerErrorManager.class).popErrorsAsString();
				if (message == null) message = "Password will be sent to you by email.";
				loginWidget.setFailed(message);
				Scope.get().getComponent(Ui.class).getWorkspace().unlock();
			}
		});
	}
}