package scrum.client.sprint;

public class DeleteTaskAction extends GDeleteTaskAction {

	public DeleteTaskAction(Task task) {
		super(task);
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		return "Delete this task.";
	}

	@Override
	public boolean isExecutable() {
		if (task.isOwnerSet() && !task.isOwner(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		task.getRequirement().deleteTask(task);
	}

}
