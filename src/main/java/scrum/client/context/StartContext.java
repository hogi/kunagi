package scrum.client.context;

import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.admin.ProjectSelectorWidget;
import scrum.client.admin.UserListWidget;
import scrum.client.workspace.Ui;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class StartContext extends AContext {

	private static StartContext singleton;

	private SwitchingNavigatorWidget navigator;
	private ProjectSelectorWidget projectSelector;
	private UserListWidget userList;

	public StartContext() {
		singleton = this;
	}

	@Override
	public Widget getSidebarWidget() {
		projectSelector = new ProjectSelectorWidget();
		userList = new UserListWidget();

		navigator = new SwitchingNavigatorWidget(Ui.get().getWorkarea());
		navigator.addItem((Image) null, "Projects", projectSelector);
		if (ScrumGwtApplication.get().getUser().isAdmin())
			navigator.addItem((Image) null, "User Management", userList);

		return navigator;
	}

	@Override
	public Widget getWorkareaWidget() {
		return projectSelector;
	}

	public UserListWidget getUserList() {
		return userList;
	}

	public static StartContext get() {
		assert singleton != null;
		return singleton;
	}

	public static void destroy() {
		singleton = null;
	}

}
