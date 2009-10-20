package scrum.client.collaboration;

import ilarkesto.gwt.client.ATextWidget;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CommentWidget extends AScrumWidget {

	private Label date;
	private ATextWidget commentText;

	private Comment comment;

	public CommentWidget(Comment comment) {
		this.comment = comment;
	}

	@Override
	protected Widget onInitialization() {
		Label author = new Label(comment.getAuthor().getName());
		author.setStyleName("CommentWidget-header-author");
		String color = getCurrentProject().getUserConfig(comment.getAuthor()).getColor();
		author.getElement().getStyle().setProperty("color", color);

		date = new Label();
		date.setStyleName("CommentWidget-header-date");

		FlowPanel header = new FlowPanel();
		header.setStyleName("CommentWidget-header");
		header.add(author);
		header.add(date);

		commentText = new ATextWidget() {

			@Override
			protected void onUpdate() {
				setHtml(cm.getRichtextConverter().toHtml(comment.getText()));
			}
		};

		FlowPanel panel = new FlowPanel();
		panel.setStyleName("CommentWidget");
		panel.add(header);
		panel.add(commentText);

		return panel;
	}

	@Override
	protected void onUpdate() {
		date.setText(comment.getDateAndTime().getPeriodToNow().toShortestString() + " ago");
		super.onUpdate();
	}

}
