package scrum.client.service;

import scrum.client.admin.User;
import scrum.client.project.Project;
import scrum.client.workspace.WorkspaceWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class ScrumClient {

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

	public static void login(String name, String password) {
		// dummy. TODO call server
		for (User u : Dummy.users) {
			if (name.equals(u.getName())) {
				user = u;
			}
		}
	}

	public static User getUser() {
		return user;
	}

	public static void selectProject(String id) {
		WorkspaceWidget.lock("Loading project " + id + "...");
		getScrumService().getProject(id, new AsyncCallback<ServerData>() {

			public void onSuccess(ServerData data) {
				processMaster(data);
				WorkspaceWidget.showBacklog();
				WorkspaceWidget.unlock();
			}

			public void onFailure(Throwable ex) {
				processError(ex);
			}
		});
	}

	private static void processMaster(ServerData data) {
		if (data.project != null) {
			System.out.println("project received");
			project = new Project(data);
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

}
