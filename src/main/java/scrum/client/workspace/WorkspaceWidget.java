package scrum.client.workspace;

import ilarkesto.core.logging.Log;
import ilarkesto.gwt.client.FullscreenPanel;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.LockWidget;
import ilarkesto.gwt.client.SwitcherWidget;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class WorkspaceWidget extends AScrumWidget {

	private static final Log LOG = Log.get(WorkspaceWidget.class);

	public static final int HEADER_HEIGHT = 25;

	private LockWidget locker;
	private LockInfoWidget lockInfo;
	private SwitcherWidget sidebar;
	private SwitcherWidget workarea = new SwitcherWidget(true);

	@Override
	protected Widget onInitialization() {
		setHeight100();

		lockInfo = new LockInfoWidget();
		HeaderWidget header = new HeaderWidget();
		sidebar = new SwitcherWidget(true);
		workarea.setStyleName("Workspace-body-center-content");

		ScrollPanel sidebarScroller = new ScrollPanel(sidebar);
		sidebarScroller.setHeight("100%");

		ScrollPanel workareaScroller = new ScrollPanel(Gwt.createDiv("Workspace-body-center-content", workarea));
		workareaScroller.setHeight("100%");

		FlowPanel body = new FlowPanel();
		body.setStyleName("Workspace-body");
		body.add(Gwt.createDiv("Workspace-body-west", sidebarScroller));
		body.add(Gwt.createDiv("Workspace-body-center", workareaScroller));

		FlowPanel workspace = Gwt.createFlowPanel(Gwt.createDiv("Workspace-header", header), body);
		workspace.setStyleName("Workspace");

		locker = new LockWidget(workspace);

		return new FullscreenPanel(locker);
	}

	@Override
	protected void onUpdate() {
		LOG.debug("Updating UI");
		super.onUpdate();
	}

	public void abort(String message) {
		Log.DEBUG("Locking UI for ABORT:", message);
		lockInfo.showBug(message + " Restarting your session...");
		locker.lock(lockInfo);
		Gwt.runLater(5000, new Runnable() {

			public void run() {
				Window.Location.reload();
			}

		});
	}

	public void lock(String message) {
		initialize();
		Log.DEBUG("Locking UI:", message);
		lockInfo.showWait(message);
		locker.lock(lockInfo);
	}

	public void unlock() {
		Log.DEBUG("Unlocking UI");
		locker.unlock();
	}

	public void showError(String message) {
		final DialogBox db = new DialogBox();
		db.setSize("200", "150");
		db.setPopupPosition(100, 100);

		FlowPanel panel = new FlowPanel();
		Label text = new Label(message);
		panel.add(text);

		Button close = new Button("close");
		close.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				db.hide();
			}
		});
		panel.add(close);

		db.add(panel);
		db.show();
	}

	public SwitcherWidget getWorkarea() {
		return workarea;
	}

	public SwitcherWidget getSidebar() {
		return sidebar;
	}

}
