package scrum.client.collaboration;

import ilarkesto.gwt.client.AWidget;
import scrum.client.project.Requirement;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class CommentsWidget extends AWidget {

	private FlowPanel containerPanel;

	private Requirement parent;

	// private Map<Comment, CommentWidget> commentWidgets = new HashMap<Comment, CommentWidget>();

	public CommentsWidget(Requirement parent) {
		this.parent = parent;
	}

	@Override
	protected Widget onInitialization() {
		containerPanel = new FlowPanel();
		containerPanel.setStyleName("UsersStatusWidget");

		return containerPanel;
	}

	@Override
	protected void onUpdate() {
		containerPanel.clear();

		for (Comment comment : parent.getComments()) {
			// CommentWidget widget = commentWidgets.get(comment);
			// if (widget == null) {
			// widget = new CommentWidget(comment);
			// commentWidgets.put(comment, widget);
			// }
			CommentWidget widget = new CommentWidget(comment);
			containerPanel.add(widget);
			widget.update();
		}
	}

}
