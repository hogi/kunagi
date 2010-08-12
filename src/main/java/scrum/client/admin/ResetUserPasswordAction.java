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
	public String getTooltip() {
		return "Reset users password to the default: " + getDao().getSystemConfig().getDefaultUserPassword();
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentUser().isAdmin()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		new ResetPasswordServiceCall(user.getId());
	}

}