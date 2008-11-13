package scrum.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import scrum.client.admin.User;
import scrum.client.dnd.MyFuckingAwesomeDragController;
import scrum.client.impediments.Impediment;
import scrum.client.project.BacklogItem;
import scrum.client.project.Project;
import scrum.client.workspace.WorkspaceWidget;

import com.google.gwt.user.client.ui.AbsolutePanel;
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

	private AbsolutePanel dndPanel;
	private MyFuckingAwesomeDragController dragController;

	/**
	 * Application entry point.
	 */
	public void onModuleLoad() {
		// RootPanel.get("workspace").add(new WorkspaceWidget());

		// dnd
		dndPanel = new AbsolutePanel();
		dndPanel.setHeight("2000px");
		dndPanel.setWidth("100%");
		dndPanel.add(new WorkspaceWidget());

		RootPanel.get("workspace").add(dndPanel);

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
		if (data.project != null) {
			System.out.println("project received");
			project = new Project(data.project);
		}
		if (data.impediments != null) {
			System.out.println(data.impediments.size() + " impediments received");
			List<Impediment> impediments = new ArrayList<Impediment>();
			for (Map impedimentData : data.impediments) {
				Impediment impediment = new Impediment(impedimentData);
				impediments.add(impediment);
			}
			project.setImpediments(impediments);
		}
		if (data.backlogItems != null) {
			System.out.println(data.backlogItems.size() + " backlog items received");
			List<BacklogItem> backlogItems = new ArrayList<BacklogItem>();
			for (Map backlogItemData : data.backlogItems) {
				BacklogItem backlogItem = new BacklogItem(backlogItemData);
				backlogItems.add(backlogItem);
			}
			project.setBacklogItems(backlogItems);
		}
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
		return project;
	}

	@Override
	public Dao getDao() {
		return dao;
	}

	public static ScrumGwtApplication get() {
		return (ScrumGwtApplication) singleton;
	}

	public MyFuckingAwesomeDragController getDragController() {
		if (dragController == null) {
			if (dndPanel == null) { throw new RuntimeException("dndPanel is null"); }
			dragController = new MyFuckingAwesomeDragController(dndPanel, false);
		}
		return dragController;
	}
}
