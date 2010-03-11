package scrum.client.workspace;

import java.util.HashMap;
import java.util.Map;

import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.dnd.BlockDropController;
import scrum.client.dnd.BlockListDropController;
import scrum.client.dnd.ScrumDragController;

import com.google.gwt.user.client.ui.Widget;

public class DndManager extends GDndManager {

	private ScrumDragController dragController;
	private Map<ABlockWidget, BlockDropController> blockDropControllers;
	private Map<BlockListWidget, BlockListDropController> blockListDropControllers;

	public DndManager() {
		dragController = new ScrumDragController();
		blockDropControllers = new HashMap<ABlockWidget, BlockDropController>();
		blockListDropControllers = new HashMap<BlockListWidget, BlockListDropController>();
	}

	public void registerDropTarget(BlockListWidget list) {
		BlockListDropController dropController = new BlockListDropController(list);
		dragController.registerDropController(dropController);
		blockListDropControllers.put(list, dropController);
	}

	public void unregisterDropTarget(BlockListWidget list) {
		BlockListDropController dropController = blockListDropControllers.get(list);
		if (dropController != null) {
			dragController.unregisterDropController(dropController);
			blockListDropControllers.remove(list);
		}
	}

	public void registerDropTarget(ABlockWidget block) {
		BlockDropController dropController = new BlockDropController(block);
		dragController.registerDropController(dropController);
		blockDropControllers.put(block, dropController);
	}

	public void unregisterDropTarget(ABlockWidget block) {
		BlockDropController dropController = blockDropControllers.get(block);
		if (dropController != null) {
			dragController.unregisterDropController(dropController);
			blockDropControllers.remove(block);
		}
	}

	public void makeDraggable(Widget widget, Widget dragHandle) {
		dragController.makeDraggable(widget, dragHandle);
	}

	public ScrumDragController getDragController() {
		return dragController;
	}
}
