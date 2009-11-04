package scrum.client.project;

import scrum.client.common.TooltipBuilder;

public class CreateRequirementAction extends GCreateRequirementAction {

	@Override
	public String getLabel() {
		return "Create new Requirement";
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Create a new Requirement.");

		if (!getCurrentProject().isProductOwner(getCurrentUser())) tb.addRemark(TooltipBuilder.NOT_A_PRODUCT_OWNER);

		return tb.getTooltip();
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isProductOwner(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		Requirement requirement = getCurrentProject().createNewRequirement();
		cm.getProjectContext().showRequirement(requirement);
	}

}
