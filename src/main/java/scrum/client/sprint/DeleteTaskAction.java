package scrum.client.sprint;

import scrum.client.common.TooltipBuilder;

public class DeleteTaskAction extends GDeleteTaskAction {

	public DeleteTaskAction(Task task) {
		super(task);
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Delete this Task.");

		if (!getCurrentProject().isTeamMember(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_A_TEAM_MEMBER);
		} else {
			if (task.isOwnerSet() && !task.isOwner(getCurrentUser())) tb.addRemark("Another user owns this Task.");
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
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
		task.getRequirement().deleteTask(task);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Delete " + task.getReference() + " " + task.getLabel();
		}

		@Override
		protected void onUndo() {
			cm.getDao().createTask(task);
		}

	}

}
