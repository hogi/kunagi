package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.StyleSheet;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
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
		sidebar = new SidebarWidget();
		workarea = new WorkareaWidget();

		// sidebar
		VerticalPanel sidebarPanel = new VerticalPanel();
		sidebarPanel.setStyleName(StyleSheet.ELEMENT_WORKSPACE_WIDGET_SIDEBAR);
		sidebarPanel.add(sidebar);

		// workspace
		workspacePanel = new DockPanel();
		workspacePanel.setStyleName(StyleSheet.ELEMENT_WORKSPACE_WIDGET_WORKSPACE);
		workspacePanel.add(sidebarPanel, DockPanel.WEST);
		workspacePanel.setCellHeight(sidebarPanel, "100%");
		workspacePanel.add(workarea, DockPanel.CENTER);
		workspacePanel.setCellWidth(workarea, "99%");

		workspacePanel.setStyleName(StyleSheet.ELEMENT_WORKSPACE_WIDGET);
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
		return sidebar;
	}

	public static WorkspaceWidget get() {
		return Ui.get().getWorkspace();
	}

}
