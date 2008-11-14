package scrum.server;

import ilarkesto.base.Url;
import ilarkesto.base.Utl;
import ilarkesto.concurrent.TaskManager;
import ilarkesto.io.IO;
import ilarkesto.logging.Logger;
import ilarkesto.persistence.ADao;
import ilarkesto.persistence.AEntity;
import ilarkesto.persistence.EntityUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import scrum.server.admin.UserDao;
import scrum.server.impediments.Impediment;
import scrum.server.project.BacklogItem;
import scrum.server.project.Project;
import scrum.server.sprint.Sprint;
import scrum.server.sprint.Task;

public class ScrumWebApplication extends GScrumWebApplication {

	private static final Logger LOG = Logger.get(ScrumWebApplication.class);

	private Set<SessionData> sessions = new HashSet<SessionData>();

	// --- client call handlers ---

	@Override
	public void onCreateEntity(SessionData session, String type, Map properties) {
		String id = (String) properties.get("id");
		if (id == null) throw new NullPointerException("id == null");
		ADao dao = getDaoService().getDaoByName(type);
		AEntity entity = dao.newEntityInstance(id);
		entity.updateProperties(properties);
		dao.saveEntity(entity);
	}

	@Override
	public void onDeleteEntity(SessionData session, String entityId) {
		AEntity entity = getDaoService().getEntityById(entityId);
		ADao dao = getDaoService().getDao(entity);
		dao.deleteEntity(entity);
		for (SessionData s : getOtherSessions(session)) {
			s.getNextData().addDeletedEntity(entityId);
		}
	}

	@Override
	public void onChangeProperties(SessionData session, String entityId, Map properties) {
		AEntity entity = getDaoService().getEntityById(entityId);
		entity.updateProperties(properties);
		for (SessionData s : getOtherSessions(session)) {
			s.getNextData().addEntity(toPropertyMap(entity));
		}
	}

	@Override
	public void onGetProject(SessionData session, String projectId) {
		Project project = (Project) getProjectDao().getEntities().toArray()[0];
		session.setProject(project);

		// prepare data for client
		session.getNextData().addEntity(toPropertyMap(project));
	}

	@Override
	public void onGetCurrentSprint(SessionData session) {
		Project project = session.getProject();
		Sprint sprint = project.getCurrentSprint();
		if (sprint == null) return;
		session.getNextData().addEntity(toPropertyMap(sprint));
		session.getNextData().addEntities(toPropertyMap(project.getBacklogItems()));
		session.getNextData().addEntities(toPropertyMap(sprint.getTasks()));
	}

	@Override
	public void onGetImpediments(SessionData session) {
		Project project = session.getProject();
		if (project == null) throw new RuntimeException("No project selected.");
		session.getNextData().addEntities(toPropertyMap(project.getImpediments()));
	}

	@Override
	public void onGetBacklogItems(SessionData session) {
		Project project = session.getProject();
		if (project == null) throw new RuntimeException("No project selected.");
		session.getNextData().addEntities(toPropertyMap(project.getBacklogItems()));
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
	public void ensureIntegrity() {
		// delete everything
		IO.delete(getApplicationDataDir());

		super.ensureIntegrity();
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

			Impediment impediment1 = getImpedimentDao().newEntityInstance();
			impediment1.setProject(project);
			impediment1.setLabel("Test Impediment 1");
			getImpedimentDao().saveEntity(impediment1);

			Impediment impediment2 = getImpedimentDao().newEntityInstance();
			impediment2.setProject(project);
			impediment2.setLabel("Test Impediment 2");
			getImpedimentDao().saveEntity(impediment2);

			BacklogItem backlogItem1 = getBacklogItemDao().newEntityInstance();
			backlogItem1.setProject(project);
			backlogItem1.setLabel("Test Backlog Item 1");
			getBacklogItemDao().saveEntity(backlogItem1);

			BacklogItem backlogItem2 = getBacklogItemDao().newEntityInstance();
			backlogItem2.setProject(project);
			backlogItem2.setLabel("Test Backlog Item 2");
			getBacklogItemDao().saveEntity(backlogItem2);

			Task task1 = getTaskDao().newEntityInstance();
			task1.setBacklogItem(backlogItem2);
			task1.setLabel("Task 1");
			getTaskDao().saveEntity(task1);

			Task task2 = getTaskDao().newEntityInstance();
			task2.setBacklogItem(backlogItem2);
			task2.setLabel("Task 2");
			getTaskDao().saveEntity(task2);

			BacklogItem backlogItem3 = getBacklogItemDao().newEntityInstance();
			backlogItem3.setProject(project);
			backlogItem3.setLabel("Test Backlog Item 3");
			getBacklogItemDao().saveEntity(backlogItem3);

			Sprint sprint1 = getSprintDao().newEntityInstance();
			sprint1.setProject(project);
			sprint1.setLabel("Sprint 1");
			getSprintDao().saveEntity(sprint1);
			backlogItem2.setSprint(sprint1);

			project.setCurrentSprint(sprint1);
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

	private List<SessionData> getOtherSessions(SessionData session) {
		List<SessionData> ret = new ArrayList<SessionData>(sessions);
		ret.remove(session);
		return ret;
	}

	private final Map toPropertyMap(AEntity entity) {
		return entity.createPropertiesMap();
	}

	private final List<Map> toPropertyMap(Collection<? extends AEntity> entities) {
		return EntityUtils.createPropertiesMaps(entities);
	}

}
