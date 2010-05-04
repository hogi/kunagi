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

import com.google.gwt.user.client.ui.Widget;

public class Release extends GRelease implements ReferenceSupport, ForumSupport {

	public static final String REFERENCE_PREFIX = "rel";

	public Release(Project project) {
		setLabel("Next Release");
		setProject(project);
	}

	public Release(Map data) {
		super(data);
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
			if (!issue.isClosed() && issue.getAffectedReleases().contains(this)) ret.add(issue);
		}
		return ret;
	}

	public String getReference() {
		return REFERENCE_PREFIX + getNumber();
	}

	public Widget createForumItemWidget() {
		return new HyperlinkWidget(new ShowEntityAction(this, getLabel()));
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

}
