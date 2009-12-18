package scrum.client.project;

public class RequirementEstimationVotingShowoffAction extends GRequirementEstimationVotingShowoffAction {

	public RequirementEstimationVotingShowoffAction(scrum.client.project.Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Planning Poker Showoff";
	}

	@Override
	public boolean isExecutable() {
		if (!requirement.isWorkEstimationVotingActive()) return false;
		if (requirement.isWorkEstimationVotingShowoff()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.activateWorkEstimationVotingShowoff();
	}

}