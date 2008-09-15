package scrum.client.sprint;

import scrum.client.common.BlockListWidget;
import scrum.client.common.Gwt;
import scrum.client.service.Service;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;


public class SprintListWidget extends Composite implements ClickListener {

	public BlockListWidget list;
	
	public SprintListWidget() {
		Button createButton = new Button("Create new Sprint");
		createButton.addClickListener(this);

		HorizontalPanel toolbar = new HorizontalPanel();
		toolbar.setWidth("100%");
		toolbar.setStyleName("Toolbar");
		toolbar.add(createButton);
		Gwt.addFiller(toolbar);
		
		list = new BlockListWidget();
		for (Sprint sprint : Service.getProject().getSprints()) {
			list.addBlock(new SprintWidget(sprint));
		}
		
		DockPanel dock = new DockPanel();
		dock.setWidth("100%");
		dock.add(toolbar, DockPanel.NORTH);
		dock.setCellHeight(toolbar, "1%");
		dock.add(list, DockPanel.CENTER);
		dock.setCellHeight(list, "99%");

		initWidget(dock);
	}

	public void onClick(Widget sender) {
		
	}
	
}
