package scrum.server.issues;

public class Issue extends GIssue {

	public static final String[] TYPES = scrum.client.issues.Issue.TYPES;

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		if (!isTypeSet()) setType(TYPES[0]);
	}

	@Override
	public String toString() {
		return "[" + getType() + "] " + getLabel();
	}
}