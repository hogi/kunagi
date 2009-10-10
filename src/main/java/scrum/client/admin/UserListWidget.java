package scrum.client.admin;

import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class UserListWidget extends AScrumWidget {

	public BlockListWidget<User> list;
	private ToolbarWidget toolbar;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<User>(UserBlock.FACTORY);
		list.setAutoSorter(User.NAME_COMPARATOR);

		toolbar = new ToolbarWidget();
		toolbar.addButton(new CreateUserAction());

		PagePanel page = new PagePanel();
		page.addHeader("Users");
		page.addSection(toolbar);
		page.addSection(list);
		return page;
	}

	@Override
	protected void onUpdate() {
		toolbar.update();
		list.setObjects(cm.getDao().getUsers());
	}

	public void showUser(User user) {
		update();
		list.extendObject(user);
	}

}
