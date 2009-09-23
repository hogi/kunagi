package scrum.client.common;

import ilarkesto.gwt.client.AWidget;
import scrum.client.admin.User;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class UserOnBlockWidget extends AWidget {

	private User user;
	private Label label;

	public UserOnBlockWidget(User user) {
		this.user = user;
	}

	@Override
	protected Widget onInitialization() {
		label = new Label();
		label.setStyleName("UserOnBlockWidget");
		label.getElement().getStyle().setProperty("color", user.getProjectConfig().getColor());
		return label;
	}

	@Override
	protected void onUpdate() {
		label.setText(user.getName());
	}

}
