package scrum.server.admin;

import ilarkesto.base.Utl;
import ilarkesto.fp.Predicate;

import java.util.ArrayList;
import java.util.List;

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
			projectUserConfig.setColor(Utl.randomElement(getPresetColors()));
			saveEntity(projectUserConfig);
		}

		return projectUserConfig;
	}

	private List<String> getPresetColors() {
		List<String> colors = new ArrayList<String>();

		colors.add("black");
		colors.add("darkred");
		colors.add("darkgreen");
		colors.add("darkblue");
		colors.add("darkorange");
		colors.add("darkorchid");
		colors.add("darkslateblue");
		colors.add("darkgray");

		return colors;
	}
}