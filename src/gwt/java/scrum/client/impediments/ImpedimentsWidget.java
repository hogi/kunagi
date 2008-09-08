package scrum.client.impediments;

import scrum.client.Client;
import scrum.client.common.BlockListWidget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ImpedimentsWidget extends Composite {

	public BlockListWidget list;

	public ImpedimentsWidget() {
		Button createButton = new Button("Create new Impediment");
		createButton.addClickListener(new CreateClickListener());

		HorizontalPanel toolbar = new HorizontalPanel();
		toolbar.setWidth("100%");
		toolbar.setStyleName("Toolbar");
		toolbar.add(createButton);

		list = new BlockListWidget();
		for (Impediment impediment : Client.impediments) {
			list.addBlock(new ImpedimentWidget(impediment));
		}

		DockPanel dock = new DockPanel();
		dock.add(toolbar, DockPanel.NORTH);
		dock.setCellHeight(toolbar, "1%");
		dock.add(list, DockPanel.CENTER);
		dock.setCellHeight(list, "99%");

		initWidget(dock);
	}

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Impediment impediment = Client.createImpediment();
			ImpedimentWidget block = new ImpedimentWidget(impediment);
			list.addBlock(block);
			list.selectBlock(block);
		}
	}

}
