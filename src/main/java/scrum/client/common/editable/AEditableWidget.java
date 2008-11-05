package scrum.client.common.editable;

import scrum.client.common.StyleSheet;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class for a editable widget.
 * 
 * A editable widget is by default in view mode. It displays a value using the viewer. By clicking on the
 * viewer it is set to <code>editMode</code>. In edit mode the value can be edited in the editor.
 */
public abstract class AEditableWidget extends Composite {

	private static AEditableWidget currentEditable;

	private HorizontalPanel panel;
	private boolean editMode;

	/**
	 * Provides the viewer for view mode.
	 */
	protected abstract Widget getViewer();

	/**
	 * Provides the editor for edit mode.
	 */
	protected abstract Widget getEditor();

	public AEditableWidget() {
		panel = new HorizontalPanel();
		panel.setWidth("100%");
		initWidget(panel);
	}

	/**
	 * Override this method to update the viewer after it is displayed.
	 */
	protected void updateViewer() {}

	/**
	 * Override this method to update the editor after it is displayed.
	 */
	protected void updateEditor() {}

	void rebuild() {
		panel.clear();
		Widget widget;
		if (editMode) {
			widget = getEditor();
			widget.addStyleName(StyleSheet.EDITABLE_EDITOR);
		} else {
			widget = getViewer();
			widget.addStyleName(StyleSheet.EDITABLE_VIEWER);
		}
		panel.add(widget);
		if (editMode) {
			updateEditor();
		} else {
			updateViewer();
		}
	}

	void setEditMode(boolean editMode) {
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

}
