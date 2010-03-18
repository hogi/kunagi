package scrum.client.issues;

public class ReopenIssueAction extends GReopenIssueAction {

	public ReopenIssueAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public boolean isExecutable() {
		if (issue.isOpen()) return false;
		return true;
	}

	@Override
	public String getLabel() {
		return "Reopen";
	}

	@Override
	protected void onExecute() {
		issue.reopen();
		cm.getProjectContext().showIssueList(issue);
	}

}