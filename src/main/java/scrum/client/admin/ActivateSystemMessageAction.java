package scrum.client.admin;

import ilarkesto.gwt.client.Gwt;

public class ActivateSystemMessageAction extends GCreateUserAction {

	@Override
	public String getLabel() {
		return "Activate";
	}

	@Override
	public boolean isExecutable() {
		if (getCurrentUser().isAdmin() == false) return false;
		if (cm.getSystemMessageManager().getSystemMessage().isActive()) return false;
		if (Gwt.isEmpty(cm.getSystemMessageManager().getSystemMessage().getText())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		cm.getSystemMessageManager().activateSystemMessage();
	}

}
