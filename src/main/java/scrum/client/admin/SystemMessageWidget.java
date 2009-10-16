package scrum.client.admin;

import ilarkesto.gwt.client.FloatingFlowPanel;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SystemMessageWidget extends AScrumWidget {

	private Label text = new Label();
	private Label expires = new Label();

	@Override
	protected Widget onInitialization() {
		FloatingFlowPanel panel = new FloatingFlowPanel();

		text.setStyleName("SystemMessageWidget-text");
		panel.add(text);
		panel.add(new Label(" "));
		expires.setStyleName("SystemMessageWidget-expires");
		panel.add(expires);

		return panel;
	}

	@Override
	protected void onUpdate() {
		if (cm.getAuth().isUserLoggedIn()) {
			SystemMessage systemMessage = cm.getSystemMessageManager().getSystemMessage();
			text.setText(systemMessage.isActive() ? systemMessage.getText() : null);
			expires.setText(systemMessage.isActive() ? systemMessage.getExpiresAsString() : null);
		} else {
			text.setText(null);
			expires.setText(null);
		}
	}

}
