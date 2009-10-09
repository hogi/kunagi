package scrum.client.project;


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
		Quality quality = getCurrentProject().createNewQuality();
		cm.getProjectContext().showQualityBacklog(quality);
	}

}
