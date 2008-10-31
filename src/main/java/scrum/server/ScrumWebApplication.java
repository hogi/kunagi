package scrum.server;

import ilarkesto.base.Url;
import ilarkesto.concurrent.TaskManager;
import ilarkesto.logging.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import scrum.client.service.ServerData;
import scrum.server.admin.UserDao;
import scrum.server.project.Project;

public class ScrumWebApplication extends GScrumWebApplication {

	private static final Logger LOG = Logger.get(ScrumWebApplication.class);

	private Set<SessionData> sessions = new HashSet<SessionData>();

	public void onProjectRequested(SessionData session, String id) {
		LOG.info("Project", id, "requested");
		ServerData data = session.getNextData();

		Project project = (Project) getProjectDao().getEntities().toArray()[0];

		data.project = new HashMap<String, String>();
		data.project.put("id", project.getId());
		data.project.put("label", project.getLabel());
	}

	public void onSessionCreated(SessionData session) {
		LOG.info("Session created");
		sessions.add(session);
	}

	@Override
	protected void onStartWebApplication() {
		if (getUserDao().getEntities().isEmpty()) {
			LOG.warn("No users. Creating initial user: admin:geheim");
			getUserDao().postUser("admin", "geheim");
		}

		// test data
		if (getProjectDao().getEntities().isEmpty()) {
			LOG.warn("Creating test project");
			getProjectDao().postProject("test project", getUserDao().getUserByName("admin"));
		}

		getTransactionService().commit();
	}

	@Override
	protected void scheduleTasks(TaskManager tm) {}

	@Override
	protected void onShutdownWebApplication() {}

	@Override
	public Url getHomeUrl() {
		return new Url("index.html");
	}

	private UserDao userDao;

	public UserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDao();
			autowire(userDao);
		}
		return userDao;
	}

}
