package scrum.client.project;

public class CreateExampleProjectAction extends GCreateExampleProjectAction {

	@Override
	public String getLabel() {
		return "Create Example Project";
	}

	@Override
	protected void onExecute() {
		getApp().callCreateExampleProject();
	}

}