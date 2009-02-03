package scrum.client.workspace;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockListController;
import scrum.client.common.BlockListWidget;
import scrum.client.common.StyleSheet;
import scrum.client.img.Img;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class ClipboardWidget extends Composite {

	private BlockListWidget list;
	private SimplePanel space;
	private HorizontalPanel trash;

	public ClipboardWidget() {
		list = new BlockListWidget(new BlockListController());

		list.setSidebarMode(true);
		list.setStyleName(StyleSheet.ELEMENT_DROP_WIDGET_ITEMS);

		space = new SimplePanel();
		space.setStyleName(StyleSheet.ELEMENT_DROP_WIDGET_SPACE);
		space.setWidget(new Label("drop here"));

		trash = new HorizontalPanel();
		trash.setStyleName(StyleSheet.ELEMENT_DROP_WIDGET_TRASH);
		trash.add(Img.icons().trashIcon32().createImage());
		trash.add(new Label("Trash"));

		DockPanel dock = new DockPanel();
		dock.setStyleName(StyleSheet.ELEMENT_DROP_WIDGET);

		// dock.add(list, DockPanel.NORTH);
		// dock.setCellHeight(list, "1%");
		dock.add(list, DockPanel.CENTER);

		// dock.add(space, DockPanel.CENTER);

		dock.add(trash, DockPanel.SOUTH);
		// dock.setCellHeight(trash, "1%");

		ScrumGwtApplication.get().getDragController().registerDropController(itemDropController);
		ScrumGwtApplication.get().getDragController().registerDropController(trashDropController);

		initWidget(dock);

		// test dummy
		// addItem(new ImpedimentWidget(Dummy.moon.getImpediments().get(0)));
		// addItem(new ImpedimentWidget(Dummy.moon.getImpediments().get(1)));
	}

	public void addItem(ABlockWidget item) {
		list.addBlock(item);
	}

	private DropController itemDropController = new DropController() {

		public Widget getDropTarget() {
			return list;
		}

		public void onDrop(DragContext context) {
			Widget widget = context.draggable;
			if (widget instanceof ABlockWidget) {
				ABlockWidget ablockwidget = (ABlockWidget) widget;
				if (ablockwidget.isInClipboard() == false) list.addBlock(ablockwidget);
				// ablockwidget.rebuild();
			}
		}

		public void onEnter(DragContext context) {
			list.addStyleName(StyleSheet.TRASH_ON_ENTER); // TODO
		}

		public void onLeave(DragContext context) {
			System.out.println("leaving...");
			list.removeStyleName(StyleSheet.TRASH_ON_ENTER); // TODO
			// Widget widget = context.draggable;
			// if (widget instanceof ABlockWidget) {
			// ABlockWidget ablockwidget = (ABlockWidget) widget;
			// ablockwidget.rebuild();
			// }
		}

		public void onMove(DragContext context) {}

		public void onPreviewDrop(DragContext context) throws VetoDragException {}

	};

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
			trash.addStyleName(StyleSheet.TRASH_ON_ENTER);
		}

		public void onLeave(DragContext context) {
			trash.removeStyleName(StyleSheet.TRASH_ON_ENTER);
		}

		public void onMove(DragContext context) {}

		public void onPreviewDrop(DragContext context) throws VetoDragException {
		// TODO: check if item can be removed
		// TODO: ask if item should be removed
		}

	};

}
