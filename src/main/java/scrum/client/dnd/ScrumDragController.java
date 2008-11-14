package scrum.client.dnd;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class ScrumDragController extends PickupDragController {

	public ScrumDragController(AbsolutePanel boundaryPanel, boolean allowDroppingOnBoundaryPanel) {
		super(boundaryPanel, allowDroppingOnBoundaryPanel);
		setBehaviorDragProxy(true);
	}

	@Override
	protected void saveSelectedWidgetsLocationAndStyle() {}

	@Override
	protected void restoreSelectedWidgetsLocation() {}
}
