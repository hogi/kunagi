package scrum.client;

import ilarkesto.gwt.client.AComponent;
import ilarkesto.gwt.client.Gwt;
import scrum.client.workspace.Workspace;

import com.google.gwt.user.client.ui.Widget;

public class Ui extends AComponent {

	private Workspace workspace;

	@Override
	protected void onInitialization() {
		super.onInitialization();
		workspace = new Workspace();
		Gwt.setRootWidget(workspace);
	}

	public void show(Widget sidebar, Widget workarea) {
		workspace.getSidebar().show(sidebar);
		workspace.getWorkarea().show(workarea);
		workspace.unlock();
	}

	public Workspace getWorkspace() {
		return workspace;
	}

}