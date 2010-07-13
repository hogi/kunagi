package scrum.client.workspace;

import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import scrum.client.ApplicationInfo;
import scrum.client.admin.LoginWidget;
import scrum.client.admin.RegisterWidget;
import scrum.client.test.WidgetsTesterWidget;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;

public class PublicWorkspaceWidgets extends GPublicWorkspaceWidgets {

	private FlowPanel sidebar;
	private SwitchingNavigatorWidget navigator;
	private LoginWidget login;
	private AboutWidget about;

	@Override
	public void initialize() {
		login = new LoginWidget();
		about = new AboutWidget();

		navigator = new SwitchingNavigatorWidget(ui.getWorkspace().getWorkarea());
		navigator.addItem("Login", login);
		navigator.addItem("Register", new RegisterWidget());
		navigator.addItem("About", about);
		ApplicationInfo applicationInfo = app.getApplicationInfo();
		if (applicationInfo != null && applicationInfo.isDevelopmentStage()) {
			navigator.addItem("Widgets Tests", new WidgetsTesterWidget());
		}

		sidebar = new FlowPanel();
		sidebar.getElement().getStyle().setMarginTop(10, Unit.PX);
		sidebar.getElement().getStyle().setMarginLeft(10, Unit.PX);
		sidebar.add(navigator);
	}

	public void activate() {
		ui.show(sidebar, login);
	}
}
