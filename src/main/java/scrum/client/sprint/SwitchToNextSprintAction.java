package scrum.client.sprint;

import scrum.client.ScrumGwtApplication;
import scrum.client.project.Requirement;
import scrum.client.workspace.Ui;

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
		Ui.get().lock("Switching to next Sprint");
		ScrumGwtApplication.get().callSwitchToNextSprint(new Runnable() {

			public void run() {
				Ui.get().showSprintBacklog((Requirement) null);
			}
		});
	}

}
