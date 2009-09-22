package scrum.client.project;

import scrum.client.ScrumGwtApplication;
import scrum.client.context.ProjectContext;

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
		ProjectContext.get().showQualityBacklog(quality);
	}

}
