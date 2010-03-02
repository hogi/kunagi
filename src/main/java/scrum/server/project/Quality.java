package scrum.server.project;

import scrum.server.admin.User;
import scrum.server.common.Numbered;

public class Quality extends GQuality implements Numbered {

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateQualityNumber());
	}

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	public boolean isEditableBy(User user) {
		return getProject().isEditableBy(user);
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();
	}

	@Override
	public String toString() {
		return getLabel();
	}
}