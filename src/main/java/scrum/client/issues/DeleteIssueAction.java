package scrum.client.issues;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.AScrumAction;

public class DeleteIssueAction extends AScrumAction {

	private Issue issue;

	public DeleteIssueAction(Issue task, AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
		this.issue = task;
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		return "Delete this issue.";
	}

	@Override
	public boolean isExecutable() {
		return getProject().isPig(getUser());
	}

	@Override
	protected void onExecute() {
		getProject().deleteIssue(issue);
	}

}
