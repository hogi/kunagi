package scrum.client.tasks;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.AAction;
import scrum.client.sprint.Task;

public class DeleteTaskAction extends AAction {

	private Task task;

	public DeleteTaskAction(Task task, AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
		this.task = task;
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		return "Delete this task.";
	}

	@Override
	public boolean isExecutable() {
		return !task.isOwnerSet() || task.isOwner(getUser());
	}

	@Override
	protected void onExecute() {
		task.getRequirement().deleteTask(task);
	}

}
