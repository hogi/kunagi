package scrum.client.admin;

import ilarkesto.gwt.client.ButtonWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class UserListWidget extends AScrumWidget {

	public BlockListWidget<User> list;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<User>(UserBlock.FACTORY);
		list.setAutoSorter(User.NAME_COMPARATOR);

		PagePanel page = new PagePanel();
		page.addHeader("Users", new ButtonWidget(new CreateUserAction()));
		page.addSection(list);
		return page;
	}

	@Override
	protected void onUpdate() {
		list.setObjects(getDao().getUsers());
		super.onUpdate();
	}

	public void showUser(User user) {
		update();
		list.extendObject(user);
	}

}
