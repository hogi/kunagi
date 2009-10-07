package scrum.server.issues;

import ilarkesto.base.time.Date;
import scrum.server.common.Numbered;

public class Issue extends GIssue implements Numbered {

	public static final String[] TYPES = scrum.client.issues.Issue.TYPES;

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateIssueNumber());
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();
		if (!isTypeSet()) setType(TYPES[0]);
		if (!isDateSet()) setDate(Date.today());
	}

	@Override
	public String toString() {
		return "[" + getType() + "] " + getLabel();
	}
}