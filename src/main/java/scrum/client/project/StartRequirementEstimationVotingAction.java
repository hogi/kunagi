package scrum.client.project;

import scrum.client.common.TooltipBuilder;
import scrum.client.estimation.ActivateRequirementEstimationVotingServiceCall;

public class StartRequirementEstimationVotingAction extends GStartRequirementEstimationVotingAction {

	public StartRequirementEstimationVotingAction(scrum.client.project.Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Start Planning Poker";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Open the Planning Poker table for this Story.");

		if (!requirement.getProject().isTeamMember(getCurrentUser())) tb.addRemark(TooltipBuilder.NOT_TEAM);

		return tb.getTooltip();
	}

	@Override
	public boolean isPermitted() {
		if (!requirement.getProject().isTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	public boolean isExecutable() {
		if (requirement.isWorkEstimationVotingActive()) return false;
		if (requirement.isClosed()) return false;
		if (requirement.isInCurrentSprint()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		new ActivateRequirementEstimationVotingServiceCall(requirement.getId()).execute();
	}

}