package scrum.client.dnd;

import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockListWidget;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.allen_sauer.gwt.dnd.client.util.CoordinateLocation;
import com.allen_sauer.gwt.dnd.client.util.WidgetArea;
import com.google.gwt.user.client.ui.Widget;

public class BlockDropController implements DropController {

	private BlockListWidget targetList;
	private ABlockWidget targetBlock;

	public BlockDropController(ABlockWidget targetBlock) {
		this.targetBlock = targetBlock;
		this.targetList = targetBlock.getList();
	}

	public Widget getDropTarget() {
		return targetBlock;
	}

	public void onDrop(DragContext context) {
		Widget draggable = context.draggable;
		if (!isDropAllowed(draggable)) return;

		WidgetArea area = new WidgetArea(targetBlock, null);
		CoordinateLocation location = new CoordinateLocation(context.mouseX, context.mouseY);

		if (draggable instanceof ABlockWidget) {
			// move inside list
			ABlockWidget draggedBlock = (ABlockWidget) draggable;
			int fromIndex = targetList.indexOfBlock(draggedBlock);
			int toIndex = targetList.indexOfBlock(targetBlock);
			if (fromIndex > toIndex) toIndex++;
			if (isHigher(area, location)) toIndex--;
			targetList.drop(draggedBlock, toIndex);
		}
	}

	public void onEnter(DragContext context) {}

	public void onLeave(DragContext context) {
		targetList.deactivateDndMarkers();
	}

	public void onMove(DragContext context) {
		if (!isDropAllowed(context.draggable)) return;

		WidgetArea area = new WidgetArea(targetBlock, null);
		CoordinateLocation location = new CoordinateLocation(context.mouseX, context.mouseY);
		boolean isHigher = isHigher(area, location);
		if (isHigher) {
			targetBlock.getList().activateDndMarkerBefore(targetBlock);
		} else {
			targetBlock.getList().activateDndMarkerAfter(targetBlock);
		}
	}

	public void onPreviewDrop(DragContext context) throws VetoDragException {
		if (!isDropAllowed(context.draggable)) { throw new VetoDragException(); }
	}

	private boolean isHigher(WidgetArea area, CoordinateLocation location) {
		int mid = area.getCenter().getTop();
		return location.getTop() < mid;
	}

	private boolean isDropAllowed(Widget draggable) {
		if (draggable instanceof ABlockWidget) {
			ABlockWidget block = (ABlockWidget) draggable;
			return targetBlock.getList().acceptsDrop(block);
		}
		return false;
	}
}
