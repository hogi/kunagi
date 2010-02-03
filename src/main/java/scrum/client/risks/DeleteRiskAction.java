package scrum.client.risks;

import scrum.client.common.TooltipBuilder;

public class DeleteRiskAction extends GDeleteRiskAction {

	protected DeleteRiskAction(Risk risk) {
		super(risk);
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Delete this Risk");

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
		getCurrentProject().deleteRisk(risk);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Delete " + risk.getReference() + " " + risk.getLabel();
		}

		@Override
		protected void onUndo() {
			cm.getDao().createRisk(risk);
		}

	}

}
