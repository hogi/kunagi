package scrum.client.service;

import scrum.client.admin.User;
import scrum.client.project.Project;

public class Service {

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
		project = Dummy.moon;
	}

	public static Project getProject() {
		return project;
	}

}
