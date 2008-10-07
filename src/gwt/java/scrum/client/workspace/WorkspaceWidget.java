package scrum.client.workspace;

import scrum.client.impediments.ImpedimentListWidget;
import scrum.client.project.BacklogItemListWidget;
import scrum.client.project.Project;
import scrum.client.service.Service;
import scrum.client.test.TestWidget;
import scrum.client.view.PortalView;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
	private static Label projectLabel;

	public WorkspaceWidget() {
		// initialize widgets
		impediments = new ImpedimentListWidget();
		backlog = new BacklogItemListWidget();
		// sprints = new SprintListWidget();

		// create workspace
		workspacePanel = new DockPanel();
		workspacePanel.setStyleName("WorkspaceWidget-workspace");
		workspacePanel.setWidth("100%");

		// header
		Widget header = createHeaderWidget();
		workspacePanel.add(header, DockPanel.NORTH);
		workspacePanel.setCellHeight(header, "35px");
		workspacePanel.setCellWidth(header, "100%");

		// sidebar
		Widget sidebar = createSidebar();
		workspacePanel.add(sidebar, DockPanel.WEST);
		workspacePanel.setCellWidth(sidebar, "200px");
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

	// public static void showSprints() {
	// setWorkarea(sprints);
	// }

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

	private Widget createHeaderWidget() {
		Label appLabel = new Label("scrum");
		appLabel.addStyleName("HeaderWidget-appLabel");

		projectLabel = new Label();
		projectLabel.addStyleName("HeaderWidget-projectLabel");
		updateTitle();

		HorizontalPanel header = new HorizontalPanel();
		header.addStyleName("HeaderWidget");
		header.setWidth("100%");
		header.add(appLabel);
		header.setCellWidth(appLabel, "1%");
		header.add(projectLabel);

		SimplePanel panel = new SimplePanel();
		panel.setWidth("100%");
		panel.setStyleName("WorkspaceWidget-header");
		panel.setWidget(header);
		return panel;
	}

	private Widget createSidebar() {
		VerticalPanel sidebar = new VerticalPanel();
		sidebar.setStyleName("WorkspaceWidget-sidebar");
		sidebar.setWidth("200px");
		sidebar.setHeight("100%");
		sidebar.add(new SidebarWidget());
		return sidebar;
	}

	public static void updateTitle() {
		Project project = Service.getProject();
		projectLabel.setText(project == null ? "Select Project" : project.getLabel());
	}
}
