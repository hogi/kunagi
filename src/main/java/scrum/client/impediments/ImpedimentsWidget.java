package scrum.client.impediments;

import scrum.client.Client;
import scrum.client.common.AItemTableWidget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ImpedimentsWidget extends Composite {

	ImpedimentsTable table;
	ImpedimentFieldsWidget details;
	Button deleteButton;

	public ImpedimentsWidget() {
		details = new ImpedimentFieldsWidget();

		Button createButton = new Button("New Impediment");
		createButton.addClickListener(new CreateClickListener());
		deleteButton = new Button("Delete Impediment");
		deleteButton.addClickListener(new DeleteClickListener());

		HorizontalPanel toolbar = new HorizontalPanel();
		toolbar.add(createButton);
		toolbar.add(deleteButton);

		table = new ImpedimentsTable();
		table.setItems(Client.project.getImpediments());

		HorizontalSplitPanel split = new HorizontalSplitPanel();
		split.setLeftWidget(table);
		split.setRightWidget(details);
		split.setSplitPosition("300px");

		DockPanel dock = new DockPanel();
		dock.add(toolbar, DockPanel.NORTH);
		dock.setCellHeight(toolbar, "1%");
		dock.add(split, DockPanel.CENTER);
		dock.setCellHeight(split, "99%");

		initWidget(dock);
	}

	class ImpedimentsTable extends AItemTableWidget<Impediment> {

		public ImpedimentsTable() {}

		@Override
		protected int getColumnCount() {
			return 1;
		}

		@Override
		protected Widget getCell(Impediment item, int column, boolean selected) {
			switch (column) {
				case 0:
					return new Label(item.label);
			}
			return null;
		}

		@Override
		protected void onItemSelected(Impediment item) {}

		@Override
		public void rebuild() {
			super.rebuild();
			Impediment impediment = getSelectedItem();
			deleteButton.setEnabled(impediment != null);
			details.setItem(impediment);
		}

	}

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Impediment impediment = Client.createImpediment();
			table.setItems(Client.impediments);
			table.selectItem(impediment);
		}

	}

	class DeleteClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Impediment impediment = table.getSelectedItem();
			if (impediment == null) return;
			Client.deleteImpediment(impediment);
			table.setItems(Client.impediments);
		}

	}

}
