package scrum.client.sprint;

import scrum.client.project.Requirement;
import scrum.client.workspace.Ui;

public class CreateTaskAction extends GCreateTaskAction {

	private Requirement requirement;

	public CreateTaskAction(Requirement requirement) {
		this.requirement = requirement;
	}

	@Override
	public String getLabel() {
		return "Create new Task";
	}

	@Override
	public String getTooltip() {
		return "Create a new Task for this Requirement.";
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void onExecute() {
		Task task = requirement.createNewTask();
		Ui.get().showTask(task);
	}

}
