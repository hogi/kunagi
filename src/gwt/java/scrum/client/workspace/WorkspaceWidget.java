package scrum.client.workspace;

import scrum.client.Client;
import scrum.client.impediments.Impediments2Widget;
import scrum.client.impediments.ImpedimentsWidget;
import scrum.client.project.ProductBacklogWidget;
import scrum.client.project.ProjectSummaryWidget;
import scrum.client.test.TestWidget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class WorkspaceWidget extends Composite {

	public static ProjectSummaryWidget summary;
	public static ProductBacklogWidget backlog;
	public static ImpedimentsWidget impediments;

	private Label projectLabel;

	public WorkspaceWidget() {

		DockPanel dock = new DockPanel();
		dock.addStyleName("WorkspaceWidget");
		dock.setWidth("100%");
		dock.setHeight("100%");

		dock.add(createHeaderWidget(), DockPanel.NORTH);

		Widget projectWidget = createProjectWidget();
		dock.add(projectWidget, DockPanel.CENTER);
		dock.setCellHeight(projectWidget, "99%");

		initWidget(dock);
	}

	private Widget createHeaderWidget() {
		Label appLabel = new Label("scrum");
		appLabel.addStyleName("WorkspaceWidget-header-appLabel");

		projectLabel = new Label();
		projectLabel.addStyleName("WorkspaceWidget-header-projectLabel");
		updateTitle();

		HorizontalPanel panel = new HorizontalPanel();
		panel.addStyleName("WorkspaceWidget-header");
		panel.add(appLabel);
		panel.add(projectLabel);

		return panel;
	}

	private Widget createProjectWidget() {
		// initialize widgets
		summary = new ProjectSummaryWidget();
		impediments = new ImpedimentsWidget();
		backlog = new ProductBacklogWidget();

		// build TabPanel for widgets
		TabPanel tabPanel = new TabPanel();
		tabPanel.add(summary, "Project Summary");
		tabPanel.add(backlog, "Product Backlog");
		tabPanel.add(impediments, "Impediments");
		tabPanel.add(new Impediments2Widget(), "Impediments 2");
		tabPanel.add(new TestWidget(), "Test");
		tabPanel.selectTab(3);

		return tabPanel;
	}

	public void updateTitle() {
		projectLabel.setText(Client.project.label);
	}
}
