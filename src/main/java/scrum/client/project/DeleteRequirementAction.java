package scrum.client.project;

public class DeleteRequirementAction extends GDeleteRequirementAction {

	protected DeleteRequirementAction(Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		return "Delete this requirement.";
	}

	@Override
	public boolean isExecutable() {
		if (!requirement.getProject().isProductOwner(getCurrentUser())) return false;
		if (getCurrentProject().isCurrentSprint(requirement.getSprint())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		getCurrentProject().deleteRequirement(requirement);
	}

}
