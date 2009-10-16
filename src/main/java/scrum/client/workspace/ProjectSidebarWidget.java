package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.FloatingFlowPanel;
import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import scrum.client.ComponentManager;
import scrum.client.collaboration.ChatWidget;
import scrum.client.communication.UsersStatusWidget;

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

		FloatingFlowPanel usersTrashSplitter = new FloatingFlowPanel();
		usersTrashSplitter.add(getUsersStatus());
		usersTrashSplitter.add(trash, true);

		PagePanel page = new PagePanel();
		page.addSection(getNavigator());
		page.addSection(getChat());
		page.addSection(usersTrashSplitter);
		return page;
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
		if (navigator == null)
			navigator = new SwitchingNavigatorWidget(ComponentManager.get().getUi().getWorkspace().getWorkarea());
		return navigator;
	}

	public static ProjectSidebarWidget get() {
		return ComponentManager.get().getProjectContext().getSidebar();
	}

}
