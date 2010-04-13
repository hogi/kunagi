package scrum.client.project;

import scrum.client.common.TooltipBuilder;

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
		getNavigator().gotoProjectSelector();
	}

}