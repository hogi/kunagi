package scrum.client.project;

import scrum.client.ScrumGwtApplication;
import scrum.client.workspace.Ui;

public class CreateQualityAction extends GCreateQualityAction {

	@Override
	public String getLabel() {
		return "Create new Quality";
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void onExecute() {
		Quality quality = ScrumGwtApplication.get().getProject().createNewQuality();
		Ui.get().showQualityBacklog(quality);
	}

}
