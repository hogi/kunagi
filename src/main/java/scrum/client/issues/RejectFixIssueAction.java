package scrum.client.issues;

public class RejectFixIssueAction extends GRejectFixIssueAction {

	public RejectFixIssueAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public String getLabel() {
		return "Reject fix";
	}

	@Override
	public boolean isExecutable() {
		if (!issue.isUrgent()) return false;
		if (issue.isClosed()) return false;
		if (!issue.isFixed()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		issue.rejectFix();
	}

}