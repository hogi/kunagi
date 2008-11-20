package scrum.client.common;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widget, which displays fields of an item. A field is a pair of a label and a value.
 * 
 * @param <I> Type of the item, which fields are displayed.
 */
public class ItemFieldsWidget extends Composite {

	private Grid grid;

	public ItemFieldsWidget() {

		grid = null;
		grid = new Grid(0, 2);
		grid.setStyleName(StyleSheet.A_ITEM_FIELDS_WIDGET_GRID);
		grid.setWidth("100%");

		initWidget(grid);
	}

	/**
	 * @param label Label of the field (left).
	 * @param value Value widget of the field (right).
	 */
	public void addField(String label, Widget value) {

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
	}

}
