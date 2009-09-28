package scrum.server.impediments;

import ilarkesto.base.time.Date;
import scrum.server.project.Project;

public class ImpedimentDao extends GImpedimentDao {

	@Override
	public Impediment newEntityInstance() {
		Impediment impediment = super.newEntityInstance();
		impediment.setLabel(scrum.client.impediments.Impediment.INIT_LABEL);
		return impediment;
	}

	// --- test data ---

	public void createTestImpediment(Project project, int variant) {
		Impediment im = newEntityInstance();
		im.setProject(project);
		im.setLabel("Impediment " + variant);
		im.setDate(Date.beforeDays(variant * 5));
		saveEntity(im);
	}

}
