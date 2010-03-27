package scrum.client.issues;

import ilarkesto.core.scope.Scope;

public class ShowSuspendedIssuesAction extends GShowSuspendedIssuesAction {

	@Override
	public String getLabel() {
		return "Show suspended issues";
	}

	@Override
	protected void onExecute() {
		Scope.get().getComponent(IssueManager.class).setSuspendedIssuesVisible(true);
	}

}