package scrum.client.issues;

public class RequestClosedIssuesAction extends GRequestClosedIssuesAction {

	@Override
	public String getLabel() {
		return "Request closed issues";
	}

	@Override
	protected void onExecute() {
		getApp().callRequestClosedIssues();
	}

}