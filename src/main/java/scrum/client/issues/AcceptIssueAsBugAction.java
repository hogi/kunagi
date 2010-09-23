package scrum.client.issues;

import ilarkesto.core.scope.Scope;
import scrum.client.common.TooltipBuilder;
import scrum.client.workspace.ProjectWorkspaceWidgets;

public class AcceptIssueAsBugAction extends GAcceptIssueAsBugAction {

	public AcceptIssueAsBugAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public String getLabel() {
		return "Accept as bug";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder(
				"Accept this issue as a bug. Bugs can be classified by severeness and need to be reviewed and (if necessary) fixed by the Team.");
		if (!issue.getProject().isProductOwnerOrScrumMaster(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_PRODUCT_OWNER_NOR_SCRUMMASTER);
		}
		return tb.getTooltip();
	}

	@Override
	public boolean isPermitted() {
		if (!issue.getProject().isProductOwnerOrScrumMaster(getCurrentUser())) return false;
		return true;
	}

	@Override
	public boolean isExecutable() {
		if (issue.isClosed()) return false;
		if (issue.isBug()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		issue.acceptAsBug();
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).showIssueList(issue);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Accept as bug: " + issue.getReference() + " " + issue.getLabel();
		}

		@Override
		protected void onUndo() {
			issue.reopen();
		}

	}

}