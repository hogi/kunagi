package scrum.server.journal;

import ilarkesto.auth.Auth;
import scrum.server.admin.User;

public class Change extends GChange {

	public boolean isVisibleFor(User user) {
		return Auth.isVisible(getParent(), user);
	}

	public boolean isEditableBy(User user) {
		return false;
	}

	@Override
	public String toString() {
		return getUser() + " on " + getDateAndTime() + ": " + getParent() + " ." + getProperty();
	}

}