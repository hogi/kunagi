package scrum.client.risks;

import java.util.Map;

import scrum.client.project.Project;

public class Risk extends GRisk {

	public static final String INITIAL_LABEL = "New Risk";

	public Risk(Project project) {
		setProject(project);
		setLabel(INITIAL_LABEL);
	}

	public Risk(Map data) {
		super(data);
	}

	public String getProbabilityLabel() {
		return RiskLabeler.getProbabilityLabel(getProbability());
	}

	public String getImpactLabel() {
		return RiskLabeler.getImpactLabel(getImpact());
	}

	public String getPriorityLabel() {
		return RiskLabeler.getPriorityLabel(getImpact(), getProbability());
	}

	public String getSummary() {
		return getPriorityLabel() + " priority because " + getImpactLabel() + " and " + getProbabilityLabel();
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
