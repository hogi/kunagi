package scrum.client.project;

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
		return getCurrentUser().isAdmin();
	}

	@Override
	protected void onExecute() {
		if (Window.confirm("Delete project " + project.getLabel() + "?")) {
			cm.getDao().deleteProject(project);
		}
	}

}