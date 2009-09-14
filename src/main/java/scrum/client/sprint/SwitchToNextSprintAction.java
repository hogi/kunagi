package scrum.client.sprint;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.AScrumAction;
import scrum.client.workspace.Ui;
import scrum.client.workspace.WorkareaWidget;

public class SwitchToNextSprintAction extends AScrumAction {

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
				WorkareaWidget.get().showSprintBacklog();
			}
		});
	}

}
