package scrum.client.collaboration;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CommentWidget extends AScrumWidget {

	private ARichtextViewEditWidget editor;

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

		Label date = new Label(comment.getDateAndTime().toString());
		date.setStyleName("CommentWidget-header-date");

		FlowPanel header = new FlowPanel();
		header.setStyleName("CommentWidget-header");
		header.add(author);
		header.add(date);

		editor = new ARichtextViewEditWidget() {

			@Override
			protected void onEditorSubmit() {
				comment.setText(getEditorText());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(comment.getText());
			}

			@Override
			protected void onViewerUpdate() {
				setViewerText(comment.getText());
			}

			@Override
			public boolean isEditable() {
				return comment.getAuthor().equals(getCurrentUser());
			}

		};

		FlowPanel panel = new FlowPanel();
		panel.setStyleName("CommentWidget");
		panel.add(header);
		panel.add(editor);

		return panel;
	}

	public void activateEditor() {
		editor.switchToEditMode();
	}

}
