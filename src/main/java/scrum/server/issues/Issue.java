package scrum.server.issues;

import ilarkesto.base.time.Date;
import scrum.server.common.Numbered;

public class Issue extends GIssue implements Numbered {

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateIssueNumber());
	}

	public String getReference() {
		return scrum.client.issues.Issue.REFERENCE_PREFIX + getNumber();
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();
		if (!isTypeSet()) setType(scrum.client.issues.Issue.INIT_TYPE);
		if (!isDateSet()) setDate(Date.today());
	}

	@Override
	public String toString() {
		return getReference() + " (" + getType() + ") " + getLabel();
	}
}