package scrum.client.tasks;

import ilarkesto.gwt.client.undo.AUndoOperation;
import scrum.client.ComponentManager;
import scrum.client.admin.User;
import scrum.client.dnd.BlockListDropAction;
import scrum.client.project.Requirement;
import scrum.client.sprint.Task;

public class ClaimTaskDropAction implements BlockListDropAction<Task> {

	private Requirement requirement;

	public ClaimTaskDropAction(Requirement requirement) {
		this.requirement = requirement;
	}

	public boolean onDrop(Task task) {
		Requirement requirement = task.getRequirement();
		User owner = task.getOwner();
		task.setRequirement(this.requirement);
		task.claim();
		ComponentManager.get().getEventBus().fireVisibleDataChanged();
		ComponentManager.get().getUndo().getManager().add(new Undo(owner, task, requirement));
		return true;
	}

	class Undo extends AUndoOperation {

		private User owner;
		private Task task;
		private Requirement requirement;

		public Undo(User owner, Task task, Requirement requirement) {
			this.owner = owner;
			this.task = task;
			this.requirement = requirement;
		}

		@Override
		public String getLabel() {
			return "Undo Claim/Change Story for " + task.getReference() + " " + task.getLabel();
		}

		@Override
		protected void onUndo() {
			task.setRequirement(requirement);
			task.setOwner(owner);
			ComponentManager.get().getEventBus().fireVisibleDataChanged();
		}

	}
}
