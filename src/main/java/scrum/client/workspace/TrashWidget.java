package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.StyleSheet;
import scrum.client.dnd.DndManager;
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
		trash.add(Img.icons().trashIcon32().createImage());
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
			Widget widget = context.draggable;
			if (widget instanceof ABlockWidget) {
				((ABlockWidget) widget).delete();
			}
		}

		public void onEnter(DragContext context) {
			trash.addStyleName(StyleSheet.DND_DROP_ALLOWED);
		}

		public void onLeave(DragContext context) {
			trash.removeStyleName(StyleSheet.DND_DROP_ALLOWED);
		}

		public void onMove(DragContext context) {}

		public void onPreviewDrop(DragContext context) throws VetoDragException {
		// TODO: check if item can be removed
		// TODO: ask if item should be removed
		}

	};

}
