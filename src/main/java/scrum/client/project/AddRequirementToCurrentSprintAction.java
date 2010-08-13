package scrum.client.project;

import scrum.client.common.TooltipBuilder;
import scrum.client.sprint.Task;

public class AddRequirementToCurrentSprintAction extends GAddRequirementToCurrentSprintAction {

	public AddRequirementToCurrentSprintAction(Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Pull to Sprint";
	}

	@Override
	public String getTooltip() {

		TooltipBuilder tb = new TooltipBuilder("Add this Story to the current Sprint Backlog.");

		if (!getCurrentProject().isTeamMember(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_TEAM);
		} else {
			if (requirement.isClosed()) tb.addRemark("Story is already closed.");
			if (!requirement.isEstimatedWorkValid()) tb.addRemark("Story has no confirmed estimation yet.");
			if (isCurrentSprint(requirement.getSprint())) tb.addRemark("Story is already in current sprint.");
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (requirement.isClosed()) return false;
		if (!requirement.isEstimatedWorkValid()) return false;
		if (isCurrentSprint(requirement.getSprint())) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		for (Task task : requirement.getTasks()) {
			task.setBurnedWork(0);
		}
		requirement.setSprint(getCurrentProject().getCurrentSprint());
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Add to Sprint for " + requirement.getReferenceAndLabel();
		}

		@Override
		protected void onUndo() {
			requirement.setSprint(null);
		}

	}

}
