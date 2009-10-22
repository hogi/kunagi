package scrum.client.sprint;

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
		return task.isClosed();
	}

	@Override
	protected void onExecute() {
		task.setUnDone(getCurrentUser());
		cm.getChat().postSystemMessage(getCurrentUser().getName() + " re-opened task " + task.getReference() + ".", true);
	}

}
