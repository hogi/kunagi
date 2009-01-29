package scrum.client.common;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;

public class ToolbarWidget extends Composite {

	private FlowPanel panel;

	public ToolbarWidget() {
		panel = new FlowPanel();
		panel.setStyleName(StyleSheet.ELEMENT_TOOLBAR_WIDGET);

		initWidget(panel);
	}

	public ButtonWidget add(ButtonWidget button) {
		panel.add(button);
		return button;
	}

	public ButtonWidget addButton(String text) {
		return add(new ButtonWidget(text));
	}

	public ButtonWidget addButton(Image icon, String text) {
		return add(new ButtonWidget(icon, text));
	}

}
