package scrum.client.issues;

import scrum.client.admin.User;
import scrum.client.common.TooltipBuilder;

public class ClaimIssueAction extends GClaimIssueAction {

	public ClaimIssueAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public String getLabel() {
		return "Claim";
	}

	@Override
	public String getTooltip() {

		TooltipBuilder tb = new TooltipBuilder("Claim ownership for this Issue, stating that you are working on it.");
		if (!getCurrentProject().isTeamMember(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_TEAM);
		} else {
			if (issue.isClosed()) tb.addRemark("Issue is already closed.");
			if (issue.isFixed()) tb.addRemark("Issue is already fixed.");
			if (issue.isOwner(getCurrentUser())) tb.addRemark("Issue is already owned by you.");
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (!issue.isUrgent()) return false;
		if (issue.isClosed()) return false;
		if (issue.isFixed()) return false;
		if (issue.isOwner(getCurrentUser())) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		User owner = issue.getOwner();
		issue.claim(getCurrentUser());
		addUndo(new Undo(owner));
	}

	class Undo extends ALocalUndo {

		User owner;

		public Undo(User owner) {
			this.owner = owner;
		}

		@Override
		public String getLabel() {
			return "Undo Claim " + issue.getReference() + " " + issue.getLabel();
		}

		@Override
		protected void onUndo() {
			issue.setOwner(owner);
		}

	}

}