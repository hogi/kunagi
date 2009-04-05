package scrum.client;

import ilarkesto.gwt.client.DataTransferObject;
import ilarkesto.gwt.client.GwtLogger;
import scrum.client.admin.User;
import scrum.client.dnd.DndManager;
import scrum.client.project.Project;
import scrum.client.workspace.Ui;
import scrum.client.workspace.WorkspaceWidget;

import com.google.gwt.user.client.ui.RootPanel;

public class ScrumGwtApplication extends GScrumGwtApplication {

	private Dao dao = new Dao();

	/**
	 * Current, logged in user.
	 * 
	 * TODO potential security problem. User could change this variable.
	 */
	private User user;

	/**
	 * Currently selected project.
	 */
	private Project project;

	private DndManager dndManager;

	private Ui ui;

	/**
	 * Application entry point.
	 */
	public void onModuleLoad() {

		// workaround for GWT issue 1813
		// http://code.google.com/p/google-web-toolkit/issues/detail?id=1813
		RootPanel.get().getElement().getStyle().setProperty("position", "relative");

		ui = Ui.get();
		ui.update();
		RootPanel.get("workspace").add(ui);
		ui.lock("Connecting to server...");
		callPing(new Runnable() {

			public void run() {
				ui.unlock();
			}
		});

		new PingTimer().scheduleRepeating(15000);
	}

	public User getUser() {
		return user;
	}

	public Ui getUi() {
		return ui;
	}

	@Override
	protected void handleDataFromServer(DataTransferObject data) {
		super.handleDataFromServer(data);

		if (data.entityIdBase != null) {
			getDao().setEntityIdBase(data.entityIdBase);
			GwtLogger.DEBUG("entityIdBase:", data.entityIdBase);
		}

		if (data.isUserSet()) {
			user = getDao().getUser(data.getUserId());
		}
	}

	@Override
	protected void handleCommunicationError(Throwable ex) {
		ex.printStackTrace();
		ui.lock("Error: " + ex.getMessage());
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Project getProject() {
		return project;
	}

	@Override
	public Dao getDao() {
		return dao;
	}

	public static ScrumGwtApplication get() {
		return (ScrumGwtApplication) singleton;
	}

	public DndManager getDndManager() {
		if (dndManager == null) {
			dndManager = new DndManager();
		}
		return dndManager;
	}

	public void logout() {
		Ui.get().lock("logging out");
		ScrumGwtApplication.get().callLogout(new Runnable() {

			public void run() {
				Ui.get().showLogin();
			}

		});
	}

	// --- helper ---

	public WorkspaceWidget getWorkspaceWidget() {
		return getUi().getWorkspace();
	}

}
