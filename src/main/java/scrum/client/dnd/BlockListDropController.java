package scrum.client.dnd;

import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockListWidget;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.allen_sauer.gwt.dnd.client.util.CoordinateLocation;
import com.allen_sauer.gwt.dnd.client.util.WidgetArea;
import com.google.gwt.user.client.ui.Widget;

public class BlockListDropController implements DropController {

	private ABlockWidget dropTarget;
	private BlockListWidget list;

	public BlockListDropController(ABlockWidget dropTarget, BlockListWidget list) {
		this.dropTarget = dropTarget;
		this.list = list;
	}

	public Widget getDropTarget() {
		return dropTarget;
	}

	public void onDrop(DragContext context) {
		WidgetArea area = new WidgetArea(dropTarget, null);
		CoordinateLocation location = new CoordinateLocation(context.mouseX, context.mouseY);

		list.remove((ABlockWidget) context.draggable);
		if (isHigher(area, location)) {
			list.addBlockAt(list.indexOf(dropTarget), (ABlockWidget) context.draggable);
		} else {
			list.addBlockAt(list.indexOf(dropTarget) + 1, (ABlockWidget) context.draggable);
		}
	}

	public void onEnter(DragContext context) {}

	public void onLeave(DragContext context) {}

	public void onMove(DragContext context) {
		WidgetArea area = new WidgetArea(dropTarget, null);
		CoordinateLocation location = new CoordinateLocation(context.mouseX, context.mouseY);
		boolean isHigher = isHigher(area, location);
		// TODO: highlight drop area
	}

	public void onPreviewDrop(DragContext context) throws VetoDragException {
		if (!isDropAllowed(context)) { throw new VetoDragException(); }
	}

	private boolean isHigher(WidgetArea area, CoordinateLocation location) {
		int mid = area.getCenter().getTop();
		return location.getTop() < mid;
	}

	private boolean isDropAllowed(DragContext context) {
		return context.draggable.getClass().equals(dropTarget.getClass());
	}
}
