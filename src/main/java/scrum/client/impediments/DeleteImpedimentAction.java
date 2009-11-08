package scrum.client.impediments;

import scrum.client.common.TooltipBuilder;

public class DeleteImpedimentAction extends GDeleteImpedimentAction {

	public DeleteImpedimentAction(Impediment impediment) {
		super(impediment);
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Delete this Impediment.");
		if (!impediment.getProject().isProductOwnerOrScrumMasterOrTeamMember(getCurrentUser()))
			tb.addRemark(TooltipBuilder.NOT_ANYTHING);

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
		getCurrentProject().deleteImpediment(impediment);
	}

}
