package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.GwtLogger;
import ilarkesto.gwt.client.LockWidget;
import scrum.client.admin.LoginWidget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
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
	private StartPageWidget startPage;
	private WorkspaceWidget workspace;
	private UserConfigWidget userconfig;

	@Override
	protected Widget onInitialization() {
		wait = new WaitWidget();
		header = new HeaderWidget();

		currentWidget = new AWidget() {

			@Override
			protected void onUpdate() {}

			@Override
			protected Widget onInitialization() {
				return new Label("Loading...");
			}
		}.update();

		contentWrapper = new SimplePanel();
		contentWrapper.setStyleName("content");
		contentWrapper.setWidget(currentWidget);

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

	public void reset() {
		login = null;
		startPage = null;
		workspace = null;
		userconfig = null;
	}

	@Override
	protected void onUpdate() {
		header.update();
		contentWrapper.setWidget(currentWidget);
		if (currentWidget != null) currentWidget.update();
		locker.update();
	}

	public void setCurrentWidget(AWidget widget) {
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

	public StartPageWidget getStartPage() {
		if (startPage == null) startPage = new StartPageWidget();
		return startPage;
	}

	public void showLogin() {
		reset();
		setCurrentWidget(getLogin());
	}

	public void showConfiguration() {
		userconfig = getUserconfig();
		userconfig.setPrevWidget(currentWidget);
		setCurrentWidget(userconfig);
	}

	public void showStartPage() {
		setCurrentWidget(getStartPage());
	}

	public WorkspaceWidget getWorkspace() {
		if (workspace == null) workspace = new WorkspaceWidget();
		return workspace;
	}

	public UserConfigWidget getUserconfig() {
		if (userconfig == null) userconfig = new UserConfigWidget();
		return userconfig;
	}

	public LoginWidget getLogin() {
		if (login == null) {
			login = new LoginWidget();
		}
		return login;
	}

	public void showWorkspace() {
		setCurrentWidget(getWorkspace());
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

	public static Ui get() {
		return SINGLETON;
	}

	// --- helper ---

}
