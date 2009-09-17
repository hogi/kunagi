package scrum.client.project;

import scrum.client.ScrumGwtApplication;

public class ChangeProjectAction extends GChangeProjectAction {

	@Override
	public String getLabel() {
		return "Change Project";
	}

	@Override
	public boolean isExecutable() {
		return getProject() != null;
	}

	@Override
	protected void onExecute() {
		ScrumGwtApplication.get().closeProject();
	}

}