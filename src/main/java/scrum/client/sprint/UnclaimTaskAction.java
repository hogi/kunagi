package scrum.client.sprint;


public class UnclaimTaskAction extends GReopenTaskAction {

	public UnclaimTaskAction(Task task) {
		super(task);
	}

	@Override
	public String getLabel() {
		return "Unclaim";
	}

	@Override
	public String getTooltip() {
		return "Remove ownership for this task.";
	}

	@Override
	public boolean isExecutable() {
		return !task.isClosed() && task.isOwner(getCurrentUser());
	}

	@Override
	protected void onExecute() {
		task.setUnOwned();
	}

}
