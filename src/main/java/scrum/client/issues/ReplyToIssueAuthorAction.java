package scrum.client.issues;

import ilarkesto.core.base.Str;

public class ReplyToIssueAuthorAction extends GReplyToIssueAuthorAction {

	public ReplyToIssueAuthorAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public String getLabel() {
		return "Reply by email";
	}

	@Override
	public String getTooltip() {
		return "Reply to the issue author by email.";
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isScrumTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	public boolean isExecutable() {
		if (Str.isBlank(issue.getIssuerEmail())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		new ReplyToIssueDialog(issue).showDialog();
	}

}