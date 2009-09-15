package scrum.client.sprint;

import scrum.client.ScrumGwtApplication;

public class CloseTaskAction extends GCloseTaskAction {

	public CloseTaskAction(Task task) {
		super(task);
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
