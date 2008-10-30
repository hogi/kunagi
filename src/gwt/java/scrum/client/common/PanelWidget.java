package scrum.client.common;

import scrum.client.service.StyleSheet;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class PanelWidget extends Composite {

	public PanelWidget(String title, Widget content) {
		SimplePanel titlePanel = new SimplePanel();
		titlePanel.setStyleName(StyleSheet.PANEL_WIDGET_TITLE);
		titlePanel.add(new Label(title));

		FlowPanel contentPanel = new FlowPanel();
		contentPanel.setStyleName(StyleSheet.PANEL_WIDGET_CONTENT);
		contentPanel.add(content);

		FlowPanel panel = new FlowPanel();
		panel.setStyleName(StyleSheet.PANEL_WIDGET);
		panel.add(titlePanel);
		panel.add(contentPanel);

		initWidget(panel);
	}

}
