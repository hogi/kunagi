package scrum.server.issues;

import scrum.server.project.Project;

public class IssueDao extends GIssueDao {

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