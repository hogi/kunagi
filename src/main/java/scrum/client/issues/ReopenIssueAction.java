package scrum.client.issues;

import ilarkesto.core.scope.Scope;
import scrum.client.workspace.ProjectWorkspaceWidgets;

public class ReopenIssueAction extends GReopenIssueAction {

	public ReopenIssueAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public boolean isPermitted() {
		if (!issue.getProject().isProductOwner(getCurrentUser())) return false;
		return true;
	}

	@Override
	public boolean isExecutable() {
		if (issue.isOpen()) return false;
		return true;
	}

	@Override
	public String getLabel() {
		return issue.isClosed() ? "Re-Open" : "Move to inbox";
	}

	@Override
	protected void onExecute() {
		issue.reopen();
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).showIssueList(issue);
	}

}