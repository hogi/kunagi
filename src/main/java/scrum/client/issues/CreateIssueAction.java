package scrum.client.issues;

public class CreateIssueAction extends GCreateIssueAction {

	@Override
	public String getLabel() {
		return "Create new Issue";
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void onExecute() {
		Issue issue = getProject().createNewIssue();
		cm.getProjectContext().showIssueList(issue);
	}

}
