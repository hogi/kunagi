package scrum.client.project;

public class ResetRequirementEstimationVotingAction extends GResetRequirementEstimationVotingAction {

	public ResetRequirementEstimationVotingAction(scrum.client.project.Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Reset";
	}

	@Override
	public String getTooltip() {
		return "Remove all cards from the table to start a new round.";
	}

	@Override
	public boolean isExecutable() {
		if (!requirement.isWorkEstimationVotingActive()) return false;
		// if (!requirement.containsWorkEstimationVotes()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		getApp().callActivateRequirementEstimationVoting(requirement.getId());
	}

}