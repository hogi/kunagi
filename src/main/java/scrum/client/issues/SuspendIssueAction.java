package scrum.client.issues;

public class SuspendIssueAction extends GSuspendIssueAction {

	public SuspendIssueAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public boolean isExecutable() {
		if (issue.isClosed()) return false;
		if (issue.isSuspended()) return false;
		if (issue.isAccepted()) return false;
		return true;
	}

	@Override
	public String getLabel() {
		return "Suspend";
	}

	@Override
	protected void onExecute() {
		issue.suspend();
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Suspend " + issue.getReference() + " " + issue.getLabel();
		}

		@Override
		protected void onUndo() {
			issue.reopen();
		}

	}

}