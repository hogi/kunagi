package scrum.client.common;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widget, which displays fields of an item. A field is a pair of a label and a value.
 */
public abstract class AItemFieldsWidget<I> extends Composite {

	public I item;

	private DockPanel dock;
	private FlexTable table;

	protected abstract void build();

	public AItemFieldsWidget() {
		dock = new DockPanel();
		initWidget(dock);
		setWidth("100%");
		rebuild();
	}

	public void setItem(I item) {
		this.item = item;
		rebuild();
	}

	public void rebuild() {
		dock.clear();
		table = null;
		if (item == null) {
			dock.add(getNothing(), DockPanel.CENTER);
			return;
		}

		table = new FlexTable();
		table.setStyleName("AItemFieldsWidget-table");
		table.setWidth("100%");

		build();

		dock.add(table, DockPanel.CENTER);
	}

	protected void addField(String label, Widget value) {

		Label l = new Label(label);
		l.setStyleName("fieldLabel");

		value.addStyleName("fieldValue");

		int row = table.getRowCount();
		table.setWidget(row, 0, l);
		table.setWidget(row, 1, value);
	}

	protected Widget getNothing() {
		return new Label("-");
	}

}
