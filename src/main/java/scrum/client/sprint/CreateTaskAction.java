package scrum.client.sprint;

import ilarkesto.core.scope.Scope;
import scrum.client.common.TooltipBuilder;
import scrum.client.project.Requirement;
import scrum.client.workspace.ProjectWorkspaceWidgets;

public class CreateTaskAction extends GCreateTaskAction {

	private Requirement requirement;

	public CreateTaskAction(Requirement requirement) {
		this.requirement = requirement;
	}

	@Override
	public String getLabel() {
		return "Create Task";
	}

	@Override
	public String getTooltip() {

		TooltipBuilder tb = new TooltipBuilder("Create a new Task for this Story.");

		if (!getCurrentProject().isTeamMember(getCurrentUser())) tb.addRemark(TooltipBuilder.NOT_TEAM);

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		Task task = requirement.createNewTask();
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).showTask(task);
	}

}
