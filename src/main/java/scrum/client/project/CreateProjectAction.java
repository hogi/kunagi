package scrum.client.project;

import ilarkesto.core.scope.Scope;
import scrum.client.common.TooltipBuilder;
import scrum.client.workspace.UsersWorkspaceWidgets;

public class CreateProjectAction extends GCreateProjectAction {

	@Override
	public String getLabel() {
		return "Create Project";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Create a new Project.");
		if (!getCurrentUser().isAdmin() && getDao().getSystemConfig().isProjectCreationDisabled())
			tb.addRemark("Creating new projects is disabled.");
		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentUser().isAdmin() && getDao().getSystemConfig().isProjectCreationDisabled()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		Project project = new Project(getCurrentUser());
		getDao().createProject(project);
		Scope.get().getComponent(UsersWorkspaceWidgets.class).getProjectSelector().select(project);
	}

}