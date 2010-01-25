package scrum.client.sprint;

import scrum.client.common.TooltipBuilder;
import scrum.client.project.Requirement;

public class CreateTaskAction extends GCreateTaskAction {

	private Requirement requirement;

	public CreateTaskAction(Requirement requirement) {
		this.requirement = requirement;
	}

	@Override
	public String getLabel() {
		return "Create new Task";
	}

	@Override
	public String getTooltip() {

		TooltipBuilder tb = new TooltipBuilder("Create a new Task for this Requirement.");

		if (!getCurrentProject().isTeamMember(getCurrentUser())) tb.addRemark(TooltipBuilder.NOT_A_TEAM_MEMBER);

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
		cm.getProjectContext().showTask(task);
	}

}
