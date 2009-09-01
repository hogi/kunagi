package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.admin.ProjectSelectorWidget;
import scrum.client.admin.UserListWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class StartPageWidget extends AWidget {

	private ProjectSelectorWidget projectSelector;
	private UserListWidget userList;

	@Override
	protected Widget onInitialization() {
		FlowPanel panel = new FlowPanel();
		panel.add(getProjectSelector());
		if (ScrumGwtApplication.get().getUser().isAdmin()) {
			panel.add(getUserList());
		}
		return panel;
	}

	@Override
	protected void onUpdate() {
		if (projectSelector != null) projectSelector.update();
		if (userList != null) userList.update();
	}

	public ProjectSelectorWidget getProjectSelector() {
		if (projectSelector == null) projectSelector = new ProjectSelectorWidget();
		return projectSelector;
	}

	public UserListWidget getUserList() {
		if (userList == null) userList = new UserListWidget();
		return userList;
	}

}
