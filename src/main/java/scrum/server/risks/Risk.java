package scrum.server.risks;

import scrum.server.common.Numbered;

public class Risk extends GRisk implements Numbered {

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateRiskNumber());
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
