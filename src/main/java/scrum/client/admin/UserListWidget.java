package scrum.client.admin;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.ClickListener;
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

		toolbar.addButton(Img.bundle.new16().createImage(), "Create new user").addClickListener(
			new CreateClickListener());

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

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
			User user = new User();
			ScrumGwtApplication.get().getDao().createUser(user);
			list.addObject(user, true);
		}
	}

}
