package scrum.client.project;

import scrum.client.common.BlockListWidget;
import scrum.client.common.Gwt;
import scrum.client.service.Service;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BacklogItemListWidget extends Composite {

	public BlockListWidget list;

	public BacklogItemListWidget() {
		Button createButton = new Button("Create new Backlog-Item");
		createButton.addClickListener(new CreateClickListener());

		HorizontalPanel toolbar = new HorizontalPanel();
		toolbar.setWidth("100%");
		toolbar.setStyleName("Toolbar");
		toolbar.add(createButton);
		Gwt.addFiller(toolbar);

		list = new BlockListWidget();
		for (BacklogItem item : Service.getProject().getBacklogItems()) {
			list.addBlock(new BacklogItemWidget(item));
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
			BacklogItem item = Service.getProject().createNewBacklogItem();
			BacklogItemWidget block = new BacklogItemWidget(item);
			list.addBlock(block);
			list.selectBlock(block);
		}
	}
}
