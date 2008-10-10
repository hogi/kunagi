package scrum.server;

import ilarkesto.logging.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import scrum.client.service.ProjectDto;
import scrum.client.service.UserDto;

public class DataStore {

	private static final Logger LOG = Logger.get(DataStore.class);

	private static final DataStore INSTANCE = new DataStore();

	private Map<String, ProjectDto> projects = new HashMap<String, ProjectDto>();
	private Map<String, UserDto> users = new HashMap<String, UserDto>();

	public ProjectDto getProject(String id) {
		ProjectDto project = projects.get(id);
		if (project == null) project = loadProject(id);
		return project;
	}

	private ProjectDto loadProject(String id) {
		LOG.info("Loading project", id);

		// dummy
		ProjectDto project = new ProjectDto();
		project.id = id;
		project.label = "Project" + id;
		project.users = new HashSet<UserDto>();
		project.users.add(getUser("1"));
		project.users.add(getUser("2"));
		project.users.add(getUser("3"));
		project.users.add(getUser("4"));
		project.users.add(getUser("5"));
		project.ownerId = "1";
		project.masterId = "2";
		project.teamMemberIds = new HashSet<String>();
		project.teamMemberIds.add("3");
		project.teamMemberIds.add("4");
		project.teamMemberIds.add("5");

		projects.put(project.id, project);
		return project;
	}

	public UserDto getUser(String id) {
		UserDto user = users.get(id);
		if (user == null) user = loadUser(id);
		return user;
	}

	private UserDto loadUser(String id) {
		LOG.info("Loading user ", id);

		// dummy
		UserDto user = new UserDto();
		user.id = id;
		user.name = "User" + id;

		users.put(user.id, user);
		return user;
	}

	public static DataStore get() {
		return INSTANCE;
	}

}
