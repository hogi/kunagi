package scrum.client.context;

import scrum.client.workspace.StartPageWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class StartContext extends AContext {

	private static StartContext singleton;

	private Label sidebar = new Label("");
	private StartPageWidget startPage = new StartPageWidget();

	public StartContext() {
		assert singleton == null;
		singleton = this;
	}

	@Override
	public Widget getSidebarWidget() {
		return sidebar;
	}

	@Override
	public Widget getWorkareaWidget() {
		return startPage;
	}

	public StartPageWidget getStartPage() {
		return startPage;
	}

	public static StartContext get() {
		assert singleton != null;
		return singleton;
	}

	public static void destroy() {
		singleton = null;
	}

}
