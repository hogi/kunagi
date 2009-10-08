package scrum.client.project;

import scrum.client.ProjectContext;

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
		Quality quality = getProject().createNewQuality();
		getProjectContext().showQualityBacklog(quality);
	}

}
