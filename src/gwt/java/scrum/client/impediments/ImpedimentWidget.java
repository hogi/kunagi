package scrum.client.impediments;

import scrum.client.common.BlockWidget;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ImpedimentWidget extends BlockWidget {

	private Impediment impediment;

	public ImpedimentWidget(Impediment impediment) {
		this.impediment = impediment;
	}

	@Override
	protected Widget build() {
		if (isExtended()) {
			ImpedimentFieldsWidget widget = new ImpedimentFieldsWidget();
			widget.setItem(impediment);
			return widget;
		}

		DockPanel dock = new DockPanel();
		Label label = new Label(impediment.label);
		label.setStyleName("ImpedimentWidget-label");
		dock.add(label, DockPanel.NORTH);
		return dock;
	}

}
