package scrum.client.workspace;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.FloatingFlowPanel;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import scrum.client.admin.SystemMessageWidget;
import scrum.client.collaboration.ChatWidget;
import scrum.client.collaboration.UsersStatusWidget;
import scrum.client.common.AScrumWidget;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class ProjectSidebarWidget extends AScrumWidget {

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

		FlowPanel sidebar = new FlowPanel();
		sidebar.getElement().getStyle().setMarginTop(10, Unit.PX);
		sidebar.getElement().getStyle().setMarginLeft(10, Unit.PX);
		sidebar.add(new SystemMessageWidget());
		sidebar.add(Gwt.createSpacer(1, 10));
		sidebar.add(getNavigator());
		sidebar.add(Gwt.createSpacer(1, 10));
		sidebar.add(getChat());
		sidebar.add(Gwt.createSpacer(1, 10));
		sidebar.add(usersTrashSplitter);
		sidebar.add(Gwt.createSpacer(1, 10));
		return sidebar;
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
			navigator = new SwitchingNavigatorWidget(Scope.get().getComponent(Ui.class).getWorkspace().getWorkarea());
		return navigator;
	}

}
