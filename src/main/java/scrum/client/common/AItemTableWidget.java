package scrum.client.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * Table designed for presenting a specific item per row.
 */
public abstract class AItemTableWidget<I> extends Composite {

	private static final String STYLE_CELL_SELECTED = "AItemTableWidget-table-cell-selected";

	private List<I> items = new ArrayList<I>();
	private int selectedRow = -1;

	private FlexTable table;

	protected abstract int getColumnCount();

	protected abstract Widget getCell(I item, int column);

	protected abstract void onItemSelected(I item);

	public AItemTableWidget() {
		table = new FlexTable();
		// table.setBorderWidth(1);
		table.addTableListener(new Listener());
		table.setWidth("100%");
		initWidget(table);
	}

	public void setItems(Collection<I> items) {
		this.items = new ArrayList<I>(items);
		rebuild();
	}

	public void addItem(I item) {
		items.add(item);
		updateRow(items.size() - 1);
	}

	public void removeItem(I item) {
		int row = items.indexOf(item);
		if (row < 0) return;
		unselect();
		items.remove(row);
		table.removeRow(row);
	}

	private void rebuild() {
		unselect();
		table.clear();
		int rowCount = items.size();
		for (int row = 0; row < rowCount; row++) {
			updateRow(row);
		}
	}

	public void updateRow(int row) {
		I item = items.get(row);
		int columnCount = getColumnCount();
		for (int column = 0; column < columnCount; column++) {
			Widget cell = getCell(item, column);
			cell.addStyleName("AItemTableWidget-table-cell");
			if (row == selectedRow) {
				cell.addStyleName(STYLE_CELL_SELECTED);
			}
			table.setWidget(row, column, cell);
		}
	}

	public void updateSelectedRow() {
		int row = getSelectedRow();
		if (row < 0 || row > items.size()) return;
		updateRow(row);
	}

	public void unselect() {
		if (selectedRow < 0) return;

		int columnCount = getColumnCount();
		for (int column = 0; column < columnCount; column++) {
			Widget cell = table.getWidget(selectedRow, column);
			cell.removeStyleName(STYLE_CELL_SELECTED);
		}

		selectedRow = -1;
		onItemSelected(null);
	}

	private void selectRow(int row) {
		unselect();
		this.selectedRow = row;

		int columnCount = getColumnCount();
		for (int column = 0; column < columnCount; column++) {
			Widget cell = table.getWidget(selectedRow, column);
			cell.addStyleName(STYLE_CELL_SELECTED);
		}

		onItemSelected(getSelectedItem());
	}

	public void selectItem(I item) {
		int row = items.indexOf(item);
		selectRow(row);
	}

	public int getSelectedRow() {
		return selectedRow;
	}

	public I getSelectedItem() {
		if (selectedRow < 0 || selectedRow >= items.size()) return null;
		return items.get(selectedRow);
	}

	class Listener implements TableListener {

		public void onCellClicked(SourcesTableEvents sender, int row, int cell) {
			selectRow(row);
		}

	}
}
