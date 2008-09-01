package scrum.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

public class ScrumGwtApplication implements EntryPoint {

	/**
	 * Application entry point.
	 */
	public void onModuleLoad() {
		// load initially required data
		Client.requestUpdateUsers();
		Client.requestUpdateProjects();

		// simulate login
		Client.user = Client.getUser("1");

		// simulate project selection
		Client.project = Client.projects.get(0);

		TabPanel tabPanel = new TabPanel();
		tabPanel.setWidth("100%");
		tabPanel.add(new ProjectSummaryWidget(), "Project Summary");
		tabPanel.add(new ProductBacklogWidget(), "Product Backlog");
		tabPanel.selectTab(0);

		RootPanel.get().add(tabPanel);
	}

}
