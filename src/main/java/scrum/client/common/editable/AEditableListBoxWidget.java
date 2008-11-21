package scrum.client.common.editable;

import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public abstract class AEditableListBoxWidget extends AEditableWidget {

	private Label viewer;
	private ListBox listBox;

	/**
	 * Provide the value for view mode.
	 */
	protected abstract String getText();

	protected abstract String getSelectedValue();

	/**
	 * Set the value inputed by the user.
	 */
	protected abstract void setValue(String value);

	protected abstract String[] getSelectableValues();

	public AEditableListBoxWidget() {
		viewer = new Label();
		viewer.addClickListener(new ViewerClickListener());
		rebuild();
	}

	@Override
	protected Widget getEditor() {
		if (listBox == null) {
			listBox = new ListBox();
			for (String item : getSelectableValues()) {
				listBox.addItem(item);
			}
			listBox.addChangeListener(new EditorChangeListener());

			String selectedValue = getSelectedValue();
			for (int i = 0; i < listBox.getItemCount(); i++) {
				if (listBox.getItemText(i).equals(selectedValue)) {
					listBox.setItemSelected(i, true);
					break;
				}
			}
		}
		return listBox;
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
			setValue(listBox.getItemText(listBox.getSelectedIndex()));
		}

	}

}
