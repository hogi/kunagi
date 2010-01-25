package scrum.client.dnd;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.user.client.ui.RootPanel;

public class ScrumDragController extends PickupDragController {

	public ScrumDragController() {
		super(RootPanel.get(), false);
		setBehaviorDragProxy(true);
	}

	@Override
	protected void saveSelectedWidgetsLocationAndStyle() {}

	@Override
	protected void restoreSelectedWidgetsLocation() {}
}
