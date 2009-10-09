package scrum.client;

import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import scrum.client.admin.ProjectSelectorWidget;
import scrum.client.admin.UserConfigWidget;
import scrum.client.admin.UserListWidget;
import scrum.client.common.AScrumComponent;
import scrum.client.context.UiComponent;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class HomeContext extends AScrumComponent implements UiComponent {

	private SwitchingNavigatorWidget navigator;
	private ProjectSelectorWidget projectSelector;
	private UserConfigWidget userConfig;
	private UserListWidget userList;

	public Widget getSidebarWidget() {
		projectSelector = new ProjectSelectorWidget();
		userConfig = new UserConfigWidget();

		navigator = new SwitchingNavigatorWidget(ComponentManager.get().getUi().getWorkspace().getWorkarea());
		navigator.addItem((Image) null, "Projects", projectSelector);
		navigator.addItem((Image) null, "Personal Preferences", userConfig);
		if (ScrumGwtApplication.get().getUser().isAdmin()) {
			userList = new UserListWidget();
			navigator.addItem((Image) null, "User Management", userList);
		}

		return navigator;
	}

	public Widget getWorkareaWidget() {
		return projectSelector;
	}

	public UserListWidget getUserList() {
		return userList;
	}

}
