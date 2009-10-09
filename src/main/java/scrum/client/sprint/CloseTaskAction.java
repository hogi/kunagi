package scrum.client.sprint;


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
		return !task.isDone() && (!task.isOwnerSet() || task.isOwner(getCurrentUser()));
	}

	@Override
	protected void onExecute() {
		task.setDone(getCurrentUser());
	}

}
