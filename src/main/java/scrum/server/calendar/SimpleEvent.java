package scrum.server.calendar;

import scrum.server.admin.User;

public class SimpleEvent extends GSimpleEvent {

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

}