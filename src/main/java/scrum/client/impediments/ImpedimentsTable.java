package scrum.client.impediments;

import scrum.client.Client;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.AItemTableWidget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ImpedimentsTable extends AItemTableWidget<Impediment> {

	private Button createButton;

	public ImpedimentsTable() {
		createButton = new Button("New Impediment");
		createButton.addClickListener(new CreateClickListener());
		addToolbarWidget(createButton);
	}

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
	protected void onItemSelected(Impediment item) {
		ScrumGwtApplication.impediments.details.setItem(item);
	}

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Impediment impediment = Client.createImpediment();
			setItems(Client.impediments);
			selectItem(impediment);
		}

	}

}
