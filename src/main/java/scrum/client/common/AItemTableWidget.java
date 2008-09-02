package scrum.client.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.Widget;

public abstract class AItemTableWidget<I> extends Composite {

	private List<I> items = new ArrayList<I>();
	private int selectedRow = -1;

	private FlexTable table;
	private FlowPanel toolbar;

	protected abstract int getColumnCount();

	protected abstract Widget getCell(I item, int column, boolean selected);

	protected abstract void onItemSelected(I item);

	public AItemTableWidget() {
		table = new FlexTable();
		table.setBorderWidth(1);
		table.setWidth("100%");
		table.addTableListener(new Listener());

		ScrollPanel tableScroll = new ScrollPanel(table);
		tableScroll.setWidth("100%");
		tableScroll.setHeight("250px");

		toolbar = new FlowPanel();

		DockPanel tableToolbarDock = new DockPanel();
		tableToolbarDock.setWidth("100%");
		tableToolbarDock.setHeight("100%");
		tableToolbarDock.add(tableScroll, DockPanel.CENTER);
		tableToolbarDock.add(toolbar, DockPanel.SOUTH);

		initWidget(tableToolbarDock);
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
				if (selected) {
					cell.addStyleName("selected");
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

	public void addToolbarWidget(Widget w) {
		toolbar.add(w);
	}

	class Listener implements TableListener {

		public void onCellClicked(SourcesTableEvents sender, int row, int cell) {
			selectRow(row);
		}

	}
}
