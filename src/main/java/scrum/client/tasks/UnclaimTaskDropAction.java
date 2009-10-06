package scrum.client.tasks;

import scrum.client.dnd.BlockListDropAction;
import scrum.client.project.Requirement;
import scrum.client.sprint.Task;
import scrum.client.workspace.Ui;

public class UnclaimTaskDropAction implements BlockListDropAction<Task> {

	private Requirement requirement;

	public UnclaimTaskDropAction(Requirement requirement) {
		this.requirement = requirement;
	}

	public boolean execute(Task task) {
		task.setRequirement(this.requirement);
		task.setUnOwned();
		Ui.get().update();
		return true;
	}

}
