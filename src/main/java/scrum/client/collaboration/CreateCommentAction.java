package scrum.client.collaboration;

import scrum.client.context.ProjectContext;
import scrum.client.project.Requirement;

public class CreateCommentAction extends GCreateCommentAction {

	private Requirement parent;

	public CreateCommentAction(Requirement parent) {
		this.parent = parent;
	}

	@Override
	public String getLabel() {
		return "Create new comment";
	}

	@Override
	protected void onExecute() {
		Comment comment = parent.createNewComment();
		ProjectContext.get().activateCommentEditor(comment);
	}

}