package scrum.client.admin;

public class DeactivateSystemMessageAction extends GCreateUserAction {

	@Override
	public String getLabel() {
		return "Deactivate";
	}

	@Override
	public boolean isExecutable() {
		if (getCurrentUser().isAdmin() == false) return false;
		if (cm.getSystemMessageManager().getSystemMessage().isActive() == false) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		cm.getSystemMessageManager().deactivateSystemMessage();
	}

}
