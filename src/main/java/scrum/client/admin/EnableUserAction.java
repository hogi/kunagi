package scrum.client.admin;

public class EnableUserAction extends GEnableUserAction {

	public EnableUserAction(scrum.client.admin.User user) {
		super(user);
	}

	@Override
	public String getLabel() {
		return "Enable";
	}

	@Override
	public boolean isExecutable() {
		if (!user.isDisabled()) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentUser().isAdmin()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		user.setDisabled(false);
	}
}