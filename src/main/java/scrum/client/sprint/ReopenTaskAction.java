package scrum.client.sprint;

import scrum.client.common.TooltipBuilder;

public class ReopenTaskAction extends GReopenTaskAction {

	public ReopenTaskAction(Task task) {
		super(task);
	}

	@Override
	public String getLabel() {
		return "Reopen";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder(
				"Reopen this Task, stating that is is not yet done or needs to be performed again.");

		if (!getCurrentProject().isTeamMember(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_TEAM);
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
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Set Undone for " + task.getReference() + " " + task.getLabel();
		}

		@Override
		protected void onUndo() {
			task.setDone(getCurrentUser());
		}

	}

}
