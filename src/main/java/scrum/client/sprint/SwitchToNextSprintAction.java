package scrum.client.sprint;

import scrum.client.project.Requirement;

public class SwitchToNextSprintAction extends GSwitchToNextSprintAction {

	@Override
	public String getLabel() {
		return "Switch to this Sprint";
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void onExecute() {
		cm.getUi().lock("Switching to next Sprint");
		cm.getApp().callSwitchToNextSprint(new Runnable() {

			public void run() {
				cm.getProjectContext().showSprintBacklog((Requirement) null);
				cm.getUi().unlock();
			}
		});
	}

}
