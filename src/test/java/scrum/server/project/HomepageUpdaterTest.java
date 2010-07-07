package scrum.server.project;

import ilarkesto.base.time.Date;

import org.testng.annotations.Test;

import scrum.TestUtil;
import scrum.server.sprint.Sprint;

public class HomepageUpdaterTest {

	@Test
	public void updateHomepage() {
		Project project = TestUtil.createProject();

		Sprint sprint = TestUtil.createSprint(project, Date.inDays(15));
		project.setCurrentSprint(sprint);

		for (int i = 1; i <= 5; i++) {
			TestUtil.createRequirement(project, i).setSprint(sprint);
		}
		for (int i = 6; i <= 20; i++) {
			TestUtil.createRequirement(project, i);
		}

		TestUtil.createWikipage(project, "InstallDoc");

		for (int i = 0; i < 5; i++) {
			TestUtil.createBlogEntry(project, i);
		}

		HomepageUpdater.updateHomepage("src/projectHomepage/velocity", "test-output/homepage", project);
	}

}
