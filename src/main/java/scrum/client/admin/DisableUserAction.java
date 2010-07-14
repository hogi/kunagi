package scrum.client.admin;

public class DisableUserAction extends GDisableUserAction {

	public DisableUserAction(scrum.client.admin.User user) {
		super(user);
	}

	@Override
	public String getLabel() {
		return "Disable";
	}

	@Override
	public boolean isExecutable() {
		if (user.isDisabled()) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentUser().isAdmin()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		user.setDisabled(true);
	}

}