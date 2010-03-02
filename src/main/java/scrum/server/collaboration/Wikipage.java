package scrum.server.collaboration;

import scrum.server.admin.User;

public class Wikipage extends GWikipage {

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	@Override
	public String toString() {
		return "Wiki-Page [[" + getName() + "]]";
	}

}