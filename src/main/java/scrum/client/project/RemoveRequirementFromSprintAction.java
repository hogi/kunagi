package scrum.client.project;

import scrum.client.common.TooltipBuilder;

public class RemoveRequirementFromSprintAction extends GRemoveRequirementFromSprintAction {

	protected RemoveRequirementFromSprintAction(Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Remove from Sprint";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Remove this Requirement from Sprint.");

		if (!requirement.getProject().isProductOwner(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_A_PRODUCT_OWNER);
		} else {
			if (!requirement.isSprintSet()) tb.addRemark("Requirement is not in Sprint.");
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (!requirement.isSprintSet()) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!requirement.getProject().isProductOwner(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.setSprint(null);
		cm.getChat()
				.postSystemMessage(
					getCurrentUser().getName() + " removed requirement " + requirement.getReference()
							+ " from current sprint.", true);
	}

}
