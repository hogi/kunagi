package scrum.server.issues;

import ilarkesto.base.time.Date;
import ilarkesto.base.time.DateAndTime;
import ilarkesto.fp.Predicate;

import java.util.Set;

import scrum.server.project.Project;

public class IssueDao extends GIssueDao {

	public Set<Issue> getAcceptedIssues(final Project project) {
		return getEntities(new Predicate<Issue>() {

			public boolean test(Issue issue) {
				if (!issue.isProject(project)) return false;
				if (issue.isClosed()) return false;
				return issue.isAccepted();
			}
		});
	}

	public Set<Issue> getClosedIssues(final Project project) {
		return getEntities(new Predicate<Issue>() {

			public boolean test(Issue issue) {
				if (!issue.isProject(project)) return false;
				return issue.isClosed();
			}
		});
	}

	public Set<Issue> getUrgentAndOpenIssues(final Project project) {
		return getEntities(new Predicate<Issue>() {

			public boolean test(Issue issue) {
				if (!issue.isProject(project)) return false;
				if (issue.isClosed()) return false;
				return issue.isAcceptedUrgent() || issue.isOpen();
			}
		});
	}

	public Issue getIssueByNumber(final int number, final Project project) {
		return getEntity(new Predicate<Issue>() {

			public boolean test(Issue t) {
				return t.isNumber(number) && t.isProject(project);
			}
		});
	}

	public Issue postIssue(Project project, String label, String text, String issuerName, String issuerEmail) {
		Issue issue = newEntityInstance();
		issue.setProject(project);
		issue.setLabel(label);
		issue.setDescription(text);
		issue.setDate(DateAndTime.now());
		issue.setIssuerName(issuerName);
		issue.setIssuerEmail(issuerEmail);
		saveEntity(issue);
		return issue;
	}

	public boolean createTestIssue(Project project, int variant) {
		Issue issue = newEntityInstance();
		issue.setProject(project);

		if (variant == 1) {
			issue.setLabel("thiz cr4p don't work, n00bz!!1");
			issue.setDescription("go home, u noobz ..#");
		} else if (variant == 2) {
			issue.setLabel("I want eclipse integration");
			issue
					.setDescription("I would be really nice if eclipse commits would be represented in Kunagi! Thank you!");
		} else if (variant == 3) {
			issue.setLabel("Bug: Links don't work");
			issue.setDescription("When I try to post links to other pages, I get links to the Wiki. WTF?");
		} else if (variant == 4) {
			issue.setLabel("Crash when using Dates after 2012");
			issue
					.setDescription("The program crashes whenever I enter dates after 2012. Can't figure out what the problem is though.");
			issue.setAcceptDate(Date.beforeDays(2));
			issue.setUrgent(true);
			issue.setSeverity(1);
		} else if (variant == 5) {
			issue.setLabel("GUI inconsistency between PB and SB");
			issue
					.setDescription("The order of Qualities and Tests is different between widgets in the PB and SB. It should be the same.");
			issue.setAcceptDate(Date.beforeDays(35));
			issue.setUrgent(true);
			issue.setSeverity(-1);
		} else if (variant == 6) {
			issue.setLabel("navigation displays wrong current view");
			issue
					.setDescription("When I open the Whiteboard, \"Sprint Backlog\" is selected in the navigation. Same for other jumps.");
			issue.setAcceptDate(Date.today());
			issue.setUrgent(true);
			issue.setSeverity(-1);
		} else if (variant == 7) {
			issue.setLabel("Product Backlog should be terrific, not amazing");
			issue
					.setDescription("56% of users want a terrific PB, not an amazing one. We should change that in one of the upcoming releases.");
			issue.setAcceptDate(Date.today());
		} else if (variant == 8) {
			issue.setLabel("Add a flattr-button");
			issue.setDescription("See flattr.com.");
			issue.setCloseDate(Date.beforeDays(1));
		} else if (variant == 9) {
			issue.setLabel("I like this software, thank you!");
			issue.setDescription("I'm using Kunagi for my own project now. Thanks for the great work.");
			issue.setCloseDate(Date.today());
		} else {
			return false;
		}
		saveEntity(issue);
		return true;
	}
}