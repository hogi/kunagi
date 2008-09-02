package scrum.client.impediments;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.AItemTableWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ImpedimentsTable extends AItemTableWidget<Impediment> {

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
	protected void onItemSelected(Impediment item) {
		ScrumGwtApplication.impediments.details.setItem(item);
	}

}
