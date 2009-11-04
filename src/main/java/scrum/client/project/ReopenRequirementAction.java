package scrum.client.project;

import scrum.client.common.TooltipBuilder;

public class ReopenRequirementAction extends GReopenRequirementAction {

	public ReopenRequirementAction(scrum.client.project.Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Re-Open";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Reopen this Requirement.");

		if (!requirement.getProject().isProductOwnerOrTeamMember(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_TEAM_MEMBER_NOR_PRODUCT_OWNER);
		} else {
			if (!requirement.isClosed()) tb.addRemark("Requirement is not yet closed.");
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (!requirement.isClosed()) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!requirement.getProject().isProductOwnerOrTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.setClosed(false);
	}

}