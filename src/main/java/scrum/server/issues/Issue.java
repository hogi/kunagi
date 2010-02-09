package scrum.server.issues;

import ilarkesto.base.time.DateAndTime;
import scrum.client.issues.IssueTypeLabelProvider;
import scrum.server.ScrumWebApplication;
import scrum.server.common.Numbered;

public class Issue extends GIssue implements Numbered {

	public static final IssueTypeLabelProvider TYPE_LABEL_PROVIDER = new IssueTypeLabelProvider();

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateIssueNumber());
	}

	public String getReference() {
		return scrum.client.issues.Issue.REFERENCE_PREFIX + getNumber();
	}

	public String getTypeLabel() {
		return TYPE_LABEL_PROVIDER.getLabel(getType());
	}

	public String getTypeAndReferenceAndLabel() {
		return getTypeLabel() + " " + getReference() + " " + getLabel();
	}

	public boolean isClosed() {
		return isCloseDateSet();
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();
		if (!isTypeSet()) setType(scrum.client.issues.Issue.INIT_TYPE);
		if (!isDateSet()) setDate(DateAndTime.now());

		// renaming: req -> sto
		if (ScrumWebApplication.REQ_RENAMING_DATE.isAfter(getLastModified())) {
			setDescription(ScrumWebApplication.convertReqToSto(getDescription()));
		}
	}

	@Override
	public String toString() {
		return getReference() + " (" + getType() + ") " + getLabel();
	}
}