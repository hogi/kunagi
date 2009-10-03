package scrum.client.project;

import scrum.client.ScrumGwtApplication;

public class AddRequirementToCurrentSprintAction extends GAddRequirementToCurrentSprintAction {

	public AddRequirementToCurrentSprintAction(Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Add to current Sprint";
	}

	@Override
	public String getTooltip() {
		return "Add this requirement the the current Sprint.";
	}

	@Override
	public boolean isExecutable() {
		if (requirement.isClosed()) return false;
		if (!requirement.isEstimatedWorkValid()) return false;
		if (isCurrentSprint(requirement.getSprint())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.setSprint(getProject().getCurrentSprint());
		ScrumGwtApplication.get().postSystemMessage(
			getUser().getName() + " added requirement " + requirement.getReference() + " to current sprint.", true);
	}

}
