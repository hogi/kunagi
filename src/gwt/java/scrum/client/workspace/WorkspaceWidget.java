package scrum.client.workspace;

import scrum.client.impediments.ImpedimentListWidget;
import scrum.client.project.BacklogItemListWidget;
import scrum.client.test.TestWidget;
import scrum.client.view.PortalView;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class WorkspaceWidget extends Composite {

	public static BacklogItemListWidget backlog;
	public static ImpedimentListWidget impediments;
	// public static SprintListWidget sprints;
	public static PortalView portal;

	private static SimplePanel rootPanel;
	private static DockPanel workspacePanel;
	private static SimplePanel workareaPanel;

	public WorkspaceWidget() {
		// initialize widgets
		impediments = new ImpedimentListWidget();
		backlog = new BacklogItemListWidget();
		// sprints = new SprintListWidget();

		// create workspace
		workspacePanel = new DockPanel();
		workspacePanel.setStyleName("WorkspaceWidget-workspace");
		workspacePanel.setWidth("100%");

		// sidebar
		Widget sidebar = createSidebar();
		workspacePanel.add(sidebar, DockPanel.WEST);
		workspacePanel.setCellHeight(sidebar, "100%");

		// workarea
		workareaPanel = new SimplePanel();
		workareaPanel.setStyleName("WorkspaceWidget-workarea");
		workspacePanel.add(workareaPanel, DockPanel.CENTER);
		workspacePanel.setStyleName("WorkspaceWidget");
		workspacePanel.setCellWidth(workareaPanel, "99%");

		// root panel
		rootPanel = new SimplePanel();
		rootPanel.setWidget(workspacePanel);

		initWidget(rootPanel);
	}

	public static void lock(String message) {
		rootPanel.setWidget(new Label(message));
	}

	public static void unlock() {
		rootPanel.setWidget(workspacePanel);
	}

	public static void showImpediments() {
		impediments.update();
		setWorkarea(impediments);
	}

	public static void showBacklog() {
		backlog.update();
		setWorkarea(backlog);
	}

	public static void showTest() {
		setWorkarea(new TestWidget());
	}

	public static void setWorkarea(Widget widget) {
		workareaPanel.setWidget(widget);
	}

	private Widget createSidebar() {
		VerticalPanel sidebar = new VerticalPanel();
		sidebar.setStyleName("WorkspaceWidget-sidebar");
		sidebar.setWidth("300px");
		sidebar.setHeight("100%");
		sidebar.add(new SidebarWidget());
		return sidebar;
	}

}
