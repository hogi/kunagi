package scrum.server.collaboration;

import ilarkesto.base.Utl;
import scrum.server.ScrumWebApplication;

public class Comment extends GComment {

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		if (Utl.isEmpty(getText()) && getDateAndTime().getPeriodToNow().toHours() > 1) {
			getDao().deleteEntity(this);
		}

		// renaming: req -> sto
		if (ScrumWebApplication.REQ_RENAMING_DATE.isAfter(getLastModified())) {
			setText(ScrumWebApplication.convertReqToSto(getText()));
		}
	}

	@Override
	public String toString() {
		return "[" + getAuthor() + "@" + getDateAndTime() + "] " + getText();
	}
}