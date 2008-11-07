package scrum.server;

import ilarkesto.base.Url;
import ilarkesto.concurrent.TaskManager;
import ilarkesto.logging.Logger;
import ilarkesto.persistence.EntityUtils;

import java.util.HashSet;
import java.util.Set;

import scrum.server.admin.UserDao;
import scrum.server.impediments.Impediment;
import scrum.server.project.BacklogItem;
import scrum.server.project.Project;

public class ScrumWebApplication extends GScrumWebApplication {

	private static final Logger LOG = Logger.get(ScrumWebApplication.class);

	private Set<SessionData> sessions = new HashSet<SessionData>();

	public void onChangeProperty(SessionData session, String entityId, String property, String value) {
		LOG.info("changeProperty:", entityId, property, value);
	}

	public void onSelectProject(SessionData session, String id) {
		LOG.info("selectProject:", id);

		Project project = (Project) getProjectDao().getEntities().toArray()[0];
		session.setProject(project);

		// prepare data for client
		session.getNextData().project = project.createPropertiesMap();
	}

	public void onGetImpediments(SessionData session) {
		LOG.info("getImpediments");

		Project project = session.getProject();
		Set<Impediment> impediments = getImpedimentDao().getImpedimentsByProject(project);

		// prepare data for client
		session.getNextData().impediments = EntityUtils.createPropertiesMaps(impediments);
	}

	public void onGetBacklogItems(SessionData session) {
		LOG.info("getBacklogItems");

		Project project = session.getProject();
		Set<BacklogItem> backlogItems = getBacklogItemDao().getBacklogItemsByProject(project);

		// prepare data for client
		session.getNextData().backlogItems = EntityUtils.createPropertiesMaps(backlogItems);
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
			Project project = getProjectDao().postProject("test project", getUserDao().getUserByName("admin"));

			Impediment impediment1 = getImpedimentDao().postImpediment(project);
			impediment1.setLabel("Test Impediment 1");

			Impediment impediment2 = getImpedimentDao().postImpediment(project);
			impediment2.setLabel("Test Impediment 2");

			BacklogItem backlogItem1 = getBacklogItemDao().postBacklogItem(project);
			backlogItem1.setLabel("Test Backlog Item 1");

			BacklogItem backlogItem2 = getBacklogItemDao().postBacklogItem(project);
			backlogItem2.setLabel("Test Backlog Item 2");

			BacklogItem backlogItem3 = getBacklogItemDao().postBacklogItem(project);
			backlogItem3.setLabel("Test Backlog Item 3");

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
