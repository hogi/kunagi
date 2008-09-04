package scrum.client.common.editable;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public abstract class AEditableTextWidget extends AEditableWidget {

	private Label viewer;
	private TextBox editor;

	protected abstract String getText();

	protected abstract void setText(String text);

	public AEditableTextWidget() {
		viewer = new Label();
		viewer.addClickListener(new ViewerClickListener());
		rebuild();
	}

	@Override
	protected Widget getEditor() {
		if (editor == null) {
			String text = getText();
			editor = new TextBox();
			editor.setText(text);
			editor.addKeyboardListener(new EditorKeyboardListener());
		}
		return editor;
	}

	@Override
	protected void updateEditor() {
		editor.setSelectionRange(0, editor.getText().length());
	}

	@Override
	protected Widget getViewer() {
		viewer.setText(getText());
		return viewer;
	}

	class ViewerClickListener implements ClickListener {

		public void onClick(Widget sender) {
			setEditMode(true);
		}

	}

	class EditorKeyboardListener extends KeyboardListenerAdapter {

		@Override
		public void onKeyPress(Widget sender, char keyCode, int modifiers) {
			if (keyCode == KeyboardListener.KEY_ENTER) {
				setText(editor.getText());
				setEditMode(false);
			}
			if (keyCode == KeyboardListener.KEY_ESCAPE) {
				setEditMode(false);
			}
		}

	}
}
