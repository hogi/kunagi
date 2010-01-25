package scrum.server.project;

import scrum.server.common.Numbered;

public class Quality extends GQuality implements Numbered {

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateQualityNumber());
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