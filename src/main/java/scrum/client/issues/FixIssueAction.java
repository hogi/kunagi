package scrum.client.issues;

import scrum.client.common.TooltipBuilder;

public class FixIssueAction extends GFixIssueAction {

	public FixIssueAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public String getLabel() {
		return "Mark as fixed";
	}

	@Override
	public String getTooltip() {

		TooltipBuilder tb = new TooltipBuilder("Mark Issue as fixed.");

		if (!getCurrentProject().isTeamMember(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_TEAM);
		} else {
			if (issue.isFixed()) tb.addRemark("Issue is already fixed.");
			if (issue.isClosed()) tb.addRemark("Issue is already closed.");
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (!issue.isUrgent()) return false;
		if (issue.isFixed()) return false;
		if (issue.isClosed()) return false;
		return true;

	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		issue.setFixed(getCurrentUser());
	}

}