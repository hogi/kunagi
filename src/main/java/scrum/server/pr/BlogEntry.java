package scrum.server.pr;

import scrum.server.admin.User;

public class BlogEntry extends GBlogEntry {

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	public String getReferenceAndLabel() {
		return getReference() + " (" + getTitle() + ")";
	}

	public String getReference() {
		return scrum.client.pr.BlogEntry.REFERENCE_PREFIX + getNumber();
	}

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateRequirementNumber());
	}

	@Override
	public String toString() {
		return getReferenceAndLabel();
	}
}