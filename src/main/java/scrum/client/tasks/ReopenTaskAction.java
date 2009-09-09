package scrum.client.tasks;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.AAction;
import scrum.client.sprint.Task;

public class ReopenTaskAction extends AAction {

	private Task task;

	public ReopenTaskAction(Task task, AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
		this.task = task;
	}

	@Override
	public String getLabel() {
		return "Re-Open";
	}

	@Override
	public String getTooltip() {
		return "Reactivate this task.";
	}

	@Override
	public boolean isExecutable() {
		return task.isDone();
	}

	@Override
	protected void onExecute() {
		task.setUnDone(getUser());
	}

}
