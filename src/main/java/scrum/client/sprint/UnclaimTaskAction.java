package scrum.client.sprint;

import scrum.client.ScrumGwtApplication;

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
		return !task.isDone() && task.isOwner(ScrumGwtApplication.get().getUser());
	}

	@Override
	protected void onExecute() {
		task.setUnOwned();
	}

}
