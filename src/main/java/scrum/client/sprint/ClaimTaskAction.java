package scrum.client.sprint;

import scrum.client.ScrumGwtApplication;

public class ClaimTaskAction extends GClaimTaskAction {

	public ClaimTaskAction(Task task) {
		super(task);
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
		return !task.isDone() && !task.isOwner(getUser());
	}

	@Override
	protected void onExecute() {
		task.claim();
		ScrumGwtApplication.get().postSystemMessage(getUser().getName() + " claimed task " + task.getReference() + ".",
			true);
	}

}
