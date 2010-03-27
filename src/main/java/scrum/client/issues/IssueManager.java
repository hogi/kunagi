package scrum.client.issues;

public class IssueManager extends GIssueManager {

	private boolean suspendedIssuesVisible;

	public void setSuspendedIssuesVisible(boolean suspendedIssuesVisible) {
		this.suspendedIssuesVisible = suspendedIssuesVisible;
	}

	public boolean isSuspendedIssuesVisible() {
		return suspendedIssuesVisible;
	}

}
