package scrum.client.common;

import ilarkesto.gwt.client.AViewEditWidget;
import ilarkesto.gwt.client.AWidget;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widget, which displays fields. A field is a pair of a label and a value.
 */
public class FieldsWidget extends AWidget {

	private Grid grid;
	private List<AWidget> widgets = new ArrayList<AWidget>();

	private AWidget autoUpdateWidget;

	@Override
	protected Widget onInitialization() {
		grid = new Grid(0, 2);
		grid.setStyleName(StyleSheet.A_ITEM_FIELDS_WIDGET_GRID);
		return grid;
	}

	@Override
	protected void onUpdate() {
		for (AWidget widget : widgets) {
			widget.update();
		}
	}

	public <W extends AWidget> W add(String label, W value) {
		widgets.add(value);
		if (autoUpdateWidget != null && value instanceof AViewEditWidget) {
			((AViewEditWidget) value).setAutoUpdateWidget(autoUpdateWidget);
		}
		return addWidget(label, value);
	}

	public Label add(String label, Label value) {
		return addWidget(label, value);
	}

	/**
	 * @param label Label of the field (left).
	 * @param value Value widget of the field (right).
	 */
	public <W extends Widget> W addWidget(String label, W value) {
		initialize();

		if (label == null) label = "";
		Label l = new Label(label);
		l.setStyleName(StyleSheet.FIELD_LABEL);
		value.addStyleName(StyleSheet.FIELD_VALUE);
		l.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		int row = grid.getRowCount();
		grid.resizeRows(row + 1);
		if (row == 0) {
			grid.getCellFormatter().setWidth(row, 0, "25%");
			grid.getCellFormatter().setWidth(row, 1, "75%");
		}
		grid.setWidget(row, 0, l);
		grid.setWidget(row, 1, value);

		return value;
	}

	public void setAutoUpdateWidget(AWidget autoUpdateWidget) {
		this.autoUpdateWidget = autoUpdateWidget;
	}

}
