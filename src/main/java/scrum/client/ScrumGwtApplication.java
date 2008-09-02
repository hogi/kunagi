package scrum.client;

import scrum.client.impediments.ImpedimentsWidget;
import scrum.client.project.ProductBacklogWidget;
import scrum.client.project.ProjectSummaryWidget;
import scrum.client.test.TestWidget;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

public class ScrumGwtApplication implements EntryPoint {

	public static ProjectSummaryWidget summary;
	public static ProductBacklogWidget backlog;
	public static ImpedimentsWidget impediments;

	/**
	 * Application entry point.
	 */
	public void onModuleLoad() {
		// load initially required data
		Client.requestUpdateUsers();
		Client.requestUpdateProjects();
		Client.requestUpdateImpediments();

		// simulate login
		Client.user = Client.getUser("1");

		// simulate project selection
		Client.project = Client.projects.get(0);

		// initialize widgets
		summary = new ProjectSummaryWidget();
		impediments = new ImpedimentsWidget();
		backlog = new ProductBacklogWidget();

		TabPanel tabPanel = new TabPanel();
		tabPanel.add(summary, "Project Summary");
		tabPanel.add(backlog, "Product Backlog");
		tabPanel.add(impediments, "Impediments");
		tabPanel.add(new TestWidget(), "Test");
		tabPanel.selectTab(2);

		RootPanel root = RootPanel.get("content");
		root.add(tabPanel);
	}
}
