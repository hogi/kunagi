package scrum.client.admin;

import scrum.client.ScrumGwtApplication;

public class CreateUserAction extends GCreateUserAction {

	@Override
	public String getLabel() {
		return "Create new User";
	}

	@Override
	public boolean isExecutable() {
		return getUser().isAdmin();
	}

	@Override
	protected void onExecute() {
		User user = new User();
		ScrumGwtApplication.get().getDao().createUser(user);
		getComponents().getHomeContext().getUserList().showUser(user);
	}

}
