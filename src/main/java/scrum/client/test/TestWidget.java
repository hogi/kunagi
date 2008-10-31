package scrum.client.test;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.MouseListenerCollection;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SourcesMouseEvents;
import com.google.gwt.user.client.ui.Widget;

public class TestWidget extends Composite {

	public TestWidget() {
		AbsolutePanel dndPanel = new AbsolutePanel();
		dndPanel.setPixelSize(400, 400);

		final Composite drag = new DragComposite();
		final Label drop = new Label("drop");

		PickupDragController dragController = new PickupDragController(RootPanel.get(), true);
		dragController.setBehaviorDragProxy(true);
		dragController.setBehaviorConstrainedToBoundaryPanel(false);
		dragController.setBehaviorMultipleSelection(true);
		DropController dropController = new DropController() {

			public Widget getDropTarget() {
				return drop;
			}

			public void onDrop(DragContext context) {
				drop.setText(drop.getText() + ".");
			}

			public void onEnter(DragContext context) {}

			public void onLeave(DragContext context) {}

			public void onMove(DragContext context) {}

			public void onPreviewDrop(DragContext context) throws VetoDragException {}
		};

		dragController.registerDropController(dropController);
		dragController.makeDraggable(drag);

		dndPanel.add(drag);
		dndPanel.add(drop);
		initWidget(dndPanel);
	}

	private class DragComposite extends Composite implements SourcesMouseEvents {

		MouseListenerCollection mouseListeners;

		public DragComposite() {
			initWidget(new Label("ownDragWidget"));
		}

		public void addMouseListener(MouseListener listener) {
			if (mouseListeners == null) {
				mouseListeners = new MouseListenerCollection();
				sinkEvents(Event.MOUSEEVENTS);
			}
			mouseListeners.add(listener);
		}

		public void removeMouseListener(MouseListener listener) {
			if (mouseListeners != null) {
				mouseListeners.remove(listener);
			}
		}

		@Override
		public void onBrowserEvent(Event event) {
			switch (DOM.eventGetType(event)) {
				case Event.ONMOUSEDOWN:
				case Event.ONMOUSEUP:
				case Event.ONMOUSEMOVE:
				case Event.ONMOUSEOVER:
				case Event.ONMOUSEOUT:
					mouseListeners.fireMouseEvent(this, event);
					break;
			}
		}

	}
}
