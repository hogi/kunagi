package scrum.server;

import ilarkesto.base.Url;
import ilarkesto.base.Utl;
import ilarkesto.concurrent.TaskManager;
import ilarkesto.logging.Logger;
import ilarkesto.persistence.ADao;
import ilarkesto.persistence.AEntity;
import ilarkesto.persistence.EntityUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import scrum.server.admin.UserDao;
import scrum.server.impediments.Impediment;
import scrum.server.project.BacklogItem;
import scrum.server.project.Project;

public class ScrumWebApplication extends GScrumWebApplication {

	private static final Logger LOG = Logger.get(ScrumWebApplication.class);

	private Set<SessionData> sessions = new HashSet<SessionData>();

	// --- client call handlers ---

	@Override
	public void onCreateEntity(SessionData session, String type, Map properties) {
		String id = (String) properties.get("id");
		ADao dao = getDaoService().getDaoByName(type);
		AEntity entity = dao.newEntityInstance(id);
		entity.updateProperties(properties);
		dao.saveEntity(entity);
	}

	@Override
	public void onChangeProperties(SessionData session, String entityId, Map properties) {
		AEntity entity = getDaoService().getEntityById(entityId);
		entity.updateProperties(properties);
		// TODO promote change to all sessions
	}

	@Override
	public void onGetProject(SessionData session, String projectId) {
		Project project = (Project) getProjectDao().getEntities().toArray()[0];
		session.setProject(project);

		// prepare data for client
		session.getNextData().project = project.createPropertiesMap();
	}

	@Override
	public void onGetImpediments(SessionData session) {
		LOG.info("getImpediments");

		Project project = session.getProject();
		if (project == null) throw new RuntimeException("No project selected.");
		Set<Impediment> impediments = getImpedimentDao().getImpedimentsByProject(project);

		// prepare data for client
		session.getNextData().impediments = EntityUtils.createPropertiesMaps(impediments);
		session.getNextData().addImpediments(EntityUtils.createPropertiesMaps(impediments));
	}

	@Override
	public void onGetBacklogItems(SessionData session) {
		LOG.info("getBacklogItems");

		Project project = session.getProject();
		if (project == null) throw new RuntimeException("No project selected.");
		Set<BacklogItem> backlogItems = getBacklogItemDao().getBacklogItemsByProject(project);

		// prepare data for client
		session.getNextData().backlogItems = EntityUtils.createPropertiesMaps(backlogItems);
	}

	@Override
	public void onSleep(SessionData session, long millis) {
		Utl.sleep(millis);
	}

	// --- ---

	public void onSessionCreated(SessionData session) {
		LOG.info("Session created");
		sessions.add(session);

		session.getNextData().entityIdBase = UUID.randomUUID().toString();
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
