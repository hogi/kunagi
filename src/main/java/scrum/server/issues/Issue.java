package scrum.server.issues;

import ilarkesto.base.time.Date;

public class Issue extends GIssue {

	public static final String[] TYPES = scrum.client.issues.Issue.TYPES;

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		if (!isTypeSet()) setType(TYPES[0]);
		if (!isDateSet()) setDate(Date.today());
	}

	@Override
	public String toString() {
		return "[" + getType() + "] " + getLabel();
	}
}