package scrum.client.project;

import ilarkesto.core.scope.Scope;
import scrum.client.common.TooltipBuilder;
import scrum.client.workspace.ProjectWorkspaceWidgets;

public class CreateQualityAction extends GCreateQualityAction {

	@Override
	public String getLabel() {
		return "Create new Quality";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Create a new Quality.");

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
		Quality quality = getCurrentProject().createNewQuality();
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).showQualityBacklog(quality);
		// addUndo(new Undo(quality));
	}

	// class Undo extends ALocalUndo {
	//
	// private Quality quality;
	//
	// public Undo(Quality quality) {
	// this.quality = quality;
	// }
	//
	// @Override
	// public String getLabel() {
	// return "Create " + quality.getReference() + " " + quality.getLabel();
	// }
	//
	// @Override
	// protected void onUndo() {
	// quality.getProject().deleteQuality(quality);
	// }
	//
	// }

}
