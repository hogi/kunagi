package scrum.client.workspace;

import scrum.client.ScrumGwtApplication;
import scrum.client.admin.LoginWidget;
import scrum.client.admin.ProjectSelectionWidget;
import scrum.client.common.PanelWidget;
import scrum.client.common.StyleSheet;
import scrum.client.impediments.ImpedimentListWidget;
import scrum.client.project.BacklogItemListWidget;
import scrum.client.project.ProjectOverviewWidget;
import scrum.client.sprint.CurrentSprintWidget;
import scrum.client.test.TestWidget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class WorkspaceWidget extends Composite {

	private static LoginWidget login;
	public static ProjectSelectionWidget projectSelection;
	public static BacklogItemListWidget backlog;
	public static CurrentSprintWidget sprint;
	public static ImpedimentListWidget impediments;

	private static SimplePanel rootPanel;
	private static DockPanel workspacePanel;
	private static SimplePanel workareaPanel;

	public WorkspaceWidget() {
		// initialize widgets
		login = new LoginWidget();
		projectSelection = new ProjectSelectionWidget();
		impediments = new ImpedimentListWidget();
		backlog = new BacklogItemListWidget();
		sprint = new CurrentSprintWidget();

		// create workspace
		workspacePanel = new DockPanel();
		workspacePanel.setStyleName(StyleSheet.ELEMENT_WORKSPACE_WIDGET_WORKSPACE);
		workspacePanel.setWidth("100%");

		// sidebar
		Widget sidebar = createSidebar();
		workspacePanel.add(sidebar, DockPanel.WEST);
		workspacePanel.setCellHeight(sidebar, "100%");

		// workarea
		workareaPanel = new SimplePanel();
		workareaPanel.setStyleName(StyleSheet.ELEMENT_WORKSPACE_WIDGET_WORKAREA);
		workspacePanel.add(workareaPanel, DockPanel.CENTER);
		workspacePanel.setStyleName(StyleSheet.ELEMENT_WORKSPACE_WIDGET);
		workspacePanel.setCellWidth(workareaPanel, "99%");

		// root panel
		rootPanel = new SimplePanel();
		rootPanel.setWidget(login);

		initWidget(rootPanel);
	}

	public static void lock(String message) {
		rootPanel.setWidget(new PanelWidget("Please Wait", new Label(message)));
	}

	public static void unlock() {
		rootPanel.setWidget(workspacePanel);
	}

	public static void activateProjectSelection() {
		rootPanel.setWidget(projectSelection);
	}

	public static void showProjectOverview() {
		setWorkarea(new ProjectOverviewWidget(ScrumGwtApplication.get().getProject()), "Project Overview");
	}

	public static void showImpediments() {
		impediments.update();
		setWorkarea(impediments, "Impediments");
	}

	public static void showSprint() {
		sprint.update();
		setWorkarea(sprint, "Current Sprint");
	}

	public static void showBacklog() {
		backlog.update();
		setWorkarea(backlog, "Product Backlog");
	}

	public static void showTest() {
		setWorkarea(new TestWidget(), "Test");
	}

	public static void setWorkarea(Widget widget, String title) {
		unlock();
		// workareaPanel.setWidget(widget);
		workareaPanel.setWidget(new PanelWidget(title, widget));
	}

	private Widget createSidebar() {
		VerticalPanel sidebar = new VerticalPanel();
		sidebar.setStyleName(StyleSheet.ELEMENT_WORKSPACE_WIDGET_SIDEBAR);
		sidebar.setWidth("300px");
		sidebar.setHeight("100%");
		sidebar.add(new SidebarWidget());
		return sidebar;
	}

}
