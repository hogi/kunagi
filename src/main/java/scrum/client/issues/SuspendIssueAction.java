package scrum.client.issues;

import scrum.client.common.TooltipBuilder;

public class SuspendIssueAction extends GSuspendIssueAction {

	private int days;

	public SuspendIssueAction(scrum.client.issues.Issue issue, int days) {
		super(issue);
		this.days = days;
	}

	@Override
	public String getLabel() {
		return "Suspend for " + days + " days";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Hide this issue for " + days
				+ " days (if you want to postpone a decision).");
		if (!issue.getProject().isProductOwner(getCurrentUser())) tb.addRemark(TooltipBuilder.NOT_PRODUCT_OWNER);
		return tb.getTooltip();
	}

	@Override
	public boolean isPermitted() {
		if (!issue.getProject().isProductOwner(getCurrentUser())) return false;
		return true;
	}

	@Override
	public boolean isExecutable() {
		if (issue.isSuspended()) return false;
		if (!issue.isOpen()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		issue.suspend(days);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Suspend " + issue.getReference() + " " + issue.getLabel();
		}

		@Override
		protected void onUndo() {
			issue.setSuspendedUntilDate(null);
		}

	}

}