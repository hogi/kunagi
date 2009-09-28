package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class WaitWidget extends AWidget {

	private Label message;

	@Override
	protected Widget onInitialization() {
		message = new Label();
		HorizontalPanel panel = new HorizontalPanel();
		panel.setStyleName("WaitWidget");
		panel.setSpacing(10);
		panel.add(new Image("spinner.gif"));
		panel.add(message);
		panel.setCellVerticalAlignment(message, HorizontalPanel.ALIGN_MIDDLE);
		return panel;
	}

	public void setMessage(String text) {
		initialize();
		message.setText(text);
	}

	@Override
	protected void onUpdate() {}

}
