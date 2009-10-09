package scrum.client.collaboration;

import ilarkesto.gwt.client.AGwtEntity;

public class CreateCommentAction extends GCreateCommentAction {

	private AGwtEntity parent;
	private CommentsWidget commentsWidget;

	public CreateCommentAction(AGwtEntity parent, CommentsWidget commentsWidget) {
		this.parent = parent;
		this.commentsWidget = commentsWidget;
	}

	@Override
	public String getLabel() {
		return "Create new Comment";
	}

	@Override
	protected void onExecute() {
		Comment comment = new Comment(parent);
		cm.getDao().createComment(comment);
		commentsWidget.activateCommentEditor(comment);
	}

}