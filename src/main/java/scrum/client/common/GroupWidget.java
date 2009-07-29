package scrum.client.common;

import ilarkesto.gwt.client.AWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class GroupWidget extends AWidget {

	private String title;
	private FlowPanel contentPanel;
	private Widget content;

	public GroupWidget(String title) {
		this.title = title;
	}

	public GroupWidget(String title, Widget content) {
		this(title);
		setContent(content);
	}

	@Override
	protected Widget onInitialization() {
		SimplePanel titlePanel = new SimplePanel();
		titlePanel.setStyleName("GroupWidget-title");
		titlePanel.add(new Label(title));

		contentPanel = new FlowPanel();
		contentPanel.setStyleName("GrouplWidget-content");

		FlowPanel panel = new FlowPanel();
		panel.setStyleName("GrouplWidget");
		panel.add(titlePanel);
		panel.add(contentPanel);

		return panel;
	}

	@Override
	protected void onUpdate() {
		if (content instanceof AWidget) {
			((AWidget) content).update();
		}
	}

	public GroupWidget setContent(Widget content) {
		initialize();
		contentPanel.clear();
		contentPanel.add(content);
		this.content = content;
		return this;
	}

}
