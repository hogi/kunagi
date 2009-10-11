package scrum.client;

import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import scrum.client.admin.LoginWidget;
import scrum.client.common.AScrumComponent;
import scrum.client.test.WidgetsTesterWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PublicContext extends AScrumComponent {

	private PagePanel sidebar;
	private SwitchingNavigatorWidget navigator;
	private LoginWidget login;

	@Override
	protected void onInitialization() {
		super.onInitialization();

		login = new LoginWidget();

		navigator = new SwitchingNavigatorWidget(ComponentManager.get().getUi().getWorkspace().getWorkarea());
		navigator.addItem((Image) null, "Login", login);
		navigator.addItem((Image) null, "Register", createRegisterWidget());
		navigator.addItem((Image) null, "About", new Label(""));
		ApplicationInfo applicationInfo = ScrumGwtApplication.get().getApplicationInfo();
		if (applicationInfo != null && applicationInfo.isDevelopmentStage()) {
			navigator.addItem((Image) null, "Widgets Tests", new WidgetsTesterWidget());
		}

		sidebar = new PagePanel();
		sidebar.addSection(navigator);
	}

	public void activate() {
		cm.getUi().show(sidebar, login);
	}

	private Widget createRegisterWidget() {
		PagePanel page = new PagePanel();
		page.addHeader("Free Account Registration");
		page
				.addSection("Create a free Scrum42 account. Create a free Scrum42 account. Create a free Scrum42 account. Create a free Scrum42 account. Create a free Scrum42 account. Create a free Scrum42 account. Create a free Scrum42 account. Create a free Scrum42 account. Create a free Scrum42 account. Create a free Scrum42 account. ");
		return page;
	}

}
