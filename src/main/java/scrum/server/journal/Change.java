package scrum.server.journal;

import ilarkesto.auth.Auth;
import ilarkesto.persistence.AEntity;
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
		AEntity parent;
		try {
			parent = getParent();
		} catch (Throwable ex) {
			parent = null;
		}
		return getUser() + " on " + getDateAndTime() + ": " + parent + " ." + getKey();
	}
}