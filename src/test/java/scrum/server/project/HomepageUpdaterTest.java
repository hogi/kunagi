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
		for (int i = 1; i <= 5; i++) {
			TestUtil.createRequirement(project, i).setSprint(sprint);
		}
		for (int i = 6; i <= 20; i++) {
			TestUtil.createRequirement(project, i);
		}
		project.setCurrentSprint(sprint);
		TestUtil.createWikipage(project, "InstallDoc");
		HomepageUpdater.updateHomepage("src/projectHomepage/velocity", "test-output/homepage", project);
	}

}
