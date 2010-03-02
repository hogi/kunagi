package scrum.server.collaboration;

import ilarkesto.auth.Auth;
import ilarkesto.base.Utl;
import scrum.server.admin.User;

public class Comment extends GComment {

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		if (Utl.isEmpty(getText()) && getDateAndTime().getPeriodToNow().toHours() > 1) {
			getDao().deleteEntity(this);
		}

	}

	public boolean isVisibleFor(User user) {
		return Auth.isVisible(getParent(), user);
	}

	public boolean isEditableBy(User user) {
		return isAuthor(user);
	}

	@Override
	public String toString() {
		return "[" + getAuthor() + "@" + getDateAndTime() + "] " + getText();
	}
}