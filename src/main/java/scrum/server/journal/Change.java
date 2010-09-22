package scrum.server.journal;

import ilarkesto.auth.Auth;
import ilarkesto.core.logging.Log;
import ilarkesto.persistence.AEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import scrum.server.admin.User;

public class Change extends GChange implements Comparable<Change> {

	private static Log log = Log.get(Change.class);

	@Override
	public boolean isVisibleFor(User user) {
		return Auth.isVisible(getParent(), user);
	}

	public boolean isEditableBy(User user) {
		return false;
	}

	public void mergeTo(Change other) {
		log.info("Merging", this, "to", other);
		other.setOldValue(getOldValue());
		if (isCommentSet()) {
			if (other.isCommentSet()) {
				other.setComment(getComment() + "\n\n" + other.getComment());
			} else {
				other.setComment(getComment());
			}
		}
		getDao().deleteEntity(this);
	}

	public boolean isMergableWith(Change other) {
		if (!isParent(other.getParent())) return false;
		if (!isUser(other.getUser())) return false;
		if (!isKey(other.getKey())) return false;
		if (getDateAndTime().getPeriodTo(other.getDateAndTime()).abs().toMinutes() > 60) return false;
		return true;
	}

	@Override
	public int compareTo(Change other) {
		return getDateAndTime().compareTo(other.getDateAndTime());
	}

	@Override
	public String toString() {
		AEntity parent;
		try {
			parent = getParent();
		} catch (Throwable ex) {
			parent = null;
		}
		return "Change(" + getUser() + "," + getDateAndTime() + ", " + parent + "." + getKey() + ")";
	}

	public static void merge(Collection<Change> changes) {
		List<Change> list = new ArrayList<Change>(changes);
		Collections.sort(list);

		Change previous = null;
		for (Change change : list) {
			if (previous == null) {
				previous = change;
				continue;
			}
			if (previous.isMergableWith(change)) previous.mergeTo(change);
			previous = change;
		}
	}

}
