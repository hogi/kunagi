package scrum.server.collaboration;

import scrum.server.admin.User;

public class Subject extends GSubject {

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateSubjectNumber());
	}

	public String getReferenceAndLabel() {
		return getReference() + " " + getLabel();
	}

	public String getReference() {
		return scrum.client.collaboration.Subject.REFERENCE_PREFIX + getNumber();
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();
	}

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	@Override
	public String toString() {
		return getReferenceAndLabel();
	}

}