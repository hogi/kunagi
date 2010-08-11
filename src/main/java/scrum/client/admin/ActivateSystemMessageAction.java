package scrum.client.admin;

import ilarkesto.core.base.Str;
import ilarkesto.core.scope.Scope;

public class ActivateSystemMessageAction extends GCreateUserAction {

	@Override
	public String getLabel() {
		return "Activate";
	}

	@Override
	public boolean isExecutable() {
		if (getCurrentUser().isAdmin() == false) return false;
		if (Scope.get().getComponent(SystemMessageManager.class).getSystemMessage().isActive()) return false;
		if (Str.isBlank(Scope.get().getComponent(SystemMessageManager.class).getSystemMessage().getText()))
			return false;
		return true;
	}

	@Override
	protected void onExecute() {
		Scope.get().getComponent(SystemMessageManager.class).activateSystemMessage();
	}

}
