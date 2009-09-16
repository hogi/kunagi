package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * The complete Scrum workspace. It contains the SidebarWidget and the WorkareaWidget.
 */
public class WorkspaceWidget extends AWidget {

	private SidebarWidget sidebar;
	private WorkareaWidget workarea;
	private ScrollPanel workareaScroller;

	@Override
	protected Widget onInitialization() {
		setHeight100();

		workarea = new WorkareaWidget();

		workareaScroller = new ScrollPanel(workarea);
		workareaScroller.setStyleName("WorkspaceWidget-workareaScroller");
		workareaScroller.setAlwaysShowScrollBars(true);

		HorizontalPanel panel = new HorizontalPanel();
		panel.setStyleName("WorkspaceWidget");
		panel.setHeight("100%");
		panel.setWidth("100%");
		panel.add(getSidebar());
		panel.setCellWidth(getSidebar(), "200px");
		panel.add(workareaScroller);
		return panel;
	}

	@Override
	protected void onUpdate() {
		workareaScroller.setHeight((Window.getClientHeight() - Ui.HEADER_HEIGHT) + "px");
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
