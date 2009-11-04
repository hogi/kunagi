package scrum.client.sprint;

import scrum.client.common.TooltipBuilder;

public class CloseTaskAction extends GCloseTaskAction {

	public CloseTaskAction(Task task) {
		super(task);
	}

	@Override
	public String getLabel() {
		return "Close";
	}

	@Override
	public String getTooltip() {

		TooltipBuilder tb = new TooltipBuilder("Mark Task as done.");

		if (!getCurrentProject().isTeamMember(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_A_TEAM_MEMBER);
		} else {
			if (task.isClosed()) tb.addRemark("Task is already closed.");
			if (task.isOwnerSet() && !task.isOwner(getCurrentUser())) tb.addRemark("Another user owns this Task.");
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {

		if (task.isClosed()) return false;
		if (task.isOwnerSet() && !task.isOwner(getCurrentUser())) return false;
		return true;

	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		task.setDone(getCurrentUser());
	}

}
