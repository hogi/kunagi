package scrum.client.admin;

import ilarkesto.core.base.Str;

public class ConfirmUserEmailAction extends GConfirmUserEmailAction {

	public ConfirmUserEmailAction(scrum.client.admin.User user) {
		super(user);
	}

	@Override
	public String getLabel() {
		return "Confirm email";
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentUser().isAdmin()) return false;
		return true;
	}

	@Override
	public boolean isExecutable() {
		if (!Str.isEmail(user.getEmail())) return false;
		if (user.isEmailVerified()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		user.setEmailVerified(true);
	}

}