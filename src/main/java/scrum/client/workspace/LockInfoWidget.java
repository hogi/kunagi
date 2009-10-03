package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class LockInfoWidget extends AWidget {

	private Label message;
	private Image image;

	@Override
	protected Widget onInitialization() {
		Label spacer = new Label();
		message = new Label();
		image = new Image();
		HorizontalPanel panel = new HorizontalPanel();
		panel.setStyleName("WaitWidget");
		panel.setWidth("100%");
		panel.setSpacing(10);
		panel.add(spacer);
		panel.setCellWidth(spacer, "200px");
		panel.add(image);
		panel.setCellWidth(image, "36px");
		panel.add(message);
		panel.setCellVerticalAlignment(message, HorizontalPanel.ALIGN_MIDDLE);
		return panel;
	}

	public void showBug(String text) {
		initialize();
		image.setUrl("bug.png");
		message.setText(text);
	}

	public void showWait(String text) {
		initialize();
		image.setUrl("spinner.gif");
		message.setText(text);
	}

	@Override
	protected void onUpdate() {}

}
