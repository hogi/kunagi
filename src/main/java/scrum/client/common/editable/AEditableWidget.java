package scrum.client.common.editable;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AEditableWidget extends Composite {

	private static AEditableWidget currentEditable;

	private HorizontalPanel panel;
	private boolean editMode;

	protected abstract Widget getViewer();

	protected abstract Widget getEditor();

	public AEditableWidget() {
		panel = new HorizontalPanel();
		initWidget(panel);
	}

	public void rebuild() {
		panel.clear();
		Widget widget;
		if (editMode) {
			widget = getEditor();
			widget.addStyleName("editableEditor");
		} else {
			widget = getViewer();
			widget.addStyleName("editableViewer");
		}
		panel.add(widget);
		if (editMode) {
			updateEditor();
		} else {
			updateViewer();
		}
	}

	public void setEditMode(boolean editMode) {
		if (editMode && currentEditable != null) {
			currentEditable.setEditMode(false);
		}
		this.editMode = editMode;
		if (editMode) {
			currentEditable = this;
		} else {
			currentEditable = null;
		}
		rebuild();
	}

	protected void updateEditor() {

	}

	protected void updateViewer() {

	}

}
