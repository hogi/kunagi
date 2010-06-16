package scrum.client.workspace;

import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import scrum.client.ApplicationInfo;
import scrum.client.admin.LoginWidget;
import scrum.client.test.WidgetsTesterWidget;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PublicWorkspaceWidgets extends GPublicWorkspaceWidgets {

	private FlowPanel sidebar;
	private SwitchingNavigatorWidget navigator;
	private LoginWidget login;

	@Override
	public void initialize() {
		login = new LoginWidget();

		navigator = new SwitchingNavigatorWidget(ui.getWorkspace().getWorkarea());
		navigator.addItem("Login", login);
		navigator.addItem("Register", createRegisterWidget());
		navigator.addItem("About", new Label(""));
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

	private Widget createRegisterWidget() {
		PagePanel page = new PagePanel();
		page.addHeader("Free Account Registration");
		page.addSection("Create an account.");
		return page;
	}

}
