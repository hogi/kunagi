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
		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	public boolean isPermitted() {
		return true;
	}

	@Override
	protected void onExecute() {
		Project project = new Project(getCurrentUser());
		getDao().createProject(project);
		Scope.get().getComponent(UsersWorkspaceWidgets.class).getProjectSelector().select(project);
	}

}