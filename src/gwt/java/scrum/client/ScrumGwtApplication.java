package scrum.client;

import scrum.client.service.Service;
import scrum.client.workspace.WorkspaceWidget;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class ScrumGwtApplication implements EntryPoint {

	/**
	 * Application entry point.
	 */
	public void onModuleLoad() {
		// simulate login
		Service.login("duke", "geheim");

		// simulate project selection
		Service.selectProject("???");

		RootPanel.get("workspace").add(new WorkspaceWidget());
	}
}
