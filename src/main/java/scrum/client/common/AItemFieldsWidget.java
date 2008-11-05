package scrum.client.common;


import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widget, which displays fields of an item. A field is a pair of a label and a value.
 * 
 * @param <I> Type of the item, which fields are displayed.
 */
public abstract class AItemFieldsWidget<I> extends Composite {

	public I item;

	private DockPanel dock;
	private Grid grid;

	protected abstract void build();

	public AItemFieldsWidget(I item) {
		this.item = item;
		dock = new DockPanel();
		initWidget(dock);
		setWidth("100%");
		rebuild();
	}

	private void rebuild() {
		dock.clear();
		grid = null;

		grid = new Grid(0, 2);
		grid.setStyleName(StyleSheet.A_ITEM_FIELDS_WIDGET_GRID);
		grid.setWidth("100%");

		build();

		dock.add(grid, DockPanel.CENTER);
	}

	/**
	 * Subclasses should call this method in their <code>build()</code>-method to add fields.
	 * 
	 * @param label Label of the field (left).
	 * @param value Value widget of the field (right).
	 */
	protected void addField(String label, Widget value) {

		Label l = new Label(label);
		l.setStyleName(StyleSheet.FIELD_LABEL);

		value.addStyleName(StyleSheet.FIELD_VALUE);

		int row = grid.getRowCount();
		grid.resizeRows(row + 1);
		if (row == 0) {
			grid.getCellFormatter().setWidth(row, 0, "1%");
			grid.getCellFormatter().setWidth(row, 1, "99%");
		}
		grid.setWidget(row, 0, l);
		grid.setWidget(row, 1, value);
	}

}
