package scrum.client.test;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.drop.SimpleDropController;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SourcesMouseEvents;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TestWidget extends Composite {

	private PickupDragController dragController = new PickupDragController(RootPanel.get(), false);

	public TestWidget() {
		VerticalPanel panel = new VerticalPanel();
		panel.setStyleName("TestWidget");
		panel.setSpacing(30);

		DragWidget drag = new DragWidget();
		DropWidget drop = new DropWidget();
		dragController.makeDraggable(drag);
		TestDropController dropController = new TestDropController(drop);
		dragController.registerDropController(dropController);

		panel.add(drag);
		panel.add(drop);
		initWidget(panel);
	}

	class DragWidget extends Composite implements SourcesMouseEvents {

		Label label = new Label("dragMe");

		public DragWidget() {
			initWidget(label);
		}

		public void addMouseListener(MouseListener listener) {
			label.addMouseListener(listener);
		}

		public void removeMouseListener(MouseListener listener) {
			label.removeMouseListener(listener);
		}

	}

	class DropWidget extends Composite {

		int dropped = 0;
		Label label = new Label("Drop here! (dropped 0)");

		public DropWidget() {
			initWidget(label);
		}

		public void increment() {
			label.setText("Drop here! (dropped " + (++dropped) + ")");
		}
	}

	public class TestDropController extends SimpleDropController {

		private DropWidget widget;

		public TestDropController(Widget dropTarget) {
			super(dropTarget);
			widget = (DropWidget) dropTarget;
		}

		@Override
		public void onDrop(DragContext context) {
			super.onDrop(context);
			widget.increment();
		}

		@Override
		public void onEnter(DragContext context) {
			super.onEnter(context);
		}

		@Override
		public void onLeave(DragContext context) {
			super.onLeave(context);
		}
	}
}
