package scrum.client.collaboration;

import ilarkesto.gwt.client.AOutputViewEditWidget;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import scrum.client.ScrumGwt;
import scrum.client.admin.User;
import scrum.client.common.AScrumWidget;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CommentWidget extends AScrumWidget {

	private Label date;

	private Comment comment;

	public CommentWidget(Comment comment) {
		this.comment = comment;
	}

	@Override
	protected Widget onInitialization() {
		Label authorLabel = new Label(comment.getAuthorName());
		authorLabel.setStyleName("CommentWidget-header-author");

		User author = comment.getAuthor();
		if (author != null) {
			String color = getCurrentProject().getUserConfig(author).getColor();
			authorLabel.getElement().getStyle().setProperty("color", color);
		}

		date = new Label();
		date.setStyleName("CommentWidget-header-date");

		HorizontalPanel header = new HorizontalPanel();
		header.setStyleName("CommentWidget-header");
		header.add(Gwt.createFlowPanel(authorLabel, date));
		if (getCurrentProject().getHomepageDir() != null) {
			header.add(new AOutputViewEditWidget() {

				@Override
				protected void onViewerUpdate() {
					Widget widget = null;
					if (comment.isPublished()) {
						widget = Img.bundle.publicComment().createImage();
						widget.setTitle("This comment is visible on the homepage.");
					} else {
						widget = new ButtonWidget(new PublishCommentAction(comment)).update();
					}
					setViewer(ScrumGwt.createDiv("Comment-Widget-header-pub", widget));
				}
			});
		}

		FlowPanel panel = new FlowPanel();
		panel.setStyleName("CommentWidget");
		panel.add(header);
		// panel.add(new ATextWidget() {
		//
		// @Override
		// protected void onUpdate() {
		// setHtml(Wiki.toHtml(comment.getText()));
		// }
		// });
		panel.add(new RichtextEditorWidget(comment.getTextModel()));

		return panel;
	}

	@Override
	protected void onUpdate() {
		date.setText(comment.getDateAndTime().getPeriodToNow().toShortestString() + " ago");
		super.onUpdate();
	}

}
