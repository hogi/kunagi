package scrum.server.release;

import scrum.server.admin.User;

public class Release extends GRelease {

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	@Override
	public String toString() {
		return getLabel();
	}

}