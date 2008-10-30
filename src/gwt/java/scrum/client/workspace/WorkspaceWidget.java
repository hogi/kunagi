package scrum.client.workspace;

import java.util.List;

import scrum.client.common.ListProvider;
import scrum.client.impediments.ImpedimentListWidget;
import scrum.client.project.BacklogItem;
import scrum.client.project.BacklogItemListWidget;
import scrum.client.service.ScrumClient;
import scrum.client.service.StyleSheet;
import scrum.client.test.TestWidget;
import scrum.client.view.CurrentSprintView;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class WorkspaceWidget extends Composite {

	public static BacklogItemListWidget backlog;
	public static CurrentSprintView sprint;
	public static ImpedimentListWidget impediments;

	private static SimplePanel rootPanel;
	private static DockPanel workspacePanel;
	private static SimplePanel workareaPanel;

	public WorkspaceWidget() {
		// initialize widgets
		impediments = new ImpedimentListWidget();
		backlog = new BacklogItemListWidget(new ListProvider() {

			public List<BacklogItem> getList() {
				return ScrumClient.getProject().getBacklogItems();
			}

		});
		sprint = new CurrentSprintView();

		// create workspace
		workspacePanel = new DockPanel();
		workspacePanel.setStyleName(StyleSheet.WORKSPACE_WIDGET_WORKSPACE);
		workspacePanel.setWidth("100%");

		// sidebar
		Widget sidebar = createSidebar();
		workspacePanel.add(sidebar, DockPanel.WEST);
		workspacePanel.setCellHeight(sidebar, "100%");

		// workarea
		workareaPanel = new SimplePanel();
		workareaPanel.setStyleName(StyleSheet.WORKSPACE_WIDGET_WORKAREA);
		workspacePanel.add(workareaPanel, DockPanel.CENTER);
		workspacePanel.setStyleName(StyleSheet.WORKSPACE_WIDGET);
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

	public static void showSprint() {
		sprint.update();
		setWorkarea(sprint);
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
		sidebar.setStyleName(StyleSheet.WORKSPACE_WIDGET_SIDEBAR);
		sidebar.setWidth("300px");
		sidebar.setHeight("100%");
		sidebar.add(new SidebarWidget());
		return sidebar;
	}

}
