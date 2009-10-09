package scrum.client.issues;

import scrum.client.project.Quality;

public class ConvertIssueToQualityAction extends GConvertIssueToQualityAction {

	public ConvertIssueToQualityAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public String getLabel() {
		return "Convert to Quality";
	}

	@Override
	public String getTooltip() {
		return "Convert this issue to a real quality on the quality backlog.";
	}

	@Override
	public boolean isExecutable() {
		if (!issue.getProject().isProductOwner(getUser())) return false;
		if (!issue.isTypeQuality()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		Quality quality = new Quality(issue);
		cm.getDao().createQuality(quality);
		cm.getDao().deleteIssue(issue);
		cm.getProjectContext().showQualityBacklog(quality);
	}

}
