package scrum.client.project;

import scrum.client.common.TooltipBuilder;

public class SetRequirementDirtyAction extends GSetRequirementDirtyAction {

	protected SetRequirementDirtyAction(Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Request Re-Estimation";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder(
				"Mark this Story as dirty. This means, the estimated work needs to be reestimated.");
		if (!requirement.getProject().isProductOwnerOrTeamMember(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_TEAM_NOR_PRODUCT_OWNER);
		} else {
			if (requirement.isClosed()) tb.addRemark("Story is already closed.");
			if (requirement.isDirty()) tb.addRemark("Story is already dirty.");
			if (requirement.isInCurrentSprint()) tb.addRemark("Story is in Sprint.");
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (requirement.isClosed()) return false;
		if (requirement.isDirty()) return false;
		if (requirement.isInCurrentSprint()) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!requirement.getProject().isProductOwnerOrTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.setDirty(true);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return super.getLabel() + " for " + requirement.getReferenceAndLabel();
		}

		@Override
		protected void onUndo() {
			requirement.setDirty(false);
		}

	}

}
