package scrum.client.collaboration;

import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import scrum.client.ComponentManager;
import scrum.client.ProjectContext;
import scrum.client.ScrumGwtApplication;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class CommentsWidget extends AWidget {

	private ProjectContext projectContext = ComponentManager.get().getProjectContext();

	private FlowPanel containerPanel;

	private AGwtEntity parent;
	private ToolbarWidget toolbar;

	private Map<Comment, CommentWidget> widgets;

	public CommentsWidget(AGwtEntity parent) {
		this.parent = parent;
	}

	@Override
	protected Widget onInitialization() {
		ScrumGwtApplication.get().callRequestComments(parent.getId());

		widgets = new HashMap<Comment, CommentWidget>();
		containerPanel = new FlowPanel();
		toolbar = new ToolbarWidget();
		toolbar.addHyperlink(new CreateCommentAction(parent, this));

		ScrollPanel scroller = new ScrollPanel(containerPanel);
		containerPanel.setStyleName("CommentsWidget");

		return scroller;
	}

	@Override
	protected void onUpdate() {
		toolbar.update();
		containerPanel.clear();
		containerPanel.add(toolbar);
		List<Comment> comments = projectContext.getComments(parent);
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
