package scrum.client.common.editable;

import scrum.client.common.StyleSheet;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class for a editable integer widget.
 * 
 * In view mode this widget displays a label for the value. In edit mode a <code>TextBox</code> is used as
 * editor to allow editing the value.
 */
public abstract class AEditableIntegerWidget extends AEditableWidget {

	private Label viewer;
	private TextBox editor;

	/**
	 * Provide the value for view mode and edit mode.
	 */
	protected abstract Integer getValue();

	/**
	 * Set the value inputed by the user.
	 */
	protected abstract void setValue(Integer value);

	public AEditableIntegerWidget() {
		viewer = new Label();
		viewer.addClickListener(new ViewerClickListener());
		viewer.setStyleName(StyleSheet.VIEWER);
		rebuild();
	}

	@Override
	protected Widget getEditor() {
		if (editor == null) {
			Integer value = getValue();
			String text = value == null ? null : getValue().toString();
			editor = new TextBox();
			editor.addFocusListener(new EditorFocusListener());
			editor.setMaxLength(3);
			editor.setStyleName(StyleSheet.INTEGER_EDITOR);
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
		Integer value = getValue();
		viewer.setText(value == null ? null : value.toString());
		return viewer;
	}

	private class ViewerClickListener implements ClickListener {

		public void onClick(Widget sender) {
			setEditMode(true);
		}

	}

	private class EditorKeyboardListener extends KeyboardListenerAdapter {

		@Override
		public void onKeyPress(Widget sender, char keyCode, int modifiers) {

			if (!Character.isDigit(keyCode) && (keyCode != (char) KEY_ENTER) && (keyCode != (char) KEY_TAB)
					&& (keyCode != (char) KEY_BACKSPACE) && (keyCode != (char) KEY_DELETE)
					&& (keyCode != (char) KEY_ESCAPE) || (Character.valueOf(keyCode) == 46)) {
				// 46 = "."

				((TextBox) sender).cancelKey();
			}

			if (keyCode == KeyboardListener.KEY_ENTER) {
				String text = editor.getText();
				if (text == null) {
					setValue(null);
				} else {
					text = text.trim();
					if (text.length() == 0) {
						setValue(null);
					} else {

						try {
							setValue(Integer.parseInt(text));
						} catch (NumberFormatException e) {
							editor.setText(viewer.getText());
						}

					}
				}
				setEditMode(false);
			}
			if (keyCode == KeyboardListener.KEY_ESCAPE) {
				setEditMode(false);
				editor.setText(viewer.getText());
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

			String text = editor.getText();
			try {
				int value = Integer.parseInt(text);
				setValue(value);
			} catch (NumberFormatException e) {
				editor.setText(viewer.getText());
			}
			setEditMode(false);
		}

	}
}
