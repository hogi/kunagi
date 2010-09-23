package scrum.server.issues;

import ilarkesto.base.Utl;
import ilarkesto.base.time.DateAndTime;

import java.util.Comparator;
import java.util.Set;

import scrum.client.common.LabelSupport;
import scrum.client.common.ReferenceSupport;
import scrum.server.admin.User;
import scrum.server.common.Numbered;
import scrum.server.release.Release;

public class Issue extends GIssue implements Numbered, ReferenceSupport, LabelSupport {

	public String getStatusText() {
		String releasesText = isFixReleasesEmpty() ? "" : " for " + getFixReleasesAsString();
		if (isClosed()) return "Issue is closed" + releasesText + ".";
		if (isIdea()) return "Idea is accepted and the Product Owner needs to create a Story of it.";
		if (isBug()) {
			if (isFixed()) return "Bug is fixed" + releasesText + ". Needs to be tested.";
			if (isOwnerSet()) return getOwner().getName() + " is working on the Bug" + releasesText + ".";
			return "Bug is accepted as '" + getSeverityLabel() + "' and the Team needs to fix it" + releasesText + ".";
		}
		return "Product Owner needs to review this Issue.";
	}

	public String getFixReleasesAsString() {
		Set<Release> releases = getFixReleases();
		if (releases.isEmpty()) return null;
		if (releases.size() == 1) return "Release " + Utl.getElement(releases, 0).getLabel();
		StringBuilder sb = new StringBuilder();
		sb.append("Releases ");
		boolean first = true;
		for (Release release : releases) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			sb.append(release.getLabel());
		}
		return sb.toString();
	}

	public String getSeverityLabel() {
		return scrum.client.issues.Issue.SEVERITY_LABELS.getLabel(getSeverity());
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

	public boolean isBug() {
		return isAccepted() && isUrgent();
	}

	public boolean isIdea() {
		return isAccepted() && !isUrgent();
	}

	public boolean isFixed() {
		return isFixDateSet();
	}

	public boolean isOpen() {
		return !isClosed();
	}

	protected boolean isAccepted() {
		return !isClosed() && isAcceptDateSet();
	}

	@Override
	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateIssueNumber());
	}

	@Override
	public String getReference() {
		return scrum.client.issues.Issue.REFERENCE_PREFIX + getNumber();
	}

	public String getReferenceAndLabel() {
		return getReference() + " " + getLabel();
	}

	public boolean isClosed() {
		return isCloseDateSet();
	}

	@Override
	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	public boolean isEditableBy(User user) {
		return getProject().isEditableBy(user);
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();
		if (!isTypeSet()) setType(scrum.client.issues.Issue.INIT_TYPE);
		if (!isDateSet()) setDate(DateAndTime.now());
		if (isAcceptDateSet() || isCloseDateSet()) setPublished(true);
	}

	@Override
	public String toString() {
		return getReferenceAndLabel();
	}

	public static final Comparator<Issue> CLOSE_DATE_COMPARATOR = new Comparator<Issue>() {

		@Override
		public int compare(Issue a, Issue b) {
			return Utl.compare(b.getCloseDate(), a.getCloseDate());
		}
	};

	public static final Comparator<Issue> ACCEPT_DATE_COMPARATOR = new Comparator<Issue>() {

		@Override
		public int compare(Issue a, Issue b) {
			return Utl.compare(b.getAcceptDate(), a.getAcceptDate());
		}
	};

	public static final Comparator<Issue> DATE_COMPARATOR = new Comparator<Issue>() {

		@Override
		public int compare(Issue a, Issue b) {
			return Utl.compare(b.getDate(), a.getDate());
		}
	};

}