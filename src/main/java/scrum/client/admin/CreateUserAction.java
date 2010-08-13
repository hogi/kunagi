package scrum.client.admin;

import ilarkesto.core.scope.Scope;
import scrum.client.common.TooltipBuilder;
import scrum.client.workspace.UsersWorkspaceWidgets;

public class CreateUserAction extends GCreateUserAction {

	@Override
	public String getLabel() {
		return "Create User";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Create a new user.");

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		return getCurrentUser().isAdmin();
	}

	@Override
	protected void onExecute() {
		User user = new User();
		getDao().createUser(user);
		Scope.get().getComponent(UsersWorkspaceWidgets.class).getUserList().showUser(user);
	}

}
