package scrum.client.project;

import scrum.client.ScrumGwtApplication;

import com.google.gwt.user.client.Window;

public class DeleteProjectAction extends GDeleteProjectAction {

	public DeleteProjectAction(Project project) {
		super(project);
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