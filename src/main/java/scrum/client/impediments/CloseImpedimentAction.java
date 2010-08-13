package scrum.client.impediments;

import scrum.client.common.TooltipBuilder;

public class CloseImpedimentAction extends GCloseImpedimentAction {

	public CloseImpedimentAction(scrum.client.impediments.Impediment impediment) {
		super(impediment);
	}

	@Override
	public String getLabel() {
		return "Close";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Close this Impediment, marking it as solved or obsolete.");
		if (!impediment.getProject().isProductOwnerOrScrumMasterOrTeamMember(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_SCRUMTEAM);
		} else {
			if (impediment.isClosed()) tb.addRemark("Impediment is already closed.");
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (impediment.isClosed()) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!impediment.getProject().isProductOwnerOrScrumMasterOrTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		impediment.setClosed(true);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Close " + impediment.getReference() + " " + impediment.getLabel();
		}

		@Override
		protected void onUndo() {
			impediment.setClosed(false);
		}

	}

}