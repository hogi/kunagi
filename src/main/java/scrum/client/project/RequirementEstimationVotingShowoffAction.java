package scrum.client.project;

public class RequirementEstimationVotingShowoffAction extends GRequirementEstimationVotingShowoffAction {

	public RequirementEstimationVotingShowoffAction(scrum.client.project.Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Showoff";
	}

	@Override
	protected void onExecute() {
		requirement.activateWorkEstimationVotingShowoff();
	}

}