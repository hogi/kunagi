package scrum.client.issues;

public class DeleteIssueAction extends GDeleteIssueAction {

	public DeleteIssueAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		return "Delete this issue.";
	}

	@Override
	public boolean isExecutable() {
		return getProject().isPig(getUser());
	}

	@Override
	protected void onExecute() {
		getProject().deleteIssue(issue);
	}

}