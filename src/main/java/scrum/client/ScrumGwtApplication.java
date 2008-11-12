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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;

public class ScrumGwtApplication implements EntryPoint {

	/**
	 * Current, logged in user.
	 * 
	 * TODO potential security problem. User could change this variable.
	 */
	private static User user;

	/**
	 * Currently selected project.
	 */
	private static Project project;

	private static ScrumServiceAsync scrumService;

	private static AbsolutePanel dndPanel;
	private static MyFuckingAwesomeDragController dragController;

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
		selectProject("???", new Runnable() {

			public void run() {
				WorkspaceWidget.showTest();
			}
		});

	}

	public static void login(String name, String password) {
		// dummy. TODO call server
		user = new User(name, name);
	}

	public static User getUser() {
		return user;
	}

	public static void changeProperty(String entityId, String property, String value) {
		getScrumService().changeProperty(entityId, property, value, new DefaultCallback());
	}

	public static void selectProject(String id, Runnable successAction) {
		getScrumService().getProject(id, new DefaultCallback(successAction));
	}

	public static void requestImpediments(Runnable successAction) {
		getScrumService().getImpediments(new DefaultCallback(successAction));
	}

	public static void requestBacklogItems(Runnable successAction) {
		getScrumService().getBacklogItems(new DefaultCallback(successAction));
	}

	private static void processMaster(DataTransferObject data) {
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
	}

	private static void processError(Throwable ex) {
		ex.printStackTrace();
		WorkspaceWidget.lock("Error: " + ex.getMessage());
	}

	public static Project getProject() {
		return project;
	}

	public static ScrumServiceAsync getScrumService() {
		if (scrumService == null) {
			scrumService = GWT.create(ScrumService.class);
			ServiceDefTarget target = (ServiceDefTarget) scrumService;
			target.setServiceEntryPoint(GWT.getModuleBaseURL() + "scrum");
		}
		return scrumService;
	}

	static class DefaultCallback implements AsyncCallback<DataTransferObject> {

		private Runnable successAction;

		public DefaultCallback() {}

		public DefaultCallback(Runnable successAction) {
			this.successAction = successAction;
		}

		public void onSuccess(DataTransferObject data) {
			processMaster(data);
			if (successAction != null) successAction.run();
		}

		public void onFailure(Throwable ex) {
			processError(ex);
		}

	}

	public static MyFuckingAwesomeDragController getDragController() {
		if (dragController == null) {
			if (dndPanel == null) { throw new RuntimeException("dndPanel is null"); }
			dragController = new MyFuckingAwesomeDragController(dndPanel, false);
		}
		return dragController;
	}
}
