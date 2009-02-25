package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;

import java.util.ArrayList;

import scrum.client.common.ABlockWidget;
import scrum.client.common.StyleSheet;
import scrum.client.dnd.DndManager;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class ClipboardWidget extends AWidget {

	private FlowPanel panel;
	private ArrayList<ClipboardItemWidget> clipboardItems = new ArrayList<ClipboardItemWidget>();

	@Override
	protected Widget onInitialization() {
		panel = new FlowPanel();
		panel.setStyleName("ClipboardWidget");

		DndManager.get().getDragController().registerDropController(itemDropController);

		return panel;
	}

	@Override
	protected void onUpdate() {
		panel.clear();
		for (ClipboardItemWidget item : clipboardItems) {
			panel.add(item);
			item.update();
		}
	}

	public void addItem(ClipboardItemWidget item) {
		item.setClipboard(this);
		clipboardItems.add(item);
		update();
	}

	public void removeItem(ClipboardItemWidget item) {
		clipboardItems.remove(item);
		update();
	}

	private DropController itemDropController = new DropController() {

		public Widget getDropTarget() {
			return panel;
		}

		public void onDrop(DragContext context) {
			Widget widget = context.draggable;
			if (widget instanceof ABlockWidget) {
				ABlockWidget ablockwidget = (ABlockWidget) widget;
				addItem(ablockwidget.createClipboardItem());
			}
		}

		public void onEnter(DragContext context) {
			panel.addStyleName(StyleSheet.DND_DROP_ALLOWED); // TODO
		}

		public void onLeave(DragContext context) {
			System.out.println("leaving...");
			panel.removeStyleName(StyleSheet.DND_DROP_ALLOWED); // TODO
			// Widget widget = context.draggable;
			// if (widget instanceof ABlockWidget) {
			// ABlockWidget ablockwidget = (ABlockWidget) widget;
			// ablockwidget.rebuild();
			// }
		}

		public void onMove(DragContext context) {}

		public void onPreviewDrop(DragContext context) throws VetoDragException {}

	};

}
