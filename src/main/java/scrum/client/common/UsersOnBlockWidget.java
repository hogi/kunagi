package scrum.client.common;

import ilarkesto.gwt.client.Gwt;

import java.util.Set;

import scrum.client.admin.User;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class UsersOnBlockWidget extends AScrumWidget {

	private AScrumGwtEntity entity;
	private FlowPanel panel;

	public UsersOnBlockWidget(AScrumGwtEntity entity) {
		super();
		this.entity = entity;
	}

	@Override
	protected Widget onInitialization() {
		panel = new FlowPanel();
		panel.setStyleName("UsersOnBlockWidget");
		return panel;
	}

	@Override
	protected void onUpdate() {
		panel.clear();
		Set<User> users = getCurrentProject().getUsersSelecting(entity);
		boolean first = true;
		for (User user : users) {
			if (user == getCurrentUser()) continue;
			if (first) {
				first = false;
			} else {
				panel.add(new Label(", "));
			}

			Label label = Gwt.createInline(user.getName());
			label.getElement().getStyle().setProperty("color", user.getProjectConfig().getColor());
			panel.add(label);
		}
	}

}
