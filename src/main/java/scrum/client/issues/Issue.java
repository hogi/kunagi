package scrum.client.issues;

import java.util.Map;

import scrum.client.project.Project;

public class Issue extends GIssue {

	public static final String[] TYPES = new String[] { "issue", "bug", "requirement", "quality" };
	public static final String INIT_TYPE = TYPES[0];

	public static final String INIT_LABEL = "New Issue";

	public Issue(Project project) {
		setLabel(INIT_LABEL);
		setType(INIT_TYPE);
		setProject(project);
	}

	public String getTypeLabel() {
		return getType(); // TODO map to labels
	}

	public Issue(Map data) {
		super(data);
	}

	public boolean isTypeRequirement() {
		return TYPES[2].equals(getType());
	}

	public boolean isTypeQuality() {
		return TYPES[3].equals(getType());
	}

	@Override
	public String toString() {
		return "[" + getType() + "] " + getLabel();
	}

}
