package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import scrum.client.collaboration.ChatWidget;
import scrum.client.common.ScrumUtil;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class SidebarWidget extends AWidget {

	// private ClipboardWidget clipboard;
	private TrashWidget trash;
	private ChatWidget chat;

	@Override
	protected Widget onInitialization() {

		trash = new TrashWidget();
		// clipboard = new ClipboardWidget();

		FlowPanel sidebar = new FlowPanel();
		sidebar.setStyleName("SidebarWidget");
		sidebar.add(ScrumUtil.createEmptyDiv("beforeNavigatorSpacer"));
		sidebar.add(WorkareaWidget.get().getNavigator());
		sidebar.add(trash);
		// sidebar.add(clipboard);
		sidebar.add(getChat());

		return sidebar;
	}

	@Override
	protected void onUpdate() {
		trash.update();
		// clipboard.update();
		chat.update();
	}

	public ChatWidget getChat() {
		if (chat == null) {
			chat = new ChatWidget();
		}
		return chat;
	}

	public static SidebarWidget get() {
		return WorkspaceWidget.get().getSidebar();
	}

}
