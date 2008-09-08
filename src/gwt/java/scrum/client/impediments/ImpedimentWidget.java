package scrum.client.impediments;

import scrum.client.Client;
import scrum.client.common.ABlockWidget;
import scrum.client.workspace.WorkspaceWidget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ImpedimentWidget extends ABlockWidget {

	private Impediment impediment;

	public ImpedimentWidget(Impediment impediment) {
		this.impediment = impediment;
	}

	@Override
	protected Widget build() {
		if (!isExtended()) {
			DockPanel dock = new DockPanel();
			Label label = new Label(impediment.label);
			label.setStyleName("ImpedimentWidget-label");
			dock.add(label, DockPanel.NORTH);
			return dock;
		}

		ImpedimentFieldsWidget fieldsWidget = new ImpedimentFieldsWidget(impediment);

		Button deleteButton = new Button("Delete");
		deleteButton.addClickListener(new DeleteClickListener());

		VerticalPanel toolbar = new VerticalPanel();
		toolbar.setStyleName("Toolbar");
		toolbar.add(deleteButton);

		DockPanel dock = new DockPanel();
		dock.add(fieldsWidget, DockPanel.CENTER);
		dock.setCellWidth(fieldsWidget, "99%");
		dock.add(toolbar, DockPanel.EAST);
		dock.setCellWidth(toolbar, "1%");

		return dock;
	}

	private class DeleteClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Client.impediments.remove(impediment);
			WorkspaceWidget.impediments.list.removeSelectedRow();
		}

	}

}
