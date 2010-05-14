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

	public boolean createTestRisk(Project project, int variant) {
		Risk risk = newEntityInstance();
		risk.setProject(project);

		if (variant == 1) {
			risk.setLabel("God comes to judge the living and the dead");
			risk.setProbability(0);
			risk.setImpact(100);
			risk.setImpactMitigation("Nothing we can do here.");
		} else if (variant == 2) {
			risk.setLabel("Duke Nukem Forever is released");
			risk.setProbability(20);
			risk.setImpact(100);
			risk.setProbabilityMitigation("Nothing to mitigate here. The probability is close to nothing.");
			risk.setImpactMitigation("Nothing to mitigate here. Everyone will be playing. Everything will be lost.");
		} else if (variant == 3) {
			risk.setLabel("Sudden Death");
			risk.setProbability(50);
			risk.setImpact(40);
			risk.setImpactMitigation("Go to the roof if it's by rising sea level.");
		} else {
			return false;
		}

		saveEntity(risk);
		return true;
	}

}
