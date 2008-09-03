package scrum.client;

import scrum.client.workspace.WorkspaceWidget;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class ScrumGwtApplication implements EntryPoint {

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

		RootPanel.get("workspace").add(new WorkspaceWidget());
	}
}
