package scrum.client.collaboration;

import ilarkesto.gwt.client.AAction;
import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.AViewEditWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.HyperlinkWidget;
import ilarkesto.gwt.client.editor.ATextEditorModel;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class CommentsWidget extends AScrumWidget {

	private FlowPanel containerPanel;
	private AGwtEntity parent;
	private Map<Comment, CommentWidget> widgets;

	private HyperlinkWidget activateCommentLink;
	private RichtextEditorWidget editor;

	public CommentsWidget(AGwtEntity parent) {
		this.parent = parent;
	}

	@Override
	protected Widget onInitialization() {
		cm.getApp().callRequestComments(parent.getId()); // TODO commentsManagerComponent

		activateCommentLink = new HyperlinkWidget(new ActivateCommentEditorAction());

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
			editor.switchToEditMode();
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
		String text = editor.getEditorText();
		if (Gwt.isEmpty(text)) return;
		text = text.trim();
		Comment comment = new Comment(parent, cm.getAuth().getUser(), text);
		cm.getDao().createComment(comment);
	}

	private void activateEditor() {
		editor = new RichtextEditorWidget(new ATextEditorModel() {

			@Override
			public void setValue(String text) {
				postComment();
			}

			@Override
			public String getValue() {
				return null;
			}
		});
		editor.setModeSwitchHandler(new AViewEditWidget.ModeSwitchHandler() {

			public void onViewerActivated() {
				editor = null;
				update();
			}

			public void onEditorActivated() {}
		});

		update();
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
