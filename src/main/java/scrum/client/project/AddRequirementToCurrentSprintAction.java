package scrum.client.project;

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
		requirement.setSprint(getCurrentProject().getCurrentSprint());
		cm.getChat().postSystemMessage(
			getCurrentUser().getName() + " added requirement " + requirement.getReference() + " to current sprint.", true);
	}

}
