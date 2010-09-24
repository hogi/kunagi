package scrum.server.project;

import ilarkesto.base.Str;
import ilarkesto.base.Utl;
import ilarkesto.base.time.Date;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import scrum.TestUtil;
import scrum.server.issues.Issue;
import scrum.server.sprint.Sprint;

public class HomepageUpdaterTest {

	Project project;

	@BeforeSuite
	public void initglobal() {
		TestUtil.initialize();
	}

	@BeforeClass
	public void createProject() {
		project = TestUtil.createProject(TestUtil.getDuke());
	}

	@Test
	public void updateHomepage() {
		Sprint sprint = TestUtil.createSprint(project, Date.inDays(15));
		project.setCurrentSprint(sprint);
		sprint.setLabel(Str.generateRandomSentence(2, 4));
		sprint.setGoal(Str.generateRandomParagraph());

		for (int i = 1; i <= 5; i++) {
			Requirement story = TestUtil.createRequirement(project, i);
			story.setSprint(sprint);
			story.setEstimatedWorkAsString(Utl.randomElement(scrum.client.project.Requirement.WORK_ESTIMATION_VALUES));
			story.setDirty(false);
			for (int j = 1; j <= 5; j++) {
				TestUtil.createTask(story, j, 16);
			}
		}
		for (int i = 6; i <= 20; i++) {
			TestUtil.createRequirement(project, i);
		}

		TestUtil.createWikipage(project, "InstallDoc");
		TestUtil.createWikipage(project, "IncludedLibraries");

		for (int i = 0; i < 5; i++) {
			TestUtil.createBlogEntry(project, i).setPublished(true);
		}

		for (int i = 1; i <= 5; i++) {
			Issue issue = TestUtil.createIssue(project, i);
			issue.setAcceptDate(Date.today());
			issue.setUrgent(true);
			if (i == 1) TestUtil.createComments(issue, 2);
		}
		for (int i = 6; i <= 10; i++) {
			Issue issue = TestUtil.createIssue(project, i);
			issue.setAcceptDate(Date.today());
		}
		for (int i = 11; i <= 15; i++) {
			Issue issue = TestUtil.createIssue(project, i);
		}

		sprint.burndownTasksRandomly(sprint.getBegin(), Date.today().addDays(-1));

		new HomepageUpdater(project, "src/projectHomepage/velocity", "test-output/homepage").processAll();
	}

	@AfterClass
	public void cleanup() {
		if (project != null) TestUtil.getApp().getProjectDao().deleteEntity(project);
	}

}
