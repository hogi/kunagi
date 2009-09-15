package scrum.client.sprint;

import scrum.client.ScrumGwtApplication;

public class ReopenTaskAction extends GReopenTaskAction {

	public ReopenTaskAction(Task task) {
		super(task);
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
