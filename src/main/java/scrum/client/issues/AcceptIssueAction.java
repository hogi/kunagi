package scrum.client.issues;

import ilarkesto.core.scope.Scope;
import scrum.client.workspace.ProjectWorkspaceWidgets;

public class AcceptIssueAction extends GAcceptIssueAction {

	public AcceptIssueAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public String getLabel() {
		return "Accept";
	}

	@Override
	public String getTooltip() {
		return "Accept this issue.";
	}

	@Override
	public boolean isPermitted() {
		if (!issue.getProject().isProductOwner(getCurrentUser())) return false;
		return true;
	}

	@Override
	public boolean isExecutable() {
		if (issue.isClosed()) return false;
		if (issue.isAccepted()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		issue.accept();
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).showIssueList(issue);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Accept " + issue.getReference() + " " + issue.getLabel();
		}

		@Override
		protected void onUndo() {
			issue.reopen();
		}

	}

}