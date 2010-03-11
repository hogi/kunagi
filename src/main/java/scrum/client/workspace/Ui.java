package scrum.client.workspace;

import ilarkesto.gwt.client.Gwt;
import scrum.client.VisibleDataChangedListener;

import com.google.gwt.user.client.ui.Widget;

public class Ui extends GUi implements VisibleDataChangedListener {

	private WorkspaceWidget workspace;

	@Override
	public void initialize() {
		workspace = new WorkspaceWidget();
		Gwt.setRootWidget(workspace);
	}

	public void onVisibleDataChanged() {
		workspace.update();
	}

	public void lock(String message) {
		workspace.lock(message);
	}

	public void unlock() {
		workspace.unlock();
	}

	public void show(Widget sidebar, Widget workarea) {
		workspace.getSidebar().show(sidebar);
		workspace.getWorkarea().show(workarea);
		workspace.unlock();
	}

	public WorkspaceWidget getWorkspace() {
		return workspace;
	}

}