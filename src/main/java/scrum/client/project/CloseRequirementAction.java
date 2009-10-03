package scrum.client.project;

public class CloseRequirementAction extends GCloseRequirementAction {

	public CloseRequirementAction(scrum.client.project.Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Close";
	}

	@Override
	public boolean isExecutable() {
		if (requirement.isClosed()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.setClosed(true);
	}

}