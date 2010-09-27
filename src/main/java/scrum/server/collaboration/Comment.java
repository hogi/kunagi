package scrum.server.collaboration;

import ilarkesto.auth.Auth;
import ilarkesto.base.Utl;
import ilarkesto.base.time.DateAndTime;
import scrum.server.admin.User;

public class Comment extends GComment implements Comparable<Comment> {

	public String getAuthorLabel() {
		if (isAuthorSet()) return getAuthor().getName();
		if (isAuthorNameSet()) return getAuthorName();
		return "anonymous";
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		if (Utl.isEmpty(getText()) && getDateAndTime().getPeriodToNow().toHours() > 1) getDao().deleteEntity(this);
		if (isAuthorSet()) setAuthorName(getAuthor().getName());
		if (!isDateAndTimeSet()) setDateAndTime(DateAndTime.now());
	}

	@Override
	public boolean isVisibleFor(User user) {
		return Auth.isVisible(getParent(), user);
	}

	public boolean isEditableBy(User user) {
		return isAuthor(user);
	}

	@Override
	public int compareTo(Comment other) {
		return Utl.compare(getDateAndTime(), other.getDateAndTime());
	}

	@Override
	public String toString() {
		return "[" + getAuthorName() + "@" + getDateAndTime() + "] " + getText();
	}

}