package scrum.client.tasks;

import scrum.client.ScrumGwtApplication;
import scrum.client.dnd.BlockListDropAction;
import scrum.client.project.Requirement;
import scrum.client.sprint.Task;

public class CloseTaskDropAction implements BlockListDropAction<Task> {

	private Requirement requirement;

	public CloseTaskDropAction(Requirement requirement) {
		this.requirement = requirement;
	}

	public boolean execute(Task task) {
		task.setRequirement(this.requirement);
		if (!task.isDone()) task.setDone(ScrumGwtApplication.get().getUser());
		return true;
	}

}
