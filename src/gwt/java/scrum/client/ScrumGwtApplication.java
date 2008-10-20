package scrum.client;

import scrum.client.dnd.MyFuckingAwesomeDragController;
import scrum.client.service.ScrumClient;
import scrum.client.workspace.WorkspaceWidget;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;

public class ScrumGwtApplication implements EntryPoint {

	private static AbsolutePanel dndPanel;
	private static MyFuckingAwesomeDragController dragController;

	/**
	 * Application entry point.
	 */
	public void onModuleLoad() {
		// RootPanel.get("workspace").add(new WorkspaceWidget());

		// dnd
		dndPanel = new AbsolutePanel();
		dndPanel.setHeight("2000px");
		dndPanel.setWidth("100%");
		dndPanel.add(new WorkspaceWidget());

		RootPanel.get("workspace").add(dndPanel);

		// simulate login
		ScrumClient.login("duke", "geheim");

		// simulate project selection
		ScrumClient.selectProject("???");
	}

	public static MyFuckingAwesomeDragController getDragController() {
		if (dragController == null) {
			if (dndPanel == null) { throw new RuntimeException("dndPanel is null"); }
			dragController = new MyFuckingAwesomeDragController(dndPanel, false);
		}
		return dragController;
	}
}
