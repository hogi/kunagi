package scrum.client.issues;

import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.DateAndTime;
import ilarkesto.gwt.client.Gwt;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import scrum.client.ScrumJs;
import scrum.client.common.ReferenceSupport;
import scrum.client.project.Project;

public class Issue extends GIssue implements ReferenceSupport {

	public static final String INIT_TYPE = Types.ISSUE;

	public static final String REFERENCE_PREFIX = "iss";

	public static final IssueTypeLabelProvider TYPE_LABEL_PROVIDER = new IssueTypeLabelProvider();

	public Issue(Project project) {
		setType(INIT_TYPE);
		setProject(project);
		setDate(DateAndTime.now());
	}

	public Issue(Map data) {
		super(data);
	}

	public boolean isOpen() {
		return !isClosed();
	}

	public boolean isClosed() {
		return getCloseDate() != null;
	}

	public String getStatusLabel() {
		if (isClosed()) return "closed on " + getCloseDate();
		return "issued on " + getDate().getDate();
	}

	public void close() {
		setCloseDate(Date.today());
	}

	public String getReference() {
		return REFERENCE_PREFIX + getNumber();
	}

	@Override
	public List<String> getTypeOptions() {
		return Types.ALL;
	}

	public String getTypeLabel() {
		return TYPE_LABEL_PROVIDER.getLabel(getType());
	}

	public boolean isTypeRequirement() {
		return Types.REQUIREMENT.equals(getType());
	}

	public boolean isTypeQuality() {
		return Types.QUALITY.equals(getType());
	}

	@Override
	public String toHtml() {
		return ScrumJs.createShowEntityByReferenceLink(getReference()) + " " + Gwt.escapeHtml(getLabel());
	}

	@Override
	public String toString() {
		return getReference() + " (" + getType() + ") " + getLabel();
	}

	public static final Comparator<Issue> DATE_COMPARATOR = new Comparator<Issue>() {

		public int compare(Issue a, Issue b) {
			return a.getDate().compareTo(b.getDate());
		}
	};

	public static final Comparator<Issue> REVERSE_DATE_COMPARATOR = new Comparator<Issue>() {

		public int compare(Issue a, Issue b) {
			return DATE_COMPARATOR.compare(b, a);
		}
	};

	public static class Types {

		public static final String ISSUE = "issue";
		public static final String BUG = "bug";
		public static final String REQUIREMENT = "requirement";
		public static final String QUALITY = "quality";
		public static final String IDEA = "idea";

		public static final List<String> ALL = Arrays.asList(ISSUE, BUG, REQUIREMENT, QUALITY, IDEA);
	}
}
