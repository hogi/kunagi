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

		TooltipBuilder tb = new TooltipBuilder("Unclaim ownership for this Task.");

		if (!getCurrentProject().isTeamMember(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_TEAM);
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
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Unclaim " + task.getReference() + " " + task.getLabel();
		}

		@Override
		protected void onUndo() {
			task.claim();
		}

	}

}
