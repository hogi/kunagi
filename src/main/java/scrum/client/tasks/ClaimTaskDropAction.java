package scrum.client.tasks;

import scrum.client.ComponentManager;
import scrum.client.dnd.BlockListDropAction;
import scrum.client.project.Requirement;
import scrum.client.sprint.Task;

public class ClaimTaskDropAction implements BlockListDropAction<Task> {

	private Requirement requirement;

	public ClaimTaskDropAction(Requirement requirement) {
		this.requirement = requirement;
	}

	public boolean onDrop(Task task) {
		task.setRequirement(this.requirement);
		task.claim();
		ComponentManager.get().getUi().getWorkspace().update();
		return true;
	}
}
