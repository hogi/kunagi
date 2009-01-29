package scrum.client.common;

import scrum.client.img.Img;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHTML;

public class ButtonWidget extends Composite {

	private FocusPanel panel;
	private FlowPanel flowPanel;

	public ButtonWidget(Image icon, String text) {
		if (icon == null) icon = Img.bundle.action16().createImage();

		flowPanel = new FlowPanel();
		flowPanel.add(icon);
		flowPanel.add(new InlineHTML("&nbsp;" + text));

		panel = new FocusPanel(flowPanel);
		panel.setStyleName(StyleSheet.ELEMENT_BUTTON_WIDGET);

		initWidget(panel);
	}

	public ButtonWidget(String text) {
		this(null, text);
	}

	public ButtonWidget addClickListener(ClickListener listener) {
		panel.addClickListener(listener);
		return this;
	}
}
