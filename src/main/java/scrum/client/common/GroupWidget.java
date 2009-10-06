package scrum.client.common;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.Gwt;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class GroupWidget extends AWidget {

	private String title;
	private FlowPanel contentPanel;
	private Widget content;
	private boolean contentChanged;

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
		if (contentChanged) {
			contentPanel.clear();
			contentPanel.add(content);
			contentChanged = false;
		}
		Gwt.update(content);
	}

	public GroupWidget setContent(Widget content) {
		this.content = content;
		contentChanged = true;
		update();
		return this;
	}

	@Override
	public String toString() {
		return "GroupWidget(" + title + ")";
	}
}
