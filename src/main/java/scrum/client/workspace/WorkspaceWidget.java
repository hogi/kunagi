package scrum.client.workspace;

import ilarkesto.gwt.client.GwtLogger;
import scrum.client.admin.LoginWidget;
import scrum.client.admin.ProjectSelectionWidget;
import scrum.client.common.PanelWidget;
import scrum.client.common.StyleSheet;
import scrum.client.impediments.ImpedimentListWidget;
import scrum.client.project.ProductBacklogWidget;
import scrum.client.project.ProjectOverviewWidget;
import scrum.client.sprint.SprintBacklogWidget;
import scrum.client.test.TestWidget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class WorkspaceWidget extends Composite {

	private Widget currentWorkareaWidget;

	private LoginWidget login;
	private ProjectSelectionWidget projectSelector;
	private ProductBacklogWidget backlog;
	private SprintBacklogWidget sprint;
	private ImpedimentListWidget impediments;
	private ProjectOverviewWidget projectOverview;

	private SimplePanel rootPanel;
	private DockPanel workspacePanel;
	private SimplePanel workareaPanel;

	public WorkspaceWidget() {
		// initialize widgets
		login = new LoginWidget();
		projectSelector = new ProjectSelectionWidget();
		impediments = new ImpedimentListWidget();
		backlog = new ProductBacklogWidget();
		sprint = new SprintBacklogWidget();
		currentWorkareaWidget = new Label("Workarea");

		// create workspace
		workspacePanel = new DockPanel();
		workspacePanel.setStyleName(StyleSheet.ELEMENT_WORKSPACE_WIDGET_WORKSPACE);

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
		initWidget(rootPanel);

	}

	public ProductBacklogWidget getBacklog() {
		return backlog;
	}

	public ImpedimentListWidget getImpediments() {
		return impediments;
	}

	public ProjectSelectionWidget getProjectSelector() {
		return projectSelector;
	}

	public void lock(String message) {
		GwtLogger.DEBUG("Locking workspace:", message);
		rootPanel.setWidget(new PanelWidget("Please Wait", new Label(message)));
	}

	public void unlock() {
		GwtLogger.DEBUG("Unlocking workspace");
		rootPanel.setWidget(workspacePanel);
	}

	public void activateLogin() {
		rootPanel.setWidget(login);
	}

	public void activateProjectSelection() {
		GwtLogger.DEBUG("Activating project selector");
		rootPanel.setWidget(projectSelector);
	}

	public void showProjectOverview() {
		getProjectOverview().update();
		showWidget(projectOverview, "Project Overview");
	}

	public boolean isProjectOverview() {
		return currentWorkareaWidget == projectOverview;
	}

	public ProjectOverviewWidget getProjectOverview() {
		if (projectOverview == null) projectOverview = new ProjectOverviewWidget();
		return projectOverview;
	}

	public void showImpediments() {
		impediments.update();
		showWidget(impediments, "Impediments");
	}

	public void showSprint() {
		sprint.update();
		showWidget(sprint, "Sprint Backlog");
	}

	public void showBacklog() {
		backlog.update();
		showWidget(backlog, "Product Backlog");
	}

	public void showTest() {
		showWidget(new TestWidget(), "Test");
	}

	private void showWidget(Widget widget, String title) {
		GwtLogger.DEBUG("showWidget:", title);
		unlock();
		workareaPanel.setWidget(new PanelWidget(title, widget));
		currentWorkareaWidget = widget;
	}

	private Widget createSidebar() {
		VerticalPanel sidebar = new VerticalPanel();
		sidebar.setStyleName(StyleSheet.ELEMENT_WORKSPACE_WIDGET_SIDEBAR);
		sidebar.add(new SidebarWidget());
		return sidebar;
	}

}
