package scrum.server.pr;

import scrum.server.admin.User;

public class BlogEntry extends GBlogEntry {

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

}