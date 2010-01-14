package scrum.client.issues;

public class CloseIssueAction extends GCloseIssueAction {

	public CloseIssueAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public boolean isExecutable() {
		if (issue.isClosed()) return false;
		return true;
	}

	@Override
	public String getLabel() {
		return "Close";
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
			issue.setCloseDate(null);
		}

	}

}