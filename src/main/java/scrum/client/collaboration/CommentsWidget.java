package scrum.client.collaboration;

import ilarkesto.gwt.client.AWidget;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import scrum.client.project.Requirement;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class CommentsWidget extends AWidget {

	private FlowPanel containerPanel;

	private Requirement parent;

	private Map<Comment, CommentWidget> widgets;

	public CommentsWidget(Requirement parent) {
		this.parent = parent;
	}

	@Override
	protected Widget onInitialization() {
		widgets = new HashMap<Comment, CommentWidget>();
		containerPanel = new FlowPanel();

		ScrollPanel scroller = new ScrollPanel(containerPanel);
		containerPanel.setStyleName("CommentsWidget");

		return scroller;
	}

	@Override
	protected void onUpdate() {
		containerPanel.clear();
		List<Comment> comments = parent.getComments();
		Collections.sort(comments, Comment.REVERSE_DATEANDTIME_COMPARATOR);
		for (Comment comment : comments) {
			CommentWidget widget = getWidget(comment);
			containerPanel.add(widget);
			widget.update();
		}
	}

	private CommentWidget getWidget(Comment comment) {
		CommentWidget widget = widgets.get(comment);
		if (widget == null) {
			widget = new CommentWidget(comment);
			widgets.put(comment, widget);
		}
		return widget;
	}

	public void activateCommentEditor(Comment comment) {
		CommentWidget widget = getWidget(comment);
		widget.update();
		widget.activateEditor();
	}

}
