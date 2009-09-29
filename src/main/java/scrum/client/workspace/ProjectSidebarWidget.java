package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import scrum.client.collaboration.ChatWidget;
import scrum.client.communication.UsersStatusWidget;
import scrum.client.context.ProjectContext;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class ProjectSidebarWidget extends AWidget {

	private SwitchingNavigatorWidget navigator;
	// private ClipboardWidget clipboard;
	private TrashWidget trash;
	private ChatWidget chat;
	private UsersStatusWidget usersStatus;

	@Override
	protected Widget onInitialization() {
		setHeight100();
		trash = new TrashWidget();
		// clipboard = new ClipboardWidget();

		FlowPanel sidebar = new FlowPanel();
		sidebar.setStyleName("ProjectSidebarWidget");
		sidebar.add(getNavigator());
		sidebar.add(trash);
		// sidebar.add(clipboard);
		sidebar.add(getUsersStatus());
		sidebar.add(getChat());

		return sidebar;
	}

	@Override
	protected void onUpdate() {
		navigator.update();
		trash.update();
		// clipboard.update();
		chat.update();
		usersStatus.update();
	}

	public ChatWidget getChat() {
		if (chat == null) chat = new ChatWidget();
		return chat;
	}

	public UsersStatusWidget getUsersStatus() {
		if (usersStatus == null) usersStatus = new UsersStatusWidget();
		return usersStatus;
	}

	public SwitchingNavigatorWidget getNavigator() {
		if (navigator == null) navigator = new SwitchingNavigatorWidget(Ui.get().getWorkarea());
		return navigator;
	}

	public static ProjectSidebarWidget get() {
		return ProjectContext.get().getSidebar();
	}

}
