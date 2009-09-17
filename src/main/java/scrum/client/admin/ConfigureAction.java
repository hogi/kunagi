package scrum.client.admin;

import scrum.client.workspace.Ui;

public class ConfigureAction extends GConfigureAction {

	@Override
	public String getLabel() {
		return "Config";
	}

	@Override
	public boolean isExecutable() {
		return getUser() != null;
	}

	@Override
	protected void onExecute() {
		Ui.get().showConfiguration();
	}
}