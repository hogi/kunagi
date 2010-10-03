package scrum.client.issues;

import ilarkesto.core.base.Str;
import ilarkesto.core.logging.Log;
import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.DateAndTime;
import ilarkesto.gwt.client.HyperlinkWidget;
import ilarkesto.gwt.client.LabelProvider;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import scrum.client.ScrumGwt;
import scrum.client.admin.User;
import scrum.client.collaboration.ForumSupport;
import scrum.client.common.ReferenceSupport;
import scrum.client.common.ShowEntityAction;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.Widget;

public class Issue extends GIssue implements ReferenceSupport, ForumSupport {

	private static Log log = Log.get(Issue.class);

	public static final String INIT_TYPE = Types.ISSUE;
	public static final String REFERENCE_PREFIX = "iss";

	public static final int CRITICAL = 2;
	public static final int SEVERE = 1;
	public static final int NORMAL = 0;
	public static final int MINOR = -1;

	public static final Integer[] SEVERITY_OPTIONS = { CRITICAL, SEVERE, NORMAL, MINOR };

	public Issue(Project project) {
		setType(Types.ISSUE);
		setProject(project);
		setDate(DateAndTime.now());
	}

	public Issue(Map data) {
		super(data);
	}

	public String getIssuer() {
		if (isCreatorSet()) return getCreator().getName();

		String name = getIssuerName();
		String email = getIssuerEmail();

		if (name == null && email == null) return null;
		if (name == null) return email;
		if (email == null) return name;

		return name + " (" + email + ")";
	}

	public boolean isSuspended() {
		Date date = getSuspendedUntilDate();
		if (date == null) return false;
		return date.isAfter(Date.today());
	}

	public void suspend(int days) {
		Date date = Date.today().addDays(days);
		log.info("Suspending for", days, "days, until", date);
		setSuspendedUntilDate(date);
	}

	public void unsuspend() {
		setSuspendedUntilDate(null);
	}

	public void appendStatement(String text) {
		String statement = getStatement();
		if (Str.isBlank(statement)) {
			setStatement(text);
			return;
		}
		setStatement(statement + "\n\n" + text);
	}

	public void claim(User user) {
		setOwner(user);
	}

	public boolean isAccepted() {
		if (isClosed()) return false;
		return getAcceptDate() != null;
	}

	public boolean isBug() {
		return isAccepted() && isUrgent();
	}

	public boolean isIdea() {
		return isAccepted() && !isUrgent();
	}

	public boolean isOpen() {
		return !isClosed() && !isAccepted();
	}

	public boolean isClosed() {
		return getCloseDate() != null;
	}

	public String getStatusLabel() {
		if (isClosed()) return "closed on " + getCloseDate();
		if (isBug()) {
			String s = "";
			if (isFixed()) s += "fixed by ";
			if (isOwnerSet()) s += getOwner().getName();
			return s;
		}
		if (isIdea()) return "accepted on " + getAcceptDate();
		if (isSuspended()) return "suspended until " + getSuspendedUntilDate();
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

	public void acceptAsIdea() {
		setAcceptDate(Date.today());
	}

	public void acceptAsBug() {
		setAcceptDate(Date.today());
		setUrgent(true);
	}

	public void reopen() {
		setAcceptDate(null);
		setUrgent(false);
		setCloseDate(null);
	}

	public String getSeverityLabel() {
		return SEVERITY_LABELS.getLabel(getSeverity());
	}

	@Override
	public List<Integer> getSeverityOptions() {
		return Arrays.asList(SEVERITY_OPTIONS);
	}

	public String getReference() {
		return REFERENCE_PREFIX + getNumber();
	}

	public String getReferenceAndLabel() {
		return getReference() + " " + getLabel();
	}

	@Override
	public List<String> getTypeOptions() {
		return Types.ALL;
	}

	@Override
	public String toHtml() {
		return ScrumGwt.toHtml(getReference(), getLabel());
	}

	@Override
	public String toString() {
		return getReference() + " (" + getType() + ") " + getLabel();
	}

	public Widget createForumItemWidget() {
		return new HyperlinkWidget(new ShowEntityAction(this, getLabel()));
	}

	public static final Comparator<Issue> SEVERITY_COMPARATOR = new Comparator<Issue>() {

		public int compare(Issue a, Issue b) {
			int aSeverity = a.getSeverity();
			int bSeverity = b.getSeverity();
			if (aSeverity == bSeverity) return ACCEPT_DATE_COMPARATOR.compare(a, b);
			return bSeverity - aSeverity;
		}
	};

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

	public static LabelProvider<Integer> SEVERITY_LABELS = new LabelProvider<Integer>() {

		public String getLabel(Integer severity) {
			switch (severity) {
				case 2:
					return "critical";
				case 1:
					return "severe";
				case 0:
					return "normal";
				case -1:
					return "minor ";
			}
			return String.valueOf(severity);
		}
	};

	@Deprecated
	public static class Types {

		public static final String ISSUE = "issue";
		public static final String BUG = "bug";
		public static final String REQUIREMENT = "requirement";
		public static final String QUALITY = "quality";
		public static final String IDEA = "idea";

		public static final List<String> ALL = Arrays.asList(ISSUE, BUG, REQUIREMENT, QUALITY, IDEA);
	}
}
