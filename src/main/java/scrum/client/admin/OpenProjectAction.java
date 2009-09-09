package scrum.client.admin;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.AAction;
import scrum.client.project.Project;
import scrum.client.workspace.Ui;

public class OpenProjectAction extends AAction {

	private Project project;

	public OpenProjectAction(Project project) {
		super(Ui.get());
		this.project = project;
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
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void onExecute() {
		ScrumGwtApplication.get().openProject(project);
	}

}
