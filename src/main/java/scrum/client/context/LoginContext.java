package scrum.client.context;


import scrum.client.admin.LoginWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class LoginContext extends AContext {

	private Widget sidebar = new Label("");
	private LoginWidget workarea = new LoginWidget();

	@Override
	public Widget getSidebarWidget() {
		return sidebar;
	}

	@Override
	public Widget getWorkareaWidget() {
		return workarea;
	}

}
