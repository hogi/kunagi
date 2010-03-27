package scrum.client.issues;

import ilarkesto.core.scope.Scope;

public class HideSuspendedIssuesAction extends GHideSuspendedIssuesAction {

	@Override
	public String getLabel() {
		return "Hide suspended issues";
	}

	@Override
	protected void onExecute() {
		Scope.get().getComponent(IssueManager.class).setSuspendedIssuesVisible(false);
	}

}