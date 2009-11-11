package scrum.client.tasks;

import ilarkesto.gwt.client.undo.AUndoOperation;
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
		Requirement requirement = task.getRequirement();
		task.setRequirement(this.requirement);
		if (!task.isClosed()) task.setDone(ComponentManager.get().getAuth().getUser());
		ComponentManager.get().getUndo().getManager().add(new Undo(task, requirement));
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
			return "Undo Close/Change Requirement for " + task.getReference() + " " + task.getLabel();
		}

		@Override
		protected void onUndo() {
			task.setRequirement(requirement);
			task.setUnDone(ComponentManager.get().getAuth().getUser());
			ComponentManager.get().getEventBus().fireVisibleDataChanged();
		}

	}

}
