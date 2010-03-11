package scrum.client.tasks;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.undo.AUndoOperation;
import scrum.client.ComponentManager;
import scrum.client.dnd.BlockListDropAction;
import scrum.client.project.Requirement;
import scrum.client.sprint.Task;

public class UnclaimTaskDropAction implements BlockListDropAction<Task> {

	private Requirement requirement;

	public UnclaimTaskDropAction(Requirement requirement) {
		this.requirement = requirement;
	}

	public boolean onDrop(Task task) {
		Requirement requirement = task.getRequirement();
		task.setRequirement(this.requirement);
		task.setUnOwned();
		ComponentManager.get().getEventBus().fireVisibleDataChanged();
		Scope.get().getComponent(scrum.client.undo.Undo.class).getManager().add(new Undo(task, requirement));
		return true;
	}

	class Undo extends AUndoOperation {

		private Task task;
		private Requirement requirement;

		public Undo(Task task, Requirement requirement) {
			this.task = task;
			this.requirement = requirement;
		}

		@Override
		public String getLabel() {
			return "Undo Unclaim/Change Story for " + task.getReference() + " " + task.getLabel();
		}

		@Override
		protected void onUndo() {
			task.setRequirement(requirement);
			task.claim();
			ComponentManager.get().getEventBus().fireVisibleDataChanged();
		}

	}
}
