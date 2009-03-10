package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.GwtLogger;
import scrum.client.common.StyleSheet;
import scrum.client.dnd.DndManager;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TrashWidget extends AWidget {

	private HorizontalPanel trash;

	@Override
	protected Widget onInitialization() {
		trash = new HorizontalPanel();
		trash.setStyleName("TrashWidget");
		trash.add(Img.bundle.trash32().createImage());
		// trash.add(new Label("Trash"));

		DndManager.get().getDragController().registerDropController(trashDropController);

		return trash;
	}

	@Override
	protected void onUpdate() {}

	private DropController trashDropController = new DropController() {

		public Widget getDropTarget() {
			return trash;
		}

		public void onDrop(DragContext context) {
			if (!isTrashable(context.draggable)) return;
			((TrashSupport) context.draggable).trash();
		}

		public void onEnter(DragContext context) {
			if (!isTrashable(context.draggable)) {
				GwtLogger.DEBUG("Not trashable: " + context.draggable);
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

	private boolean isTrashable(Widget draggable) {
		if (draggable instanceof TrashSupport) return ((TrashSupport) draggable).isTrashable();
		return false;
	}

}
