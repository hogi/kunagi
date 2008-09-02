package scrum.client.impediments;

import scrum.client.Client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ImpedimentsWidget extends Composite {

	ImpedimentsTable table;
	ImpedimentFieldsWidget details;

	public ImpedimentsWidget() {
		table = new ImpedimentsTable();
		table.setItems(Client.project.getImpediments());

		details = new ImpedimentFieldsWidget();

		HorizontalSplitPanel split = new HorizontalSplitPanel();
		split.setLeftWidget(table);
		split.setRightWidget(details);
		split.setSplitPosition("300px");

		Button createButton = new Button("New Impediment");
		createButton.addClickListener(new CreateClickListener());

		VerticalPanel toolbar = new VerticalPanel();
		toolbar.add(createButton);

		DockPanel dock = new DockPanel();
		dock.add(toolbar, DockPanel.NORTH);
		dock.setCellHeight(toolbar, "1%");
		dock.add(split, DockPanel.CENTER);
		dock.setCellHeight(split, "99%");

		initWidget(dock);
	}

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Impediment impediment = Client.createImpediment();
			table.setItems(Client.impediments);
			table.selectItem(impediment);
		}

	}

}
