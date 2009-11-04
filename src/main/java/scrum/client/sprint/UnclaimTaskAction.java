package scrum.client.sprint;

import scrum.client.common.TooltipBuilder;

public class UnclaimTaskAction extends GReopenTaskAction {

	public UnclaimTaskAction(Task task) {
		super(task);
	}

	@Override
	public String getLabel() {
		return "Unclaim";
	}

	@Override
	public String getTooltip() {

		TooltipBuilder tb = new TooltipBuilder("Remove ownership for this Task.");

		if (!getCurrentProject().isTeamMember(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_A_TEAM_MEMBER);
		} else {
			if (task.isClosed()) tb.addRemark("Task is already closed.");
			if (!task.isOwner(getCurrentUser())) tb.addRemark("You are not the owner of this Task.");
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {

		if (task.isClosed()) return false;
		if (!task.isOwner(getCurrentUser())) return false;

		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		task.setUnOwned();
	}

}
