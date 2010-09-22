package scrum.client.sprint;

import ilarkesto.core.scope.Scope;
import scrum.client.common.TooltipBuilder;
import scrum.client.impediments.Impediment;
import scrum.client.workspace.ProjectWorkspaceWidgets;

public class CreateTaskImpedimentAction extends GCreateTaskImpedimentAction {

	public CreateTaskImpedimentAction(scrum.client.sprint.Task task) {
		super(task);
	}

	@Override
	public String getLabel() {
		return "Create Impediment";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Create new Impediment, which is blocking this Task.");
		if (!getCurrentProject().isTeamMember(getCurrentUser())) tb.addRemark(TooltipBuilder.NOT_TEAM);

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (task.isImpedimentSet()) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		Impediment impediment = getCurrentProject().createNewImpediment();
		task.setImpediment(impediment);
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).showImpedimentList(impediment);
	}
}