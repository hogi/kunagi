package scrum.client.tasks;

import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.AAction;
import scrum.client.sprint.Task;

public class ClaimTaskAction extends AAction {

	private Task task;

	public ClaimTaskAction(Task task, AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
		this.task = task;
	}

	@Override
	public String getLabel() {
		return "Claim";
	}

	@Override
	public String getTooltip() {
		return "Claim ownership for this task.";
	}

	@Override
	public boolean isExecutable() {
		return !task.isDone() && !task.isOwner(ScrumGwtApplication.get().getUser());
	}

	@Override
	protected void onExecute() {
		task.setOwner(ScrumGwtApplication.get().getUser());
	}

}
