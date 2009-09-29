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
		Label spacer = new Label();
		message = new Label();
		Image spinner = new Image("spinner.gif");
		HorizontalPanel panel = new HorizontalPanel();
		panel.setStyleName("WaitWidget");
		panel.setWidth("100%");
		panel.setSpacing(10);
		panel.add(spacer);
		panel.setCellWidth(spacer, "200px");
		panel.add(spinner);
		panel.setCellWidth(spinner, "36px");
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
