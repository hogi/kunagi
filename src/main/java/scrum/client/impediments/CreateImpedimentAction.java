package scrum.client.impediments;

import scrum.client.common.TooltipBuilder;

public class CreateImpedimentAction extends GCreateImpedimentAction {

	@Override
	public String getLabel() {
		return "Create new Impediment";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Create new Impediment.");
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
		Impediment impediment = getCurrentProject().createNewImpediment();
		cm.getProjectContext().showImpedimentList(impediment);
	}

}
