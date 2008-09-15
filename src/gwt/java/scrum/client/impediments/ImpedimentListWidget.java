package scrum.client.impediments;

import scrum.client.common.BlockListWidget;
import scrum.client.common.Gwt;
import scrum.client.service.Service;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ImpedimentListWidget extends Composite {

	public BlockListWidget list;

	public ImpedimentListWidget() {
		Button createButton = new Button("Create new Impediment");
		createButton.addClickListener(new CreateClickListener());

		HorizontalPanel toolbar = new HorizontalPanel();
		toolbar.setWidth("100%");
		toolbar.setStyleName("Toolbar");
		toolbar.add(createButton);
		Gwt.addFiller(toolbar);

		list = new BlockListWidget();
		for (Impediment impediment : Service.getProject().getImpediments()) {
			list.addBlock(new ImpedimentWidget(impediment));
		}

		DockPanel dock = new DockPanel();
		dock.setWidth("100%");
		dock.add(toolbar, DockPanel.NORTH);
		dock.setCellHeight(toolbar, "1%");
		dock.add(list, DockPanel.CENTER);
		dock.setCellHeight(list, "99%");

		initWidget(dock);
	}

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Impediment impediment = Service.getProject().createNewImpediment();
			ImpedimentWidget block = new ImpedimentWidget(impediment);
			list.addBlock(block);
			list.selectBlock(block);
		}
	}

}
