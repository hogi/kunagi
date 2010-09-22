package scrum.client.issues;

import scrum.client.common.TooltipBuilder;

public class CloseIssueAction extends GCloseIssueAction {

	public CloseIssueAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public String getLabel() {
		return "Close";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder(
				"Close this Issue, marking it as resolved or rejected. You can give a reason in the Statement and Change Log.");

		if (issue.isIdea() || issue.isBug()) {
			if (!getCurrentProject().isProductOwner(getCurrentUser())) tb.addRemark(TooltipBuilder.NOT_PRODUCT_OWNER);
		} else {
			if (!getCurrentProject().isProductOwnerOrScrumMasterOrTeamMember(getCurrentUser()))
				tb.addRemark(TooltipBuilder.NOT_PRODUCT_OWNER);
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (issue.isClosed()) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if ((issue.isIdea() || issue.isBug()) && !getCurrentProject().isProductOwner(getCurrentUser())) return false;
		if (!getCurrentProject().isProductOwnerOrScrumMasterOrTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		issue.close();
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Close " + issue.getReference() + " " + issue.getLabel();
		}

		@Override
		protected void onUndo() {
			issue.reopen();
		}

	}

}