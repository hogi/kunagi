package scrum.client.communication;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.GwtLogger;
import scrum.client.ScrumGwtApplication;
import scrum.client.admin.User;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class UserStatusWidget extends AWidget {

	private Label label;
	private User user;

	public UserStatusWidget(User user) {
		this.user = user;
	}

	@Override
	protected Widget onInitialization() {
		label = new Label(user.getName());
		label.setStyleName("UserStatusWidget");
		return label;
	}

	@Override
	protected void onUpdate() {
		String color = ScrumGwtApplication.get().getProject().getUserConfig(user).getColor();
		GwtLogger.DEBUG("COLOR: ----------------- > ", color);
		label.getElement().getStyle().setProperty("color", color);
		if (ScrumGwtApplication.get().getProject().isOnline(user)) {
			label.addStyleName("UserStatusWidget-online");
		} else {
			label.removeStyleName("UserStatusWidget-online");
		}
	}
}
