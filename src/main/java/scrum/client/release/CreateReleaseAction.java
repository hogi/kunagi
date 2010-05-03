package scrum.client.release;

import ilarkesto.core.scope.Scope;
import scrum.client.common.TooltipBuilder;
import scrum.client.workspace.ProjectWorkspaceWidgets;

public class CreateReleaseAction extends GCreateReleaseAction {

	@Override
	public String getLabel() {
		return "Create new Release";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Create new Release");

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
		Release release = getCurrentProject().createNewRelease();
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).showRelease(release);
	}

}