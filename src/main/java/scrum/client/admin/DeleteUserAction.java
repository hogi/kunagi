package scrum.client.admin;

import scrum.client.ScrumGwtApplication;

public class DeleteUserAction extends GDeleteUserAction {

	public DeleteUserAction(User user) {
		super(user);
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		return "Delete this user.";
	}

	@Override
	public boolean isExecutable() {
		return getUser().isAdmin();
	}

	@Override
	protected void onExecute() {
		ScrumGwtApplication.get().getDao().deleteUser(user);
	}

}
