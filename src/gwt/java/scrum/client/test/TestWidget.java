package scrum.client.test;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.SourcesMouseEvents;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TestWidget extends Composite {

	public TestWidget() {
		VerticalPanel panel = new VerticalPanel();
		panel.setStyleName("TestWidget");
		DragWidget d = new DragWidget();
		panel.add(d);
		initWidget(panel);
	}

	class DragWidget extends Composite implements SourcesMouseEvents {

		public void addMouseListener(MouseListener listener) {}

		public void removeMouseListener(MouseListener listener) {}

	}
}
