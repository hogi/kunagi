package scrum.client.project;

import scrum.client.common.TooltipBuilder;

public class AddRequirementToCurrentSprintAction extends GAddRequirementToCurrentSprintAction {

	public AddRequirementToCurrentSprintAction(Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Add to current Sprint";
	}

	@Override
	public String getTooltip() {

		TooltipBuilder tb = new TooltipBuilder("Add this Requirement the the current Sprint.");

		if (!getCurrentProject().isTeamMember(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_A_TEAM_MEMBER);
		} else {
			if (requirement.isClosed()) tb.addRemark("Requirement is already closed.");
			if (!requirement.isEstimatedWorkValid()) tb.addRemark("Requirement has no confirmed estimation yet.");
			if (isCurrentSprint(requirement.getSprint())) tb.addRemark("Requirement is already in current sprint.");
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (requirement.isClosed()) return false;
		if (!requirement.isEstimatedWorkValid()) return false;
		if (isCurrentSprint(requirement.getSprint())) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.setSprint(getCurrentProject().getCurrentSprint());
		cm.getChat().postSystemMessage(
			getCurrentUser().getName() + " added requirement " + requirement.getReference() + " to current sprint.",
			true);
	}

}
