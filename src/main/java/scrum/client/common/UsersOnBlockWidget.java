package scrum.client.common;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;

import java.util.Set;

import scrum.client.admin.User;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class UsersOnBlockWidget extends AScrumWidget {

	private AScrumGwtEntity entity;
	private SimplePanel wrapper;

	public UsersOnBlockWidget(AScrumGwtEntity entity) {
		super();
		this.entity = entity;
	}

	@Override
	protected Widget onInitialization() {
		wrapper = new SimplePanel();
		wrapper.setStyleName("UsersOnBlockWidget");
		return wrapper;
	}

	@Override
	protected void onUpdate() {
		TableBuilder tb = new TableBuilder();
		tb.setWidth(null);
		Set<User> users = getCurrentProject().getUsersSelecting(entity);
		boolean first = true;
		for (User user : users) {
			if (user == getCurrentUser()) continue;
			if (first) {
				first = false;
			} else {
				tb.add(new Label(","));
				tb.add(Gwt.createSpacer(3, 1));
			}

			Label label = Gwt.createInline(user.getName());
			label.getElement().getStyle().setProperty("color", user.getProjectConfig().getColor());
			tb.add(label);
		}
		wrapper.setWidget(tb.createTable());
	}

}
