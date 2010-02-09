package scrum.server.risks;

import scrum.client.risks.RiskComputer;
import scrum.server.ScrumWebApplication;
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

		// renaming: req -> sto
		if (ScrumWebApplication.REQ_RENAMING_DATE.isAfter(getLastModified())) {
			setDescription(ScrumWebApplication.convertReqToSto(getDescription()));
		}
	}

	@Override
	public String toString() {
		return getLabel();
	}

}
