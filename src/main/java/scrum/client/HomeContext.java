package scrum.client;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import scrum.client.admin.ProjectSelectorWidget;
import scrum.client.admin.SystemMessageManagerWidget;
import scrum.client.admin.SystemMessageWidget;
import scrum.client.admin.UserConfigWidget;
import scrum.client.admin.UserListWidget;
import scrum.client.common.AScrumComponent;
import scrum.client.workspace.Ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;

public class HomeContext extends AScrumComponent {

	private Ui ui;

	private FlowPanel sidebar;
	private SwitchingNavigatorWidget navigator;
	private ProjectSelectorWidget projectSelector;
	private UserConfigWidget userConfig;
	private UserListWidget userList;
	private SystemMessageManagerWidget messageManager;

	@Override
	protected void onInitialization() {
		super.onInitialization();
		ui = Scope.get().getComponent(Ui.class);

		projectSelector = new ProjectSelectorWidget();
		userConfig = new UserConfigWidget();
		messageManager = new SystemMessageManagerWidget();

		navigator = new SwitchingNavigatorWidget(ui.getWorkspace().getWorkarea());
		navigator.addItem("Projects", projectSelector);
		navigator.addItem("Personal Preferences", userConfig);
		if (getCurrentUser().isAdmin()) {
			userList = new UserListWidget();
			navigator.addItem("User Management", userList);
			navigator.addItem("System Message Management", messageManager);
		}

		sidebar = new FlowPanel();
		sidebar.getElement().getStyle().setMarginTop(10, Unit.PX);
		sidebar.getElement().getStyle().setMarginLeft(10, Unit.PX);
		sidebar.add(new SystemMessageWidget());
		sidebar.add(Gwt.createSpacer(1, 10));
		sidebar.add(navigator);
	}

	public void activate() {
		ui.show(sidebar, projectSelector);
	}

	public UserListWidget getUserList() {
		return userList;
	}

}
