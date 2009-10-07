package scrum.client.issues;

import ilarkesto.gwt.client.Date;

import java.util.Comparator;
import java.util.Map;

import scrum.client.project.Project;

public class Issue extends GIssue {

	public static final String[] TYPES = new String[] { "issue", "bug", "requirement", "quality", "idea" };
	public static final String INIT_TYPE = TYPES[0];

	public static final String INIT_LABEL = "New Issue";
	public static final String REFERENCE_PREFIX = "iss";

	public Issue(Project project) {
		setLabel(INIT_LABEL);
		setType(INIT_TYPE);
		setProject(project);
		setDate(Date.today());
	}

	public Issue(Map data) {
		super(data);
	}

	public String getReference() {
		return REFERENCE_PREFIX + getNumber();
	}

	public String getTypeLabel() {
		return getType(); // TODO map to labels
	}

	public boolean isTypeRequirement() {
		return TYPES[2].equals(getType());
	}

	public boolean isTypeQuality() {
		return TYPES[3].equals(getType());
	}

	@Override
	public String toString() {
		return getReference() + " [" + getType() + "] " + getLabel();
	}

	public static Comparator<Issue> DATE_COMPARATOR = new Comparator<Issue>() {

		public int compare(Issue a, Issue b) {
			return a.getDate().compareTo(b.getDate());
		}
	};

	public static Comparator<Issue> REVERSE_DATE_COMPARATOR = new Comparator<Issue>() {

		public int compare(Issue a, Issue b) {
			return DATE_COMPARATOR.compare(b, a);
		}
	};
}
