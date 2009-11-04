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

		TooltipBuilder tb = new TooltipBuilder(
				"Mark this requirement as clean. This means, the estimated work is accurate.");

		if (!requirement.getProject().isProductOwnerOrTeamMember(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_TEAM_MEMBER_NOR_PRODUCT_OWNER);
		} else {
			if (requirement.isClosed()) tb.addRemark("Requirement is already closed.");
			if (!requirement.isDirty()) tb.addRemark("Requirement is already clean.");
			if (requirement.getEstimatedWork() == null) tb.addRemark("Requirement is not yet estimated.");
			if (requirement.isInCurrentSprint()) tb.addRemark("Requirement is in Sprint.");
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (requirement.isClosed()) return false;
		if (!requirement.isDirty()) return false;
		if (requirement.getEstimatedWork() == null) return false;
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
		requirement.setDirty(false);
	}

}
