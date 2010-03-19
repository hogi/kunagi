package scrum.client.issues;

import ilarkesto.core.scope.Scope;
import scrum.client.workspace.ProjectWorkspaceWidgets;

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
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).showIssueList(issue);
	}

}