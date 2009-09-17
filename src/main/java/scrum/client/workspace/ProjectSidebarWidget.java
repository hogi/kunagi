package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import scrum.client.collaboration.ChatWidget;
import scrum.client.common.ScrumUtil;
import scrum.client.communication.UsersStatusWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class ProjectSidebarWidget extends AWidget {

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
		sidebar.add(ScrumUtil.createEmptyDiv("beforeNavigatorSpacer"));
		sidebar.add(WorkareaWidget.get().getNavigator());
		sidebar.add(trash);
		// sidebar.add(clipboard);
		sidebar.add(getUsersStatus());
		sidebar.add(getChat());

		return sidebar;
	}

	@Override
	protected void onUpdate() {
		trash.update();
		// clipboard.update();
		chat.update();
		usersStatus.update();
	}

	public ChatWidget getChat() {
		if (chat == null) {
			chat = new ChatWidget();
		}
		return chat;
	}

	public UsersStatusWidget getUsersStatus() {
		if (usersStatus == null) {
			usersStatus = new UsersStatusWidget();
		}
		return usersStatus;
	}

	public static ProjectSidebarWidget get() {
		return Ui.get().getProjectSidebar();
	}

}
