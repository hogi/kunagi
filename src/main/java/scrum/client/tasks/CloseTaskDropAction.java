package scrum.client.tasks;

import scrum.client.dnd.BlockListDropAction;
import scrum.client.project.Requirement;
import scrum.client.sprint.CloseTaskAction;
import scrum.client.sprint.Task;

public class CloseTaskDropAction implements BlockListDropAction<Task> {

	private Requirement requirement;

	public CloseTaskDropAction(Requirement requirement) {
		this.requirement = requirement;
	}

	public boolean execute(Task task) {
		task.setRequirement(this.requirement);
		new CloseTaskAction(task).execute();
		return true;
	}

}
