package scrum.client.context;

import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import scrum.client.ApplicationInfo;
import scrum.client.Components;
import scrum.client.ScrumGwtApplication;
import scrum.client.admin.LoginWidget;
import scrum.client.test.WidgetsTesterWidget;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PublicContext implements UiComponent {

	private SwitchingNavigatorWidget navigator;
	private LoginWidget login = new LoginWidget();

	public Widget getSidebarWidget() {
		navigator = new SwitchingNavigatorWidget(Components.get().getUi().getWorkspace().getWorkarea());
		navigator.addItem((Image) null, "Login", login);
		navigator.addItem((Image) null, "Register", new Label(""));
		navigator.addItem((Image) null, "About", new Label(""));
		ApplicationInfo applicationInfo = ScrumGwtApplication.get().getApplicationInfo();
		if (applicationInfo != null && applicationInfo.isDevelopmentStage()) {
			navigator.addItem((Image) null, "Widgets Tests", new WidgetsTesterWidget());
		}
		return navigator;
	}

	public Widget getWorkareaWidget() {
		return login;
	}

}
