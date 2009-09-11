package scrum.client.tasks;

import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.AScrumAction;
import scrum.client.sprint.Task;

public class ReopenTaskAction extends AScrumAction {

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
		ScrumGwtApplication.get().postSystemMessage(
			getUser().getName() + " re-opened task " + task.getReference() + ".", true);
	}

}
