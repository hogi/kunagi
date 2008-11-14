package scrum.client;

import scrum.client.admin.User;
import scrum.client.dnd.ScrumDragController;
import scrum.client.project.Project;
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

	private ScrumDragController dragController;

	/**
	 * Application entry point.
	 */
	public void onModuleLoad() {
		// RootPanel.get("workspace").add(new WorkspaceWidget());

		RootPanel.get("workspace").add(new WorkspaceWidget());

		// simulate login
		login("duke", "geheim");

		// simulate project selection
		WorkspaceWidget.lock("Loading project...");
		callGetProject("???", new Runnable() {

			public void run() {
				WorkspaceWidget.showTest();
			}
		});

	}

	public void login(String name, String password) {
	// dummy. TODO call server
	}

	public User getUser() {
		return user;
	}

	@Override
	protected void handleDataFromServer(DataTransferObject data) {
		super.handleDataFromServer(data);

		if (data.entityIdBase != null) {
			entityIdBase = data.entityIdBase;
			System.out.println("entityIdBase: " + entityIdBase);
		}
	}

	@Override
	protected void handleCommunicationError(Throwable ex) {
		ex.printStackTrace();
		WorkspaceWidget.lock("Error: " + ex.getMessage());
	}

	public Project getProject() {
		if (project == null) {
			project = getDao().getProjects().get(0); // TODO will fail, on project change
		}
		return project;
	}

	@Override
	public Dao getDao() {
		return dao;
	}

	public static ScrumGwtApplication get() {
		return (ScrumGwtApplication) singleton;
	}

	public ScrumDragController getDragController() {
		if (dragController == null) {
			dragController = new ScrumDragController(RootPanel.get("dnd"), false);
		}
		return dragController;
	}
}
