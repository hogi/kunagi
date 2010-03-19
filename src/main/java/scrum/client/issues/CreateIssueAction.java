package scrum.client.issues;

import ilarkesto.core.scope.Scope;
import scrum.client.workspace.ProjectWorkspaceWidgets;

public class CreateIssueAction extends GCreateIssueAction {

	@Override
	public String getLabel() {
		return "Create new Issue";
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void onExecute() {
		Issue issue = getCurrentProject().createNewIssue();
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).showIssueList(issue);
		addUndo(new Undo(issue));
	}

	class Undo extends ALocalUndo {

		private Issue issue;

		public Undo(Issue issue) {
			this.issue = issue;
		}

		@Override
		public String getLabel() {
			return "Undo Create " + issue.getReference() + " " + issue.getLabel();
		}

		@Override
		protected void onUndo() {
			issue.getProject().deleteIssue(issue);
		}

	}

}
