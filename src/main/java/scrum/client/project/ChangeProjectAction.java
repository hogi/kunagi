package scrum.client.project;

import scrum.client.ScrumScopeManager;
import scrum.client.common.TooltipBuilder;

public class ChangeProjectAction extends GChangeProjectAction {

	@Override
	public String getLabel() {
		return "Change Project";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Switch to another Project.");

		if (!cm.getProjectContext().isProjectOpen()) tb.addRemark("No project is open yet.");

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (!cm.getProjectContext().isProjectOpen()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		cm.getProjectContext().closeProject();
		ScrumScopeManager.destroyProjectScope();
		cm.getHomeContext().activate();
	}

}