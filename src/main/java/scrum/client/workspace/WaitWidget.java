package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.GroupWidget;
import scrum.client.common.ScrumUtil;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class WaitWidget extends AWidget {

	private Label message;

	@Override
	protected Widget onInitialization() {
		message = new Label();
		return ScrumUtil.createDiv("WaitWidget", new GroupWidget("Please wait...", message));
	}

	public void setMessage(String text) {
		initialize();
		message.setText(text);
	}

	@Override
	protected void onUpdate() {}

}
