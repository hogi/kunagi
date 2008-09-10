package scrum.client.test;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.drop.AbsolutePositionDropController;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class TestWidget extends Composite {

	public TestWidget() {
		AbsolutePanel dndPanel = new AbsolutePanel();
		dndPanel.setPixelSize(400, 400);

		PickupDragController dragController = new PickupDragController(RootPanel.get(), true);
		dragController.setBehaviorConstrainedToBoundaryPanel(false);
		dragController.setBehaviorMultipleSelection(true);
		DropController dropController = new AbsolutePositionDropController(dndPanel);
		dragController.registerDropController(dropController);

		Label drag = new Label("drag");
		dragController.makeDraggable(drag);

		dndPanel.add(drag);
		initWidget(dndPanel);
	}
}
