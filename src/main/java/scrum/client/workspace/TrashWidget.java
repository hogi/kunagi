package scrum.client.workspace;

import ilarkesto.core.logging.Log;
import ilarkesto.core.scope.Scope;
import scrum.client.common.AScrumWidget;
import scrum.client.common.StyleSheet;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TrashWidget extends AScrumWidget {

	private DndManager dndManager;
	private HorizontalPanel trash;
	private DropController trashDropController = new TrashDropController();

	@Override
	protected Widget onInitialization() {
		dndManager = Scope.get().getComponent(DndManager.class);

		trash = new HorizontalPanel();
		trash.setStyleName("TrashWidget");
		trash.add(Img.bundle.trash32().createImage());
		// trash.add(new Label("Trash"));

		dndManager.getDragController().registerDropController(trashDropController);

		return trash;
	}

	@Override
	protected void onUpdate() {}

	private boolean isTrashable(Widget draggable) {
		boolean trashable;
		if (draggable instanceof TrashSupport) {
			trashable = ((TrashSupport) draggable).getTrashAction().isExecutable();
		} else {
			trashable = false;
		}
		Log.DEBUG(trashable ? "Trashable:" : "Not Trashable:", draggable);
		return trashable;
	}

	private class TrashDropController implements DropController {

		public Widget getDropTarget() {
			return trash;
		}

		public void onDrop(DragContext context) {
			if (!isTrashable(context.draggable)) return;
			((TrashSupport) context.draggable).getTrashAction().execute();
		}

		public void onEnter(DragContext context) {
			if (!isTrashable(context.draggable)) {
				Log.DEBUG("Not trashable: " + context.draggable);
				return;
			}
			trash.addStyleName(StyleSheet.DND_DROP_ALLOWED);
		}

		public void onLeave(DragContext context) {
			trash.removeStyleName(StyleSheet.DND_DROP_ALLOWED);
		}

		public void onMove(DragContext context) {}

		public void onPreviewDrop(DragContext context) throws VetoDragException {}

	};

}
