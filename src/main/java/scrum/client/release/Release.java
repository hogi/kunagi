package scrum.client.release;

import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.HyperlinkWidget;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import scrum.client.ScrumGwt;
import scrum.client.collaboration.ForumSupport;
import scrum.client.common.ReferenceSupport;
import scrum.client.common.ShowEntityAction;
import scrum.client.issues.Issue;
import scrum.client.project.Project;
import scrum.client.project.Requirement;
import scrum.client.sprint.Sprint;

import com.google.gwt.user.client.ui.Widget;

public class Release extends GRelease implements ReferenceSupport, ForumSupport {

	public static final String REFERENCE_PREFIX = "rel";

	public Release(Project project) {
		setProject(project);
	}

	public Release(Map data) {
		super(data);
	}

	public boolean isMajor() {
		return !isBugfix();
	}

	public boolean isBugfix() {
		return isParentReleaseSet();
	}

	public List<Release> getBugfixReleases() {
		return getDao().getReleasesByParentRelease(this);
	}

	public List<Issue> getAffectedByIssues() {
		List<Issue> ret = new ArrayList<Issue>();
		for (Issue issue : getDao().getIssues()) {
			if (issue.getAffectedReleases().contains(this)) ret.add(issue);
		}
		return ret;
	}

	public List<Issue> getFixedIssues() {
		List<Issue> ret = new ArrayList<Issue>();
		for (Issue issue : getDao().getIssues()) {
			if (issue.isClosed() && issue.getFixReleases().contains(this)) ret.add(issue);
		}
		return ret;
	}

	public List<Issue> getPlannedIssues() {
		List<Issue> ret = new ArrayList<Issue>();
		for (Issue issue : getDao().getIssues()) {
			if (!issue.isClosed() && issue.getFixReleases().contains(this)) ret.add(issue);
		}
		return ret;
	}

	public String getReference() {
		return REFERENCE_PREFIX + getNumber();
	}

	public Widget createForumItemWidget() {
		return new HyperlinkWidget(new ShowEntityAction(this, getLabel()));
	}

	public String getIzemizedReleaseNotes() {
		String notes = "";

		// add Stories from all Sprints that are part of this Release
		if (someSprintHasStories()) {
			notes += "'''New Features'''\n\n";
			for (Sprint sprint : getSprints()) {
				for (Requirement story : sprint.getRequirements()) {
					notes += "* " + (story.isClosed() ? "" : "(UNFINISHED) ") + story.getReferenceAndLabel() + "\n";
				}
			}
			notes += "\n\n";
		}

		// add Bugs that have been fixed for this Release
		if (!getFixedIssues().isEmpty() || !getPlannedIssues().isEmpty()) {
			notes += "'''Fixed Bugs'''\n\n";
			for (Issue issue : getFixedIssues()) {
				notes += "* " + issue.getReferenceAndLabel() + "\n";
			}
			for (Issue issue : getPlannedIssues()) {
				notes += "* (UNFINISHED) " + issue.getReferenceAndLabel() + "\n";
			}
			notes += "\n\n";
		}

		// add all Bugs that have not been fixed for this Release
		if (!getAffectedByIssues().isEmpty()) {
			notes += "'''Known Issues'''\n\n";
			for (Issue issue : getAffectedByIssues()) {
				notes += "* " + issue.getReferenceAndLabel() + "\n";
			}
		}

		return notes;
	}

	private boolean someSprintHasStories() {
		for (Sprint sprint : getSprints()) {
			if (!sprint.getRequirements().isEmpty()) return true;
		}
		return false;
	}

	@Override
	public String toHtml() {
		return ScrumGwt.toHtml(getReference(), getLabel());
	}

	@Override
	public String toString() {
		return getReference() + " " + getLabel();
	}

	public static final Comparator<Release> DATE_COMPARATOR = new Comparator<Release>() {

		public int compare(Release ra, Release rb) {
			Date a = ra.getReleaseDate();
			Date b = rb.getReleaseDate();
			if (a == null && b == null) return 0;
			if (a == null) return 1;
			if (b == null) return -1;
			return a.compareTo(b);
		}
	};

	public static final Comparator<Release> DATE_REVERSE_COMPARATOR = new Comparator<Release>() {

		public int compare(Release ra, Release rb) {
			return -DATE_COMPARATOR.compare(ra, rb);
		}
	};

	public List<Requirement> getRequirements() {
		List<Requirement> ret = new ArrayList<Requirement>();
		for (Sprint sprint : getSprints()) {
			ret.addAll(sprint.getRequirements());
		}
		return ret;
	}

}
