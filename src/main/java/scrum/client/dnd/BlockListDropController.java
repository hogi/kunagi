package scrum.client.dnd;

import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.ClipboardItemWidget;

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
		WidgetArea area = new WidgetArea(targetBlock, null);
		CoordinateLocation location = new CoordinateLocation(context.mouseX, context.mouseY);
		BlockListWidget targetList = targetBlock.getList();

		if (context.draggable instanceof ClipboardItemWidget) {
			// move from clipboard
			ClipboardItemWidget item = (ClipboardItemWidget) context.draggable;

			int fromIndex = targetList.indexOf(item.getSource());
			if (fromIndex < 0) {
				// TODO move data
			}

			int toIndex = targetList.indexOf(targetBlock);
			if (fromIndex > toIndex) toIndex++;
			if (isHigher(area, location)) toIndex--;
			targetList.moveBlock(item.getSource(), toIndex);
			item.removeFromClipboard();
		} else {
			// move in the list
			ABlockWidget draggedBlock = (ABlockWidget) context.draggable;

			int fromIndex = targetList.indexOf(draggedBlock);
			int toIndex = targetList.indexOf(targetBlock);
			if (fromIndex > toIndex) toIndex++;
			if (isHigher(area, location)) toIndex--;
			targetList.moveBlock(draggedBlock, toIndex);
		}
	}

	public void onEnter(DragContext context) {}

	public void onLeave(DragContext context) {
		targetBlock.deactivateDndMarkers();
	}

	public void onMove(DragContext context) {
		WidgetArea area = new WidgetArea(targetBlock, null);
		CoordinateLocation location = new CoordinateLocation(context.mouseX, context.mouseY);

		boolean isHigher = isHigher(area, location);
		if (isHigher) {
			targetBlock.activateDndMarkerTop();
		} else {
			targetBlock.activateDndMarkerBottom();
		}
	}

	public void onPreviewDrop(DragContext context) throws VetoDragException {
		if (!isDropAllowed(context)) { throw new VetoDragException(); }
	}

	private boolean isHigher(WidgetArea area, CoordinateLocation location) {
		int mid = area.getCenter().getTop();
		return location.getTop() < mid;
	}

	private boolean isDropAllowed(DragContext context) {
		if (context.draggable instanceof ClipboardItemWidget) {
			ClipboardItemWidget item = (ClipboardItemWidget) context.draggable;
			return item.getSource().getClass().equals(targetBlock.getClass());
		}
		return context.draggable.getClass().equals(targetBlock.getClass());
	}
}
