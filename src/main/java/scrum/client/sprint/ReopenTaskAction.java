package scrum.client.sprint;

import scrum.client.common.TooltipBuilder;

public class ReopenTaskAction extends GReopenTaskAction {

	public ReopenTaskAction(Task task) {
		super(task);
	}

	@Override
	public String getLabel() {
		return "Re-Open";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Reactivate this Task.");

		if (!getCurrentProject().isTeamMember(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_A_TEAM_MEMBER);
		} else {
			if (!task.isClosed()) tb.addRemark("Task is not closed.");
		}

		return tb.getTooltip();

	}

	@Override
	public boolean isExecutable() {
		if (!task.isClosed()) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		task.setUnDone(getCurrentUser());
		cm.getChat().postSystemMessage(getCurrentUser().getName() + " re-opened task " + task.getReference() + ".",
			true);
	}

}
