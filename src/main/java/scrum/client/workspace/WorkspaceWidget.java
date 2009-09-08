package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * The complete Scrum workspace. It contains the SidebarWidget and the WorkareaWidget.
 */
public class WorkspaceWidget extends AWidget {

	private DockPanel workspacePanel;

	private SidebarWidget sidebar;
	private WorkareaWidget workarea;

	@Override
	protected Widget onInitialization() {
		workarea = new WorkareaWidget();

		SimplePanel sidebarWrapper = new SimplePanel();
		sidebarWrapper.setStyleName("sidebar");
		sidebarWrapper.add(getSidebar());

		workspacePanel = new DockPanel();
		workspacePanel.setStyleName("WorkspaceWidget");
		workspacePanel.setWidth("100%");
		workspacePanel.add(sidebarWrapper, DockPanel.WEST);
		workspacePanel.setCellHeight(sidebarWrapper, "100%");
		workspacePanel.add(workarea, DockPanel.CENTER);
		workspacePanel.setCellWidth(workarea, "99%");

		return workspacePanel;
	}

	@Override
	protected void onUpdate() {
		sidebar.update();
		workarea.update();
	}

	public WorkareaWidget getWorkarea() {
		return workarea;
	}

	public SidebarWidget getSidebar() {
		if (sidebar == null) sidebar = new SidebarWidget();
		return sidebar;
	}

	public boolean isWorkareaActive() {
		return workarea != null;
	}

	public static WorkspaceWidget get() {
		return Ui.get().getWorkspace();
	}

}
