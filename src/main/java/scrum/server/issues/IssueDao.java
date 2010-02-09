package scrum.server.issues;

import ilarkesto.fp.Predicate;

import java.util.Set;

import scrum.server.project.Project;

public class IssueDao extends GIssueDao {

	public Set<Issue> getAcceptedIssues(final Project project) {
		return getEntities(new Predicate<Issue>() {

			public boolean test(Issue issue) {
				return issue.isProject(project) && issue.isAccepted();
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

	public void createTestIssue(Project project, int variant) {
		Issue issue = new Issue();
		issue.setProject(project);
		issue.setLabel("Issue " + variant);
		if (variant < scrum.client.issues.Issue.Types.ALL.size()) {
			issue.setType(scrum.client.issues.Issue.Types.ALL.get(variant));
		} else {
			issue.setType(scrum.client.issues.Issue.INIT_TYPE);
		}
		saveEntity(issue);
	}

}