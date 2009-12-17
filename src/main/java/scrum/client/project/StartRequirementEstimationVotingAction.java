package scrum.client.project;

public class StartRequirementEstimationVotingAction extends GStartRequirementEstimationVotingAction {

	public StartRequirementEstimationVotingAction(scrum.client.project.Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Start Planning Poker";
	}

	@Override
	protected void onExecute() {
		requirement.activateWorkEstimationVoting();
	}

}