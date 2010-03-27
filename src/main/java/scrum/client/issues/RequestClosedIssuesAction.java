package scrum.client.issues;

import ilarkesto.core.scope.Scope;

public class RequestClosedIssuesAction extends GRequestClosedIssuesAction {

	@Override
	public String getLabel() {
		return "Show closed issues";
	}

	@Override
	protected void onExecute() {
		Scope.get().getComponent(IssueManager.class).setClosedIssuesVisible(true);
	}

}