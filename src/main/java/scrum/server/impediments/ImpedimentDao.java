package scrum.server.impediments;

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
		Impediment impediment1 = newEntityInstance();
		impediment1.setProject(project);
		impediment1.setLabel("Impediment " + variant);
		saveEntity(impediment1);
	}

}
