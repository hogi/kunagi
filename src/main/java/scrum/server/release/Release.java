package scrum.server.release;

import java.util.ArrayList;
import java.util.List;

import scrum.server.admin.User;
import scrum.server.common.Numbered;
import scrum.server.issues.Issue;
import scrum.server.issues.IssueDao;

public class Release extends GRelease implements Numbered {

	// --- dependencies ---

	private static IssueDao issueDao;

	public static void setIssueDao(IssueDao issueDao) {
		Release.issueDao = issueDao;
	}

	// --- ---

	public List<Issue> getIssues() {
		List<Issue> ret = new ArrayList<Issue>();
		ret.addAll(issueDao.getIssuesByAffectedRelease(this));
		ret.addAll(issueDao.getIssuesByFixRelease(this));
		return ret;
	}

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateReleaseNumber());
	}

	public String getReference() {
		return scrum.client.release.Release.REFERENCE_PREFIX + getNumber();
	}

	public String getReferenceAndLabel() {
		return getReference() + " " + getLabel();
	}

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	public boolean isEditableBy(User user) {
		return getProject().isEditableBy(user);
	}

	@Override
	public String toString() {
		return getReferenceAndLabel();
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();
	}

}