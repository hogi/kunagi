package scrum.client.tasks;

import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.AScrumAction;
import scrum.client.sprint.Task;

public class CloseTaskAction extends AScrumAction {

	private Task task;

	public CloseTaskAction(Task task, AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
		this.task = task;
	}

	@Override
	public String getLabel() {
		return "Close";
	}

	@Override
	public String getTooltip() {
		return "Mark task as done.";
	}

	@Override
	public boolean isExecutable() {
		return !task.isDone() && (!task.isOwnerSet() || task.isOwner(getUser()));
	}

	@Override
	protected void onExecute() {
		task.setDone(getUser());
		ScrumGwtApplication.get().postSystemMessage(getUser().getName() + " closed task " + task.getReference() + ".",
			true);
	}

}
