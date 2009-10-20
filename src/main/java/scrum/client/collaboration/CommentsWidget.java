package scrum.client.collaboration;

import ilarkesto.gwt.client.AAction;
import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.ActionKeyPressHandler;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.HyperlinkWidget;
import ilarkesto.gwt.client.ToolbarWidget;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import scrum.client.common.AScrumWidget;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class CommentsWidget extends AScrumWidget {

	private FlowPanel containerPanel;
	private AGwtEntity parent;
	private Map<Comment, CommentWidget> widgets;

	private HyperlinkWidget activateCommentLink;
	private TextArea editor;
	private ToolbarWidget editorSubmitToolbar;

	public CommentsWidget(AGwtEntity parent) {
		this.parent = parent;
	}

	@Override
	protected Widget onInitialization() {
		cm.getApp().callRequestComments(parent.getId()); // TODO commentsManagerComponent

		activateCommentLink = new HyperlinkWidget(new ActivateCommentEditorAction());

		editorSubmitToolbar = new ToolbarWidget();
		editorSubmitToolbar.addButton(new PostCommentAction());
		editorSubmitToolbar.addButton(new DeactivateCommentEditorAction());

		widgets = new HashMap<Comment, CommentWidget>();

		containerPanel = new FlowPanel();
		containerPanel.setStyleName("CommentsWidget");

		return containerPanel;
	}

	@Override
	protected void onUpdate() {
		containerPanel.clear();

		if (editor != null) {
			containerPanel.add(editor);
			containerPanel.add(editorSubmitToolbar);
			editor.setFocus(true);
		} else {
			containerPanel.add(activateCommentLink);
		}

		List<Comment> comments = cm.getProjectContext().getComments(parent);
		Collections.sort(comments, Comment.REVERSE_DATEANDTIME_COMPARATOR);
		for (Comment comment : comments) {
			CommentWidget widget = getWidget(comment);
			containerPanel.add(widget);
		}

		super.onUpdate();
	}

	private CommentWidget getWidget(Comment comment) {
		CommentWidget widget = widgets.get(comment);
		if (widget == null) {
			widget = new CommentWidget(comment);
			widgets.put(comment, widget);
		}
		return widget;
	}

	private void postComment() {
		String text = editor.getText().trim();
		if (Gwt.isEmpty(text)) return;
		Comment comment = new Comment(parent, cm.getAuth().getUser(), text);
		cm.getDao().createComment(comment);
		editor = null;
		update();
	}

	private void cancelEditor() {
		editor = null;
		update();
	}

	private void activateEditor() {
		editor = new TextArea();
		editor.setStyleName("CommentsWidget-editor");
		editor.setHeight("100px");
		editor.setWidth("95%");
		editor.addKeyPressHandler(new ActionKeyPressHandler(new PostCommentAction(), true, 13, 10));
		editor.addKeyPressHandler(new ActionKeyPressHandler(new DeactivateCommentEditorAction(), false,
				KeyCodes.KEY_ESCAPE));
		update();
	}

	private class PostCommentAction extends AAction {

		@Override
		public String getLabel() {
			return "Post this comment";
		}

		@Override
		protected void onExecute() {
			postComment();
		}
	}

	private class DeactivateCommentEditorAction extends AAction {

		@Override
		public String getLabel() {
			return "Cancel this comment";
		}

		@Override
		protected void onExecute() {
			cancelEditor();
		}

	}

	private class ActivateCommentEditorAction extends AAction {

		@Override
		public String getLabel() {
			return "Create a comment...";
		}

		@Override
		protected void onExecute() {
			activateEditor();
		}

	}

}
