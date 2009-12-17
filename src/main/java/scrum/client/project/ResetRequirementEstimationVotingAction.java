package scrum.client.project;

public class ResetRequirementEstimationVotingAction extends GResetRequirementEstimationVotingAction {

	public ResetRequirementEstimationVotingAction(scrum.client.project.Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Reset Planning Poker";
	}

	@Override
	protected void onExecute() {
		requirement.resetWorkEstimationVoting();
	}

}