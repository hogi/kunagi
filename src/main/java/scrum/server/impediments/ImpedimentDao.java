package scrum.server.impediments;

import scrum.server.project.Project;

public class ImpedimentDao extends GImpedimentDao {

	@Override
	public Impediment newEntityInstance() {
		Impediment impediment = super.newEntityInstance();
		impediment.setLabel(scrum.client.impediments.Impediment.INIT_LABEL);
		return impediment;
	}

	public Impediment postImpediment(Project project) {
		Impediment impediment = newEntityInstance();
		impediment.setProject(project);
		saveEntity(impediment);
		return impediment;
	}

}
