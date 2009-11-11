package scrum.client.sprint;

import ilarkesto.gwt.client.Gwt;
import scrum.client.common.TooltipBuilder;
import scrum.client.project.Requirement;

public class SwitchToNextSprintAction extends GSwitchToNextSprintAction {

	@Override
	public String getLabel() {
		return "Switch to this Sprint";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Switch to this Sprint");
		if (!getCurrentProject().isScrumMaster(getCurrentUser())) tb.addRemark(TooltipBuilder.NOT_A_SCRUM_MASTER);
		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isScrumMaster(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		if (!Gwt.confirm("Switch to next Sprint?")) return;
		cm.getUi().lock("Switching to next Sprint");
		cm.getApp().callSwitchToNextSprint(new Runnable() {

			public void run() {
				cm.getProjectContext().showSprintBacklog((Requirement) null);
				cm.getUi().unlock();
			}
		});
	}

}
