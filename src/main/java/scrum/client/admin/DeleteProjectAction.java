package scrum.client.admin;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.AScrumAction;
import scrum.client.project.Project;
import scrum.client.workspace.Ui;

import com.google.gwt.user.client.Window;

public class DeleteProjectAction extends AScrumAction {

	private Project project;

	public DeleteProjectAction(Project project) {
		super(Ui.get());
		this.project = project;
	}

	@Override
	public String getLabel() {
		return "Delete Project";
	}

	@Override
	public String getTooltip() {
		return "Delete this project and destroy all its data.";
	}

	@Override
	public boolean isExecutable() {
		return getUser().isAdmin();
	}

	@Override
	protected void onExecute() {
		if (Window.confirm("Delete project " + project.getLabel() + "?")) {
			ScrumGwtApplication.get().getDao().deleteProject(project);
		}
	}

}
