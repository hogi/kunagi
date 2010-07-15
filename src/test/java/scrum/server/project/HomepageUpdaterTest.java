package scrum.server.project;

import ilarkesto.base.Str;
import ilarkesto.base.Utl;
import ilarkesto.base.time.Date;

import org.testng.annotations.Test;

import scrum.TestUtil;
import scrum.server.issues.Issue;
import scrum.server.sprint.Sprint;
import scrum.server.sprint.Task;

public class HomepageUpdaterTest {

	@Test
	public void updateHomepage() {
		Project project = TestUtil.createProject();

		Sprint sprint = TestUtil.createSprint(project, Date.inDays(15));
		project.setCurrentSprint(sprint);
		sprint.setLabel(Str.generateRandomSentence(2, 4));
		sprint.setGoal(Str.generateRandomParagraph());

		for (int i = 1; i <= 5; i++) {
			Requirement story = TestUtil.createRequirement(project, i);
			story.setSprint(sprint);
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
		}
		for (int i = 6; i <= 10; i++) {
			Issue issue = TestUtil.createIssue(project, i);
			issue.setAcceptDate(Date.today());
		}
		for (int i = 11; i <= 15; i++) {
			Issue issue = TestUtil.createIssue(project, i);
		}

		Date date = sprint.getBegin();
		sprint.getDaySnapshot(date).updateWithCurrentSprint();
		date = date.nextDay();
		while (date.isPast()) {
			if (!date.isWeekend()) {
				int toBurn = Utl.randomInt(16, 24);
				int totalRemaining = sprint.getRemainingWork();
				for (Task task : sprint.getTasks()) {
					if (toBurn == 0) break;
					int remaining = task.getRemainingWork();
					int burn = Math.min(toBurn, remaining);
					remaining -= burn;
					toBurn -= burn;
					task.setBurnedWork(task.getBurnedWork() + burn);
					if (Utl.randomInt(1, 10) == 1) {
						remaining += Utl.randomInt(-30, 30);
					}
					if (totalRemaining == 0) {
						remaining += Utl.randomInt(100, 300);
						totalRemaining = remaining;
					}
					task.setRemainingWork(remaining);
				}
			}
			sprint.getDaySnapshot(date).updateWithCurrentSprint();
			date = date.nextDay();
		}

		HomepageUpdater.updateHomepage("src/projectHomepage/velocity", "test-output/homepage", project);
	}

}
