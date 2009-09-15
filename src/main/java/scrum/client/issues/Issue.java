package scrum.client.issues;

import java.util.Map;

import scrum.client.project.Project;

public class Issue extends GIssue {

	public static final String INIT_LABEL = "New Issue";

	public Issue(Project project) {
		setLabel(INIT_LABEL);
		setProject(project);
	}

	public Issue(Map data) {
		super(data);
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
