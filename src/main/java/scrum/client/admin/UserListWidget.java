package scrum.client.admin;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;
import scrum.client.workspace.StartPageWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class UserListWidget extends AWidget {

	public BlockListWidget<User> list;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<User>(UserBlock.FACTORY);
		list.setAutoSorter(User.NAME_COMPARATOR);

		ToolbarWidget toolbar = new ToolbarWidget(true);
		toolbar.addButton(new CreateUserAction(this));

		FlowPanel panel = new FlowPanel();
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);

		return new GroupWidget("Users", panel);
	}

	@Override
	protected void onUpdate() {
		list.setObjects(ScrumGwtApplication.get().getDao().getUsers());
	}

	public void showUser(User user) {
		update();
		list.extendObject(user);
	}

	public static UserListWidget get() {
		return StartPageWidget.get().getUserList();
	}

}
