package scrum.client.issues;

public class UnsuspendIssueAction extends GUnsuspendIssueAction {

	public UnsuspendIssueAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public String getLabel() {
		return "Unsuspend";
	}

	@Override
	public String getTooltip() {
		return "Unhide this issue.";
	}

	@Override
	public boolean isExecutable() {
		if (!issue.isSuspended()) return false;
		if (!issue.isOpen()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		issue.unsuspend();
	}

}