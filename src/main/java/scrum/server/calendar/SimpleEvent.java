package scrum.server.calendar;

import ilarkesto.base.time.Date;
import scrum.server.admin.User;

public class SimpleEvent extends GSimpleEvent {

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	@Override
	public void ensureIntegrity() {
		if (!isDateSet()) setDate(Date.today());
		super.ensureIntegrity();
	}

}