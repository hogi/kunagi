package scrum.client.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.Widget;

public abstract class AItemTableWidget<I> extends Composite {

	private List<I> items = new ArrayList<I>();
	private int selectedRow = -1;

	private FlexTable table;

	protected abstract int getColumnCount();

	protected abstract Widget getCell(I item, int column, boolean selected);

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

	public void rebuild() {
		table.clear();
		int columnCount = getColumnCount();
		int row = 0;
		for (I item : items) {
			boolean selected = row == selectedRow;
			for (int column = 0; column < columnCount; column++) {
				Widget cell = getCell(item, column, selected);
				cell.setStyleName("AItemTableWidget-table-cell");
				if (selected) {
					cell.addStyleName("AItemTableWidget-table-cell-selected");
				}
				table.setWidget(row, column, cell);
			}
			row++;
		}
	}

	public void selectRow(int row) {
		this.selectedRow = row;
		rebuild();
		I item = row < items.size() ? items.get(row) : null;
		onItemSelected(item);
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
