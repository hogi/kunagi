package scrum.client.project;

import scrum.client.common.TooltipBuilder;

public class SetRequirementCleanAction extends GSetRequirementCleanAction {

	protected SetRequirementCleanAction(Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Confirm Estimation";
	}

	@Override
	public String getTooltip() {

		TooltipBuilder tb = new TooltipBuilder("Mark this Story as clean. This means, the estimated work is accurate.");

		if (!requirement.getProject().isTeamMember(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_TEAM);
		} else {
			if (requirement.isClosed()) tb.addRemark("Story is already closed.");
			if (!requirement.isDirty()) tb.addRemark("Story is already clean.");
			if (requirement.getEstimatedWork() == null) tb.addRemark("Story is not yet estimated.");
			if (requirement.isInCurrentSprint()) tb.addRemark("Story is in Sprint.");
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isPermitted() {
		if (!requirement.getProject().isTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	public boolean isExecutable() {
		if (requirement.isClosed()) return false;
		if (!requirement.isDirty()) return false;
		if (requirement.getEstimatedWork() == null) return false;
		if (requirement.isInCurrentSprint()) return false;
		if (requirement.isWorkEstimationVotingActive()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.setDirty(false);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Mark as clean for " + requirement.getReferenceAndLabel();
		}

		@Override
		protected void onUndo() {
			requirement.setDirty(true);
		}

	}

}
