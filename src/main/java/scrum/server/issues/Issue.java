package scrum.server.issues;

import ilarkesto.base.time.DateAndTime;
import scrum.server.admin.User;
import scrum.server.common.Numbered;

public class Issue extends GIssue implements Numbered {

	public String getIssuer() {
		if (isCreatorSet()) return getCreator().getName();

		String name = getIssuerName();
		String email = getIssuerEmail();

		if (name == null && email == null) return null;
		if (name == null) return email;
		if (email == null) return name;

		return name + " (" + email + ")";
	}

	public boolean isBug() {
		return isAccepted() && isUrgent();
	}

	public boolean isIdea() {
		return isAccepted() && !isUrgent();
	}

	public boolean isFixed() {
		return isFixDateSet();
	}

	public boolean isOpen() {
		return !isClosed();
	}

	protected boolean isAccepted() {
		return !isClosed() && isAcceptDateSet();
	}

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateIssueNumber());
	}

	public String getReference() {
		return scrum.client.issues.Issue.REFERENCE_PREFIX + getNumber();
	}

	public String getReferenceAndLabel() {
		return getReference() + " " + getLabel();
	}

	public boolean isClosed() {
		return isCloseDateSet();
	}

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	public boolean isEditableBy(User user) {
		return getProject().isEditableBy(user);
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();
		if (!isTypeSet()) setType(scrum.client.issues.Issue.INIT_TYPE);
		if (!isDateSet()) setDate(DateAndTime.now());

	}

	@Override
	public String toString() {
		return getReferenceAndLabel();
	}

}