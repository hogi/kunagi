package scrum.server.risks;

import scrum.client.risks.RiskComputer;
import scrum.server.admin.User;
import scrum.server.common.Numbered;

public class Risk extends GRisk implements Numbered {

	public int getPriority() {
		return RiskComputer.getPriority(getImpact(), getProbability());
	}

	public String getPriorityLabel() {
		return RiskComputer.getPriorityLabel(getImpact(), getProbability());
	}

	public String getProbabilityLabel() {
		return RiskComputer.getProbabilityLabel(getProbability());
	}

	public String getImpactLabel() {
		return RiskComputer.getImpactLabel(getImpact());
	}

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateRiskNumber());
	}

	public String getReferenceAndLabel() {
		return getReference() + " " + getLabel();
	}

	public String getReference() {
		return scrum.client.risks.Risk.REFERENCE_PREFIX + getNumber();
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();

	}

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	@Override
	public String toString() {
		return getReferenceAndLabel();
	}

}
