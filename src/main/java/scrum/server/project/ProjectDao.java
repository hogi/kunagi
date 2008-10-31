package scrum.server.project;

import scrum.server.admin.User;

public class ProjectDao extends GProjectDao {

	public Project postProject(String label, User admin) {
		Project project = newEntityInstance();
		project.setLabel(label);
		saveEntity(project);
		return project;
	}

}
