package scrum.server.issues;

import scrum.server.project.Project;

public class IssueDao extends GIssueDao {

	public void createTestIssue(Project project, int variant) {
		Issue issue = new Issue();
		issue.setProject(project);
		issue.setLabel("Issue " + variant);
		if (variant < Issue.TYPES.length) {
			issue.setType(Issue.TYPES[variant]);
		} else {
			issue.setType(scrum.client.issues.Issue.INIT_TYPE);
		}
		saveEntity(issue);
	}

}