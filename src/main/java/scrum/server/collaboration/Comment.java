package scrum.server.collaboration;

import ilarkesto.base.Utl;

public class Comment extends GComment {

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		if (Utl.isEmpty(getText()) && getDateAndTime().getPeriodToNow().toHours() > 1) {
			getDao().deleteEntity(this);
		}
	}

	@Override
	public String toString() {
		return "[" + getAuthor() + "@" + getDateAndTime() + "] " + getText();
	}
}