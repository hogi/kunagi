package scrum.server.risks;

import scrum.server.project.Project;

public class RiskDao extends GRiskDao {

	@Override
	public Risk newEntityInstance() {
		Risk risk = super.newEntityInstance();
		risk.setLabel("New Risk");
		return risk;
	}

	// --- test data ---

	public Risk postRisk(Project project, String label, int probability, int impact) {
		Risk risk = newEntityInstance();
		risk.setProject(project);
		risk.setLabel(label);
		risk.setProbability(probability);
		risk.setImpact(impact);
		saveEntity(risk);
		return risk;
	}
}
