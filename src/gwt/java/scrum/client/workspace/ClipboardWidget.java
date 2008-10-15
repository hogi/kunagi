package scrum.client.workspace;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.img.Img;
import scrum.client.impediments.ImpedimentWidget;
import scrum.client.service.Dummy;

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

	private BlockListWidget items;
	private SimplePanel space;
	private HorizontalPanel trash;

	public ClipboardWidget() {
		ScrumGwtApplication.getDragController().registerDropController(trashDropController);

		items = new BlockListWidget(true);
		items.addStyleName("DropWidget-items");

		space = new SimplePanel();
		space.setStyleName("DropWidget-space");
		// space.setHeight("50px");
		space.setWidth("100%");
		space.setWidget(new Label("drop here"));

		trash = new HorizontalPanel();
		trash.setStyleName("DropWidget-trash");
		trash.setWidth("100%");
		trash.add(Img.icons().trashIcon32().createImage());
		trash.add(new Label("Trash"));

		DockPanel dock = new DockPanel();
		dock.setWidth("100%");
		dock.setHeight("300px");
		dock.setStyleName("DropWidget");

		dock.add(items, DockPanel.NORTH);
		dock.setCellHeight(items, "1%");

		dock.add(space, DockPanel.CENTER);

		dock.add(trash, DockPanel.SOUTH);
		dock.setCellHeight(trash, "1%");

		initWidget(dock);

		// test dummy
		addItem(new ImpedimentWidget(Dummy.moon.getImpediments().get(0)));
		addItem(new ImpedimentWidget(Dummy.moon.getImpediments().get(1)));
	}

	public void addItem(ABlockWidget item) {
		items.addBlock(item);
	}

	private DropController trashDropController = new DropController() {

		public Widget getDropTarget() {
			return trash;
		}

		public void onDrop(DragContext context) {
			Widget widget = context.draggable;
			widget.addStyleName("deleted");
		}

		public void onEnter(DragContext context) {
			trash.addStyleName("Trash-onEnter");
		}

		public void onLeave(DragContext context) {
			trash.removeStyleName("Trash-onEnter");
		}

		public void onMove(DragContext context) {}

		public void onPreviewDrop(DragContext context) throws VetoDragException {
		// TODO: check if item can be removed
		// TODO: ask if item should be removed
		}

	};

}
