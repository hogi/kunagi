package scrum.client.admin;

@Deprecated
public class RequestNewPasswordAction extends GRequestNewPasswordAction {

	@Override
	public String getLabel() {
		return "Request new password";
	}

	@Override
	protected void onExecute() {}
}