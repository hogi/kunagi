package scrum.client;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import scrum.client.admin.LoginWidget;
import scrum.client.common.AScrumComponent;
import scrum.client.test.WidgetsTesterWidget;
import scrum.client.workspace.PagePanel;
import scrum.client.workspace.Ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PublicContext extends AScrumComponent {

	private FlowPanel sidebar;
	private SwitchingNavigatorWidget navigator;
	private LoginWidget login;

	@Override
	protected void onInitialization() {
		super.onInitialization();

		login = new LoginWidget();

		navigator = new SwitchingNavigatorWidget(Scope.get().getComponent(Ui.class).getWorkspace().getWorkarea());
		navigator.addItem("Login", login);
		navigator.addItem("Register", createRegisterWidget());
		navigator.addItem("About", new Label(""));
		ApplicationInfo applicationInfo = cm.getApp().getApplicationInfo();
		if (applicationInfo != null && applicationInfo.isDevelopmentStage()) {
			navigator.addItem("Widgets Tests", new WidgetsTesterWidget());
		}

		sidebar = new FlowPanel();
		sidebar.getElement().getStyle().setMarginTop(10, Unit.PX);
		sidebar.getElement().getStyle().setMarginLeft(10, Unit.PX);
		sidebar.add(navigator);
	}

	public void activate() {
		Scope.get().getComponent(Ui.class).show(sidebar, login);
	}

	private Widget createRegisterWidget() {
		PagePanel page = new PagePanel();
		page.addHeader("Free Account Registration");
		page.addSection("Create an account.");
		return page;
	}

}
