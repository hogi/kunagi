package scrum.client.collaboration;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CommentWidget extends AWidget {

	private Label label;

	private ARichtextViewEditWidget editor;

	private Comment comment;

	public CommentWidget(Comment comment) {
		this.comment = comment;
	}

	@Override
	protected Widget onInitialization() {
		label = new Label(comment.getAuthor().getName());
		label.setStyleName("UserStatusWidget");

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
				return comment.getAuthor().equals(ScrumGwtApplication.get().getUser());
			}

		};

		FlowPanel panel = new FlowPanel();
		panel.add(label);
		panel.add(editor);

		return panel;
	}

	@Override
	protected void onUpdate() {
		editor.update();
	}

}
