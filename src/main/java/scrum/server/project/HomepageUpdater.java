package scrum.server.project;

import ilarkesto.velocity.Velocity;

import java.util.HashMap;
import java.util.Map;

public class HomepageUpdater {

	public static void updateHomepage(String templatePath, String outputPath, Project project) {
		Map<String, ?> context = new HashMap<String, Object>();
		Velocity.processDir(new java.io.File(templatePath), new java.io.File(outputPath), context);
	}

}
