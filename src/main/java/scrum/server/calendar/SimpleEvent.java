package scrum.server.calendar;

import ilarkesto.base.time.Date;
import scrum.server.admin.User;
import scrum.server.common.Numbered;

public class SimpleEvent extends GSimpleEvent implements Numbered {

	public String getReferenceAndLabel() {
		return getReference() + " " + getLabel();
	}

	public String getReference() {
		return scrum.client.calendar.SimpleEvent.REFERENCE_PREFIX + getNumber();
	}

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateEventNumber());
	}

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	@Override
	public void ensureIntegrity() {
		if (!isDateSet()) setDate(Date.today());
		updateNumber();
		super.ensureIntegrity();
	}

	@Override
	public String toString() {
		return getReferenceAndLabel();
	}

}