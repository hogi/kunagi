package scrum.client.risks;

import scrum.client.common.TooltipBuilder;

public class CreateRiskAction extends GCreateRiskAction {

	@Override
	public String getLabel() {
		return "Create new Risk";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Create new Risk");

		if (!getCurrentProject().isProductOwnerOrScrumMasterOrTeamMember(getCurrentUser()))
			tb.addRemark(TooltipBuilder.NOT_SCRUMTEAM);

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isProductOwnerOrScrumMasterOrTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		Risk risk = getCurrentProject().createNewRisk();
		cm.getProjectContext().showRiskList(risk);
	}

}
