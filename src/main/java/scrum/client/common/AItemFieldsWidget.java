package scrum.client.common;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class AItemFieldsWidget<I> extends Composite {

	public I item;

	private DockPanel dock = new DockPanel();
	private FlexTable fieldTable;

	protected abstract void build();

	public AItemFieldsWidget() {
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
		fieldTable = null;
		if (item == null) {
			dock.add(getNothing(), DockPanel.CENTER);
			return;
		}

		fieldTable = new FlexTable();

		build();

		dock.add(fieldTable, DockPanel.CENTER);
	}

	protected void addField(String label, Widget value) {

		Label l = new Label(label);
		l.setStyleName("fieldLabel");

		value.addStyleName("fieldValue");

		int row = fieldTable.getRowCount();
		fieldTable.setWidget(row, 0, l);
		fieldTable.setWidget(row, 1, value);
	}

	protected Widget getNothing() {
		return new Label("-");
	}

}
