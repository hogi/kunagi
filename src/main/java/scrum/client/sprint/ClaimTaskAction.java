package scrum.client.sprint;

import scrum.client.admin.User;
import scrum.client.common.TooltipBuilder;

public class ClaimTaskAction extends GClaimTaskAction {

	public ClaimTaskAction(Task task) {
		super(task);
	}

	@Override
	public String getLabel() {
		return "Claim";
	}

	@Override
	public String getTooltip() {

		TooltipBuilder tb = new TooltipBuilder(
				"Claim ownership for this Task, stating that you are working on this Task.");
		if (!getCurrentProject().isTeamMember(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_TEAM);
		} else {
			if (task.isClosed()) tb.addRemark("Task is already closed.");
			if (task.isOwner(getCurrentUser())) tb.addRemark("Task is already owned by you.");
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (task.isClosed()) return false;
		if (task.isOwner(getCurrentUser())) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		User owner = task.getOwner();
		task.claim();
		addUndo(new Undo(owner));
	}

	class Undo extends ALocalUndo {

		User owner;

		public Undo(User owner) {
			this.owner = owner;
		}

		@Override
		public String getLabel() {
			return "Undo Claim " + task.getReference() + " " + task.getLabel();
		}

		@Override
		protected void onUndo() {
			task.setOwner(owner);
		}

	}

}
