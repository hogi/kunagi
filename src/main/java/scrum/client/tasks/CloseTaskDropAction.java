package scrum.client.tasks;

import scrum.client.ComponentManager;
import scrum.client.dnd.BlockListDropAction;
import scrum.client.project.Requirement;
import scrum.client.sprint.Task;

public class CloseTaskDropAction implements BlockListDropAction<Task> {

	private Requirement requirement;

	public CloseTaskDropAction(Requirement requirement) {
		this.requirement = requirement;
	}

	public boolean onDrop(Task task) {
		task.setRequirement(this.requirement);
		if (!task.isDone()) task.setDone(ComponentManager.get().getAuth().getUser());
		return true;
	}

}
