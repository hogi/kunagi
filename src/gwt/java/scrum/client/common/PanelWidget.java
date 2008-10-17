package scrum.client.common;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class PanelWidget extends Composite {

	public PanelWidget(String title, Widget content) {
		SimplePanel titlePanel = new SimplePanel();
		titlePanel.setStyleName("PanelWidget-title");
		titlePanel.add(new Label(title));

		FlowPanel contentPanel = new FlowPanel();
		contentPanel.setStyleName("PanelWidget-content");
		contentPanel.add(content);

		FlowPanel panel = new FlowPanel();
		panel.setStyleName("PanelWidget");
		panel.add(titlePanel);
		panel.add(contentPanel);

		initWidget(panel);
	}

}
