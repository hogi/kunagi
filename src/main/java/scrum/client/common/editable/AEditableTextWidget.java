package scrum.client.common.editable;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class for a editable text widget.
 * 
 * In view mode this widget displays a label for the value. In edit mode a <code>TextBox</code> is used as
 * editor to allow editing the value.
 */
public abstract class AEditableTextWidget extends AEditableWidget {

	private Label viewer;
	private TextBox editor;

	/**
	 * Provide the value for view mode and edit mode.
	 */
	protected abstract String getText();

	/**
	 * Set the value inputed by the user.
	 */
	protected abstract void setText(String text);

	public AEditableTextWidget() {
		viewer = new Label();
		viewer.addClickListener(new ViewerClickListener());
		viewer.setSize("100%", "20px");
		rebuild();
	}

	@Override
	protected Widget getEditor() {
		if (editor == null) {
			String text = getText();
			editor = new TextBox();
			editor.addFocusListener(new EditorFocusListener());
			editor.setText(text);
			editor.addKeyboardListener(new EditorKeyboardListener());
		}
		return editor;
	}

	@Override
	protected void updateEditor() {
		editor.setText(viewer.getText());
		editor.setSelectionRange(0, editor.getText().length());
		editor.setFocus(true);
	}

	@Override
	protected Widget getViewer() {
		viewer.setText(getText());
		return viewer;
	}

	private class ViewerClickListener implements ClickListener {

		public void onClick(Widget sender) {
			if (getEditMode() != true) {
				setEditMode(true);
			}
		}

	}

	private class EditorKeyboardListener extends KeyboardListenerAdapter {

		@Override
		public void onKeyPress(Widget sender, char keyCode, int modifiers) {
			if (keyCode == KeyboardListener.KEY_ENTER) {
				setText(editor.getText());
				setEditMode(false);
			}
			if (keyCode == KeyboardListener.KEY_ESCAPE) {
				editor.setText(viewer.getText());
				setEditMode(false);
			}
		}

	}

	private class EditorFocusListener implements FocusListener {

		public void onFocus(Widget sender) {
			if (getEditMode() != true) {
				setEditMode(true);
			}
		}

		public void onLostFocus(Widget sender) {
			setText(editor.getText());
			setEditMode(false);
		}

	}

}
