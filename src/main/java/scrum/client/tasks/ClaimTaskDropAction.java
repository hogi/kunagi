package scrum.client.tasks;

import scrum.client.dnd.BlockListDropAction;
import scrum.client.project.Requirement;
import scrum.client.sprint.ClaimTaskAction;
import scrum.client.sprint.Task;

public class ClaimTaskDropAction implements BlockListDropAction<Task> {

	private Requirement requirement;

	public ClaimTaskDropAction(Requirement requirement) {
		this.requirement = requirement;
	}

	public boolean execute(Task task) {
		task.setRequirement(this.requirement);
		new ClaimTaskAction(task).execute();
		return true;
	}
}
