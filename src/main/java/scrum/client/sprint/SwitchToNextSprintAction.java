package scrum.client.sprint;

import scrum.client.Components;
import scrum.client.ScrumGwtApplication;
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
		getUi().getWorkspace().lock("Switching to next Sprint");
		ScrumGwtApplication.get().callSwitchToNextSprint(new Runnable() {

			public void run() {
				Components.get().getProjectContext().showSprintBacklog((Requirement) null);
			}
		});
	}

}
