package scrum.client.context;

import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import scrum.client.admin.LoginWidget;
import scrum.client.test.WidgetsTesterWidget;
import scrum.client.workspace.Ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PublicContext extends AContext {

	private SwitchingNavigatorWidget navigator;
	private LoginWidget login = new LoginWidget();

	@Override
	public Widget getSidebarWidget() {
		navigator = new SwitchingNavigatorWidget(Ui.get().getWorkarea());
		navigator.addItem((Image) null, "Login", login);
		navigator.addItem((Image) null, "Register", new Label(""));
		navigator.addItem((Image) null, "About", new Label(""));
		if (GWT.isClient()) navigator.addItem((Image) null, "Widgets Tests", new WidgetsTesterWidget());
		return navigator;
	}

	@Override
	public Widget getWorkareaWidget() {
		return login;
	}

}
