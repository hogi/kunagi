package scrum.client.project;

import ilarkesto.core.scope.Scope;
import scrum.client.common.TooltipBuilder;
import scrum.client.workspace.UsersWorkspaceWidgets;

public class ChangeProjectAction extends GChangeProjectAction {

	@Override
	public String getLabel() {
		return "Change Project";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Switch to another Project.");
		return tb.getTooltip();
	}

	@Override
	protected void onExecute() {
		cm.getProjectContext().closeProject();
		Scope.get().getComponent(UsersWorkspaceWidgets.class).activate();
	}

}