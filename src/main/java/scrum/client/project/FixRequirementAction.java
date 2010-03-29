package scrum.client.project;

public class FixRequirementAction extends GFixRequirementAction {

	public FixRequirementAction(scrum.client.project.Requirement requirement) {
		super(requirement);
	}

	@Override
	public boolean isPermitted() {
		if (!requirement.getProject().isTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	public boolean isExecutable() {
		if (!requirement.isRejected()) return false;
		return true;
	}

	@Override
	public String getLabel() {
		return "Mark as done";
	}

	@Override
	protected void onExecute() {
		requirement.fix();
	}

}