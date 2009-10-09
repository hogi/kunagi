package scrum.client.admin;

import scrum.client.ScrumGwtApplication;

public class CreateUserAction extends GCreateUserAction {

	@Override
	public String getLabel() {
		return "Create new User";
	}

	@Override
	public boolean isExecutable() {
		return getCurrentUser().isAdmin();
	}

	@Override
	protected void onExecute() {
		User user = new User();
		ScrumGwtApplication.get().getDao().createUser(user);
		cm.getHomeContext().getUserList().showUser(user);
	}

}
