package scrum.server.pr;

import ilarkesto.base.Utl;
import ilarkesto.base.time.DateAndTime;
import scrum.server.admin.User;
import scrum.server.common.Numbered;

public class BlogEntry extends GBlogEntry implements Numbered, Comparable<BlogEntry> {

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	public String getReferenceAndLabel() {
		return getReference() + " " + getTitle();
	}

	public String getReference() {
		return scrum.client.pr.BlogEntry.REFERENCE_PREFIX + getNumber();
	}

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateBlogEntryNumber());
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();
		if (!isDateAndTimeSet()) setDateAndTime(DateAndTime.now());
	}

	@Override
	public int compareTo(BlogEntry other) {
		return Utl.compare(getDateAndTime(), other.getDateAndTime()) * -1;
	}

	@Override
	public String toString() {
		return getReferenceAndLabel();
	}
}