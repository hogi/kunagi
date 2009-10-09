package scrum.client;

import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import scrum.client.admin.LoginWidget;
import scrum.client.common.AScrumComponent;
import scrum.client.test.WidgetsTesterWidget;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class PublicContext extends AScrumComponent {

	private SwitchingNavigatorWidget navigator;
	private LoginWidget login;

	@Override
	protected void onInitialization() {
		super.onInitialization();

		navigator = new SwitchingNavigatorWidget(ComponentManager.get().getUi().getWorkspace().getWorkarea());
		navigator.addItem((Image) null, "Login", login);
		navigator.addItem((Image) null, "Register", new Label(""));
		navigator.addItem((Image) null, "About", new Label(""));
		ApplicationInfo applicationInfo = ScrumGwtApplication.get().getApplicationInfo();
		if (applicationInfo != null && applicationInfo.isDevelopmentStage()) {
			navigator.addItem((Image) null, "Widgets Tests", new WidgetsTesterWidget());
		}

		login = new LoginWidget();
	}

	public void activate() {
		cm.getUi().show(navigator, login);
	}

}
