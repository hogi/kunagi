package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.GwtLogger;
import ilarkesto.gwt.client.LockWidget;
import scrum.client.admin.LoginWidget;
import scrum.client.admin.ProjectSelectorWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * The applications root panel. It manages the top widgets (LoginWidget, ProjectSelectorWidget and
 * WorkspaceWidget). It also provides the global UI locking.
 */
public class Ui extends AWidget {

	private static final Ui SINGLETON = new Ui();

	private LockWidget locker;
	private WaitWidget wait;
	private SimplePanel contentWrapper;
	private AWidget currentWidget;

	private HeaderWidget header;
	private LoginWidget login;
	private ProjectSelectorWidget projectSelector;
	private WorkspaceWidget workspace;

	@Override
	protected Widget onInitialization() {
		wait = new WaitWidget();
		header = new HeaderWidget();

		login = new LoginWidget();
		currentWidget = login;

		contentWrapper = new SimplePanel();
		contentWrapper.setStyleName("content");
		contentWrapper.setWidget(login);

		SimplePanel headerWrapper = new SimplePanel();
		headerWrapper.setStyleName("header");
		headerWrapper.setWidget(header);

		FlowPanel pagePanel = new FlowPanel();
		pagePanel.setStyleName("page");
		pagePanel.add(headerWrapper);
		pagePanel.add(contentWrapper);

		locker = new LockWidget(pagePanel);
		return locker;
	}

	@Override
	protected void onUpdate() {
		header.update();
		contentWrapper.setWidget(currentWidget);
		currentWidget.update();
		locker.update();
	}

	private void setCurrentWidget(AWidget widget) {
		GwtLogger.DEBUG("Setting UI widget:", widget);
		this.currentWidget = widget;
		unlock();
		update();
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

	public ProjectSelectorWidget getProjectSelector() {
		if (projectSelector == null) projectSelector = new ProjectSelectorWidget();
		return projectSelector;
	}

	public void showProjectSelector() {
		setCurrentWidget(getProjectSelector());
	}

	public WorkspaceWidget getWorkspace() {
		if (workspace == null) workspace = new WorkspaceWidget();
		return workspace;
	}

	public void showWorkspace() {
		setCurrentWidget(getWorkspace());
	}

	public static Ui get() {
		return SINGLETON;
	}

	// --- helper ---

}
