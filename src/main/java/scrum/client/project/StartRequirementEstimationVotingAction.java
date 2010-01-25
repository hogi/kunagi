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
	public boolean isExecutable() {
		if (requirement.isWorkEstimationVotingActive()) return false;
		if (requirement.isClosed()) return false;
		if (!requirement.isDirty()) return false;
		if (requirement.isInCurrentSprint()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.activateWorkEstimationVoting();
	}

}