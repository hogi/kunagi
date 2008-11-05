package scrum.server.impediments;

import scrum.server.project.Project;

public class ImpedimentDao extends GImpedimentDao {

	public Impediment postImpediment(Project project) {
		Impediment impediment = newEntityInstance();
		impediment.setProject(project);
		impediment.setLabel(scrum.client.impediments.Impediment.INIT_LABEL);
		saveEntity(impediment);
		return impediment;
	}

}
