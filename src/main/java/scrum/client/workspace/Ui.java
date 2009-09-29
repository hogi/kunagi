package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.FullScreenDockWidget;
import ilarkesto.gwt.client.GwtLogger;
import ilarkesto.gwt.client.LockWidget;
import ilarkesto.gwt.client.SwitcherWidget;
import scrum.client.context.AContext;
import scrum.client.context.PublicContext;
import scrum.client.context.ProjectContext;
import scrum.client.context.StartContext;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * The applications root panel. It manages the top widgets (LoginWidget, ProjectSelectorWidget and
 * WorkspaceWidget). It also provides the global UI locking.
 */
public class Ui extends AWidget {

	public static final int HEADER_HEIGHT = 25;
	private static final Ui SINGLETON = new Ui();

	private LockWidget locker;
	private WaitWidget wait;
	private SwitcherWidget sidebar;
	private SwitcherWidget workarea;

	@Override
	protected Widget onInitialization() {
		setHeight100();

		wait = new WaitWidget();
		sidebar = new SwitcherWidget(true);
		workarea = new SwitcherWidget(true);

		FullScreenDockWidget dock = new FullScreenDockWidget(new HeaderWidget(), 25, sidebar, 200, workarea);

		locker = new LockWidget(dock);
		return locker;
	}

	@Override
	protected void onUpdate() {
		locker.update();
	}

	public void activatePublicView() {
		ProjectContext.destroy();
		StartContext.destroy();
		activateView(new PublicContext());
	}

	public void activateStartView() {
		ProjectContext.destroy();
		activateView(new StartContext());
	}

	public void activateProjectView() {
		StartContext.destroy();
		activateView(new ProjectContext());
	}

	private void activateView(AContext view) {
		GwtLogger.DEBUG("Activating view:", view);
		sidebar.show(view.getSidebarWidget());
		workarea.show(view.getWorkareaWidget());
		unlock();
	}

	public void lock(String message) {
		GwtLogger.DEBUG("Locking UI:", message);
		wait.setMessage(message);
		locker.lock(wait);
	}

	public void unlock() {
		GwtLogger.DEBUG("Unlocking UI");
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

	public static Ui get() {
		return SINGLETON;
	}

}
