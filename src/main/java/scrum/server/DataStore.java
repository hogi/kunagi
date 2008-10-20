package scrum.server;

import ilarkesto.logging.Logger;

import java.util.HashMap;
import java.util.Map;

import scrum.client.admin.User;
import scrum.client.project.Project;

public class DataStore {

	private static final Logger LOG = Logger.get(DataStore.class);

	private Map<String, Map<String, ?>> projects = new HashMap<String, Map<String, ?>>();
	private Map<String, Map<String, String>> users = new HashMap<String, Map<String, String>>();

	public Map<String, ?> getProject(String id) {
		Map<String, ?> project = projects.get(id);
		if (project == null) project = loadProject(id);
		return project;
	}

	private Map<String, String> loadProject(String id) {
		LOG.info("Loading project", id);

		// dummy
		Map<String, String> project = new HashMap<String, String>();
		project.put(Project.ID, id);
		project.put(Project.LABEL, "Project" + id);

		projects.put(id, project);
		return project;
	}

	public Map<String, String> getUser(String id) {
		Map<String, String> user = users.get(id);
		if (user == null) user = loadUser(id);
		return user;
	}

	private Map<String, String> loadUser(String id) {
		LOG.info("Loading user ", id);

		// dummy
		Map<String, String> user = new HashMap<String, String>();
		user.put(User.ID, id);
		user.put(User.NAME, "User" + id);

		users.put(id, user);
		return user;
	}

}
