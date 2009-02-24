package scrum.client.dnd;

import java.util.HashMap;
import java.util.Map;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;

import com.google.gwt.user.client.ui.Widget;

public class DndManager {

	private ScrumDragController dragController;
	private Map<ABlockWidget, BlockListDropController> blockListDropControllers;

	public DndManager() {
		dragController = new ScrumDragController();
		blockListDropControllers = new HashMap<ABlockWidget, BlockListDropController>();
	}

	public void registerDropTarget(ABlockWidget block) {
		BlockListDropController dropController = new BlockListDropController(block);
		dragController.registerDropController(dropController);
		blockListDropControllers.put(block, dropController);
	}

	public void unregisterDropTarget(ABlockWidget block) {
		BlockListDropController dropController = blockListDropControllers.get(block);
		if (dropController != null) {
			dragController.unregisterDropController(dropController);
			blockListDropControllers.remove(block);
		}
	}

	public void makeDraggable(Widget widget, Widget handle) {
		dragController.makeDraggable(widget, handle);
	}

	public static DndManager get() {
		return ScrumGwtApplication.get().getDndManager();
	}

	@Deprecated
	public ScrumDragController getDragController() {
		return dragController;
	}

}
