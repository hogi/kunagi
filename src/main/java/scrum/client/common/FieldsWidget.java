package scrum.client.common;

import ilarkesto.gwt.client.AViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.Gwt;

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
	private List<Widget> widgets = new ArrayList<Widget>();

	private Widget autoUpdateWidget = Gwt.rootWidget;

	@Override
	protected Widget onInitialization() {
		grid = new Grid(0, 2);
		grid.setWidth("100%");
		grid.setStyleName("FieldsWidget-grid");
		return grid;
	}

	@Override
	protected void onUpdate() {
		Gwt.update(widgets);
	}

	public <W extends Widget> W add(String label, W value) {
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
		assert value != null;
		initialize();

		if (label == null) label = "";
		Label l = new Label(label);
		l.setStyleName("FieldsWidget-fieldLabel");
		value.addStyleName("FieldsWidget-fieldValue");
		l.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		int row = grid.getRowCount();
		grid.resizeRows(row + 1);
		if (row == 0) {
			grid.getCellFormatter().setWidth(row, 0, "10%");
			grid.getCellFormatter().setWidth(row, 1, "90%");
		}
		grid.setWidget(row, 0, l);
		grid.setWidget(row, 1, value);

		return value;
	}

	public void setAutoUpdateWidget(AWidget autoUpdateWidget) {
		this.autoUpdateWidget = autoUpdateWidget;
	}

}
