package scrum.client.project;

import scrum.client.ScrumGwtApplication;

public class OpenProjectAction extends GOpenProjectAction {

	public OpenProjectAction(Project project) {
		super(project);
	}

	@Override
	public String getLabel() {
		return "Open Project";
	}

	@Override
	public String getTooltip() {
		return "Open this project.";
	}

	@Override
	protected void onExecute() {
		ScrumGwtApplication.get().openProject(project);
	}

}
