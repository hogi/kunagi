package scrum.client.impediments;

import scrum.client.Client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;

public class ImpedimentsWidget extends Composite {

	private HorizontalSplitPanel split;
	public ImpedimentFieldsWidget details;
	public ImpedimentsTable table;

	public ImpedimentsWidget() {
		table = new ImpedimentsTable();
		table.setItems(Client.project.getImpediments());

		details = new ImpedimentFieldsWidget();

		split = new HorizontalSplitPanel();
		split.setLeftWidget(table);
		split.setRightWidget(details);

		initWidget(split);
		split.setSplitPosition("50%");
	}

}
