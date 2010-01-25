package scrum.client.project;

public class CloseRequirementEstimationVotingAction extends GCloseRequirementEstimationVotingAction {

	public CloseRequirementEstimationVotingAction(scrum.client.project.Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Close Planning Poker";
	}

	@Override
	public boolean isExecutable() {
		if (!requirement.isWorkEstimationVotingActive()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.deactivateWorkEstimationVoting();
	}

}