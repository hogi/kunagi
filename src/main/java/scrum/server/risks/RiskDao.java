package scrum.server.risks;

import scrum.server.project.Project;

public class RiskDao extends GRiskDao {

	@Override
	public Risk newEntityInstance() {
		Risk risk = super.newEntityInstance();
		risk.setLabel(scrum.client.impediments.Impediment.INIT_LABEL);
		return risk;
	}

	// --- test data ---

	public void createTestRisk(Project project, int variant) {
		Risk risk = newEntityInstance();
		risk.setProject(project);

		if (variant == 1) {
			risk.setLabel("Poop");
			risk.setImpact(20);
			risk.setProbability(20);
		} else if (variant == 2) {
			risk.setLabel("Hurtz");
			risk.setImpact(60);
			risk.setProbability(20);
		} else if (variant == 3) {
			risk.setLabel("Car accident");
			risk.setImpact(60);
			risk.setProbability(60);
		} else if (variant == 4) {
			risk.setLabel("Fire");
			risk.setImpact(80);
			risk.setProbability(60);
		} else if (variant == 5) {
			risk.setLabel("Nuclear bomb");
			risk.setImpact(80);
			risk.setProbability(60);
		} else if (variant == 6) {
			risk.setLabel("God comes to judge the living and the dead");
			risk.setImpact(100);
			risk.setProbability(20);
		} else if (variant == 7) {
			risk.setLabel("End of days");
			risk.setImpact(80);
			risk.setProbability(40);
		}

		saveEntity(risk);
	}

}
