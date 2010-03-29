package scrum.client.project;

public class RejectRequirementAction extends GRejectRequirementAction {

	public RejectRequirementAction(scrum.client.project.Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Reject";
	}

	@Override
	public boolean isPermitted() {
		if (!requirement.getProject().isProductOwner(getCurrentUser())) return false;
		return true;
	}

	@Override
	public boolean isExecutable() {
		if (!requirement.isInCurrentSprint()) return false;
		if (!requirement.isTasksClosed()) return false;
		if (requirement.isClosed()) return false;
		if (requirement.isRejected()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.reject();
	}

}