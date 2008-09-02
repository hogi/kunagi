package scrum.client.test;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TestWidget extends Composite {

	public TestWidget() {
		VerticalPanel panel = new VerticalPanel();
		panel.setStyleName("TestWidget");
		panel.add(new Button("test"));
		initWidget(panel);
	}
}
