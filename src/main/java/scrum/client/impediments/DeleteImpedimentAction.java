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
		getCurrentProject().deleteImpediment(impediment);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Delete " + impediment.getReference() + " " + impediment.getLabel();
		}

		@Override
		protected void onUndo() {
			getDao().createImpediment(impediment);
		}

	}

}
