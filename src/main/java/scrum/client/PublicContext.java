package scrum.client;

import ilarkesto.gwt.client.AComponent;
import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import scrum.client.admin.LoginWidget;
import scrum.client.test.WidgetsTesterWidget;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class PublicContext extends AComponent {

	// --- dependencies ---

	private Ui ui;

	public void setUi(Ui ui) {
		this.ui = ui;
	}

	// --- ---

	private SwitchingNavigatorWidget navigator;
	private LoginWidget login;

	@Override
	protected void onInitialization() {
		super.onInitialization();

		navigator = new SwitchingNavigatorWidget(Components.get().getUi().getWorkspace().getWorkarea());
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
		ui.show(navigator, login);
	}

}
