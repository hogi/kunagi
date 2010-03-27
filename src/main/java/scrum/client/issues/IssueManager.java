package scrum.client.issues;

public class IssueManager extends GIssueManager {

	private boolean suspendedIssuesVisible;
	private boolean closedIssuesVisible;

	public void setSuspendedIssuesVisible(boolean suspendedIssuesVisible) {
		this.suspendedIssuesVisible = suspendedIssuesVisible;
	}

	public boolean isSuspendedIssuesVisible() {
		return suspendedIssuesVisible;
	}

	public boolean isClosedIssuesVisible() {
		return closedIssuesVisible;
	}

	public void setClosedIssuesVisible(boolean closedIssuesVisible) {
		if (this.closedIssuesVisible == closedIssuesVisible) return;
		this.closedIssuesVisible = closedIssuesVisible;
		if (closedIssuesVisible) {
			app.callRequestClosedIssues();
		}
	}

}
