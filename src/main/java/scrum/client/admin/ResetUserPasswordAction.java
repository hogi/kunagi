package scrum.client.admin;

public class ResetUserPasswordAction extends GResetUserPasswordAction {

	public ResetUserPasswordAction(scrum.client.admin.User user) {
		super(user);
	}

	@Override
	public String getLabel() {
		return "Reset password to default";
	}

	@Override
	protected void onExecute() {}

}