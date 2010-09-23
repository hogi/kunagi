package scrum.client.issues;

public class PublishIssueAction extends GPublishIssueAction {

	public PublishIssueAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public String getLabel() {
		return "Publish";
	}

	@Override
	public String getTooltip() {
		return "Make this issue available on the homepage.";
	}

	@Override
	public boolean isExecutable() {
		if (issue.isPublished()) return false;
		if (getCurrentProject().getHomepageDir() == null) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isProductOwnerOrScrumMasterOrTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		issue.setPublished(true);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Publish: " + issue.getReference() + " " + issue.getLabel();
		}

		@Override
		protected void onUndo() {
			issue.setPublished(false);
		}
	}

}