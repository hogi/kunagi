package scrum.client.issues;

import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.DateAndTime;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.HyperlinkWidget;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import scrum.client.ScrumJs;
import scrum.client.admin.User;
import scrum.client.collaboration.ForumSupport;
import scrum.client.common.ReferenceSupport;
import scrum.client.common.ShowEntityAction;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.Widget;

public class Issue extends GIssue implements ReferenceSupport, ForumSupport {

	public static final String INIT_TYPE = Types.ISSUE;

	public static final String REFERENCE_PREFIX = "iss";

	public Issue(Project project) {
		setType(Types.ISSUE);
		setProject(project);
		setDate(DateAndTime.now());
	}

	public Issue(Map data) {
		super(data);
	}

	public void claim(User user) {
		setOwner(user);
	}

	public boolean isAccepted() {
		if (isClosed()) return false;
		return getAcceptDate() != null;
	}

	public boolean isAcceptedUrgent() {
		if (isClosed()) return false;
		return getAcceptDate() != null && isUrgent();
	}

	public boolean isOpen() {
		return !isClosed() && !isAccepted();
	}

	public boolean isClosed() {
		return getCloseDate() != null;
	}

	public String getStatusLabel() {
		if (isClosed()) return "closed on " + getCloseDate();
		if (isAcceptedUrgent()) {
			String s = "";
			if (isFixed()) s += "fixed by ";
			if (isOwnerSet()) s += getOwner().getName();
			return s;
		}
		if (isAccepted()) return "accepted on " + getAcceptDate();
		return "issued on " + getDate().getDate();
	}

	public void setFixed(User user) {
		setOwner(user);
		setFixDate(Date.today());
	}

	public void rejectFix() {
		setFixDate(null);
	}

	public boolean isFixed() {
		return getFixDate() != null;
	}

	public void close() {
		setOwner(null);
		setCloseDate(Date.today());
	}

	public void accept() {
		setAcceptDate(Date.today());
	}

	public void acceptAsUrgent() {
		setAcceptDate(Date.today());
		setUrgent(true);
	}

	public void reopen() {
		setAcceptDate(null);
		setUrgent(false);
		setCloseDate(null);
	}

	public String getReference() {
		return REFERENCE_PREFIX + getNumber();
	}

	@Override
	public List<String> getTypeOptions() {
		return Types.ALL;
	}

	@Override
	public String toHtml() {
		return ScrumJs.createShowEntityByReferenceLink(getReference()) + " " + Gwt.escapeHtml(getLabel());
	}

	@Override
	public String toString() {
		return getReference() + " (" + getType() + ") " + getLabel();
	}

	public Widget createForumItemWidget() {
		return new HyperlinkWidget(new ShowEntityAction(this, getLabel()));
	}

	public static final Comparator<Issue> ISSUE_DATE_COMPARATOR = new Comparator<Issue>() {

		public int compare(Issue a, Issue b) {
			return b.getDate().compareTo(a.getDate());
		}
	};

	public static final Comparator<Issue> CLOSE_DATE_COMPARATOR = new Comparator<Issue>() {

		public int compare(Issue a, Issue b) {
			return b.getCloseDate().compareTo(a.getCloseDate());
		}
	};

	public static final Comparator<Issue> ACCEPT_DATE_COMPARATOR = new Comparator<Issue>() {

		public int compare(Issue a, Issue b) {
			return b.getAcceptDate().compareTo(a.getAcceptDate());
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
