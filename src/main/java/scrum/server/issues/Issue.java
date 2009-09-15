package scrum.server.issues;


public class Issue extends GIssue {

	@Override
	public String toString() {
		return getLabel();
	}
}