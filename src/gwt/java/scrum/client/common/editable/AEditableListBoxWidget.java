package scrum.client.common.editable;

import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public abstract class AEditableListBoxWidget extends AEditableWidget {

	private Label viewer;
	private ListBox editor;

	/**
	 * Provide the value for view mode and edit mode.
	 */
	protected abstract String getText();

	/**
	 * Set the value inputed by the user.
	 */
	protected abstract void setText(String text);

	public AEditableListBoxWidget() {
		viewer = new Label();
		viewer.addClickListener(new ViewerClickListener());
		rebuild();
	}

	@Override
	protected Widget getEditor() {
		if (editor == null) {
			editor = new ListBox();
			editor.addItem("1");
			editor.addItem("2");
			editor.addItem("3");
			editor.addItem("5");
			editor.addItem("8");
			editor.addItem("13");
			editor.addItem("21");
			editor.addChangeListener(new EditorChangeListener());

			String text = getText();
			for (int i = 0; i < editor.getItemCount(); i++) {
				if (editor.getItemText(i).equals(text) == false) continue;
				editor.setItemSelected(i, true);
			}
		}
		return editor;
	}

	@Override
	protected Widget getViewer() {
		viewer.setText(getText());
		return viewer;
	}

	private class ViewerClickListener implements ClickListener {

		public void onClick(Widget sender) {
			setEditMode(true);
		}

	}

	private class EditorChangeListener implements ChangeListener {

		public void onChange(Widget sender) {
			setText(editor.getItemText(editor.getSelectedIndex()));
		}

	}

}
