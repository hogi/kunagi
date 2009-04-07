package scrum.client.dnd;

import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.workspace.ClipboardItemWidget;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.allen_sauer.gwt.dnd.client.util.CoordinateLocation;
import com.allen_sauer.gwt.dnd.client.util.WidgetArea;
import com.google.gwt.user.client.ui.Widget;

public class BlockListDropController implements DropController {

	private ABlockWidget targetBlock;

	public BlockListDropController(ABlockWidget targetBlock) {
		this.targetBlock = targetBlock;
	}

	public Widget getDropTarget() {
		return targetBlock;
	}

	public void onDrop(DragContext context) {
		Widget draggable = context.draggable;
		if (!isDropAllowed(draggable)) return;

		WidgetArea area = new WidgetArea(targetBlock, null);
		CoordinateLocation location = new CoordinateLocation(context.mouseX, context.mouseY);
		BlockListWidget targetList = targetBlock.getList();

		if (draggable instanceof ABlockWidget) {
			// move inside list
			ABlockWidget draggedBlock = (ABlockWidget) draggable;
			int fromIndex = targetList.indexOf(draggedBlock);
			int toIndex = targetList.indexOf(targetBlock);
			if (fromIndex > toIndex) toIndex++;
			if (isHigher(area, location)) toIndex--;
			targetList.moveBlock(draggedBlock, toIndex);
		} else if (draggable instanceof ClipboardItemWidget) {
			// move from clipboard
			ClipboardItemWidget item = (ClipboardItemWidget) draggable;

			int fromIndex = targetList.indexOf(item.getPayload());
			if (fromIndex < 0) {
				// TODO move data
			}

			int toIndex = targetList.indexOf(targetBlock);
			if (fromIndex > toIndex) toIndex++;
			if (isHigher(area, location)) toIndex--;
			targetList.moveBlock(item.getPayload(), toIndex);
			item.removeFromClipboard();
		}
	}

	public void onEnter(DragContext context) {}

	public void onLeave(DragContext context) {
		targetBlock.getList().deactivateDndMarkers(targetBlock);
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
			return block.getList() == targetBlock.getList();
		}
		if (draggable instanceof ClipboardItemWidget) {
			ClipboardItemWidget item = (ClipboardItemWidget) draggable;
			return item.getPayload().getList() == targetBlock.getList();
		}
		return false;
	}
}
