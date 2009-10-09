package scrum.client.project;

public class CreateRequirementAction extends GCreateRequirementAction {

	@Override
	public String getLabel() {
		return "Create new Requirement";
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void onExecute() {
		Requirement requirement = getCurrentProject().createNewRequirement();
		cm.getProjectContext().showRequirement(requirement);
	}

}
