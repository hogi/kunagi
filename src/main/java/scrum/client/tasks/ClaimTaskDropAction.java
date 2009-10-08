package scrum.client.tasks;

import scrum.client.Components;
import scrum.client.dnd.BlockListDropAction;
import scrum.client.project.Requirement;
import scrum.client.sprint.Task;

public class ClaimTaskDropAction implements BlockListDropAction<Task> {

	private Requirement requirement;

	public ClaimTaskDropAction(Requirement requirement) {
		this.requirement = requirement;
	}

	public boolean execute(Task task) {
		task.setRequirement(this.requirement);
		task.claim();
		Components.get().getUi().getWorkspace().update();
		return true;
	}
}
