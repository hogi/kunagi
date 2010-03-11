package scrum.client.admin;

import ilarkesto.core.scope.Scope;

public class DeactivateSystemMessageAction extends GCreateUserAction {

	@Override
	public String getLabel() {
		return "Deactivate";
	}

	@Override
	public boolean isExecutable() {
		if (getCurrentUser().isAdmin() == false) return false;
		if (Scope.get().getComponent(SystemMessageManager.class).getSystemMessage().isActive() == false) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		Scope.get().getComponent(SystemMessageManager.class).deactivateSystemMessage();
	}

}
