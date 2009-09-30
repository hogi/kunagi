package scrum.server.admin;

import ilarkesto.fp.Predicate;
import scrum.server.project.Project;

public class ProjectUserConfigDao extends GProjectUserConfigDao {

	public ProjectUserConfig getProjectUserConfig(final Project project, final User user) {
		ProjectUserConfig projectUserConfig = getEntity(new Predicate<ProjectUserConfig>() {

			public boolean test(ProjectUserConfig e) {
				return e.isProject(project) && e.isUser(user);
			}
		});

		if (projectUserConfig == null) {
			projectUserConfig = new ProjectUserConfig();
			projectUserConfig.setProject(project);
			projectUserConfig.setUser(user);
			projectUserConfig.setColor(user.getColor());
			saveEntity(projectUserConfig);
		}

		return projectUserConfig;
	}

}