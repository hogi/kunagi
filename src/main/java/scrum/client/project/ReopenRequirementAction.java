package scrum.client.project;


public class ReopenRequirementAction extends GReopenRequirementAction {

	public ReopenRequirementAction(scrum.client.project.Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Re-Open";
	}

	@Override
	public boolean isExecutable() {
		if (!requirement.isClosed()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.setClosed(false);
	}

}