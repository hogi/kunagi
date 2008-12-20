package scrum.server;

import ilarkesto.base.Url;
import ilarkesto.base.Utl;
import ilarkesto.base.time.Date;
import ilarkesto.concurrent.TaskManager;
import ilarkesto.io.IO;
import ilarkesto.logging.Logger;
import ilarkesto.persistence.ADao;
import ilarkesto.persistence.AEntity;
import ilarkesto.persistence.EntityUtils;
import ilarkesto.ui.web.AWebApplication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import scrum.server.admin.User;
import scrum.server.admin.UserDao;
import scrum.server.impediments.Impediment;
import scrum.server.project.Project;
import scrum.server.project.Story;
import scrum.server.sprint.Sprint;
import scrum.server.sprint.SprintBurndownChart;
import scrum.server.sprint.SprintDaySnapshot;
import scrum.server.sprint.Task;

public class ScrumWebApplication extends GScrumWebApplication {

	private static final Logger LOG = Logger.get(ScrumWebApplication.class);

	private Set<SessionData> sessions = new HashSet<SessionData>();
	private SprintBurndownChart sprintBurndownChart;

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

		// probably dirty hacked stuff x-ing
		if (entity instanceof Task) {
			Task task = (Task) entity;
			task.getStory().getSprint().getDaySnapshot(Date.today()).update();
		}

		for (SessionData s : getOtherSessions(session)) {
			s.getNextData().addEntity(toPropertyMap(entity));
		}
	}

	@Override
	public void onLogin(SessionData session, String username, String password) {
		User user = getUserDao().getUserByName(username);
		if (user == null) throw new RuntimeException("Login failed.");

		session.getNextData().setUserId(user.getId());
		session.getNextData().addEntity(toPropertyMap(user));
		// TODO limit to users projects
		session.getNextData().addEntities(toPropertyMap(getProjectDao().getEntities()));
	}

	@Override
	public void onSelectProject(SessionData session, String projectId) {
		Project project = (Project) getProjectDao().getEntities().toArray()[0];
		session.setProject(project);

		// prepare data for client
		session.getNextData().addEntity(toPropertyMap(project));
		session.getNextData().addEntities(toPropertyMap(project.getMembers()));
		if (project.isCurrentSprintSet()) session.getNextData().addEntity(toPropertyMap(project.getCurrentSprint()));
	}

	@Override
	public void onRequestCurrentSprint(SessionData session) {
		Project project = session.getProject();
		Sprint sprint = project.getCurrentSprint();
		if (sprint == null) return;
		session.getNextData().addEntity(toPropertyMap(sprint));
		session.getNextData().addEntities(toPropertyMap(project.getStorys()));
		session.getNextData().addEntities(toPropertyMap(sprint.getTasks()));
	}

	@Override
	public void onRequestImpediments(SessionData session) {
		Project project = session.getProject();
		if (project == null) throw new RuntimeException("No project selected.");
		session.getNextData().addEntities(toPropertyMap(project.getImpediments()));
	}

	@Override
	public void onRequestStorys(SessionData session) {
		Project project = session.getProject();
		if (project == null) throw new RuntimeException("No project selected.");
		Collection<Story> stories = project.getStorys();
		for (Story item : stories) {
			Sprint sprint = item.getSprint();
			if (sprint != null) session.getNextData().addEntity(toPropertyMap(sprint));
		}
		session.getNextData().addEntities(toPropertyMap(stories));
	}

	@Override
	public void onPing(SessionData session) {}

	@Override
	public void onSleep(SessionData session, long millis) {
		Utl.sleep(millis);
	}

	// --- components ---

	public SprintBurndownChart getSprintBurndownChart() {
		if (sprintBurndownChart == null) {
			sprintBurndownChart = new SprintBurndownChart();
			autowire(sprintBurndownChart);
		}
		return sprintBurndownChart;
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
		if (getProjectDao().getEntities().isEmpty()) createTestData();
	}

	private void createTestData() {
		LOG.warn("Creating test data");

		User user1 = getUserDao().postUser("homer", "geheim");
		User user2 = getUserDao().postUser("cartman", "geheim");
		User user3 = getUserDao().postUser("duke", "geheim");

		Project project1 = getProjectDao().postProject("test project", getUserDao().getUserByName("admin"));
		project1.addAdmins(getUserDao().getEntities());
		project1.addTeamMembers(getUserDao().getEntities());
		project1.setProductOwner(user1);
		project1.setScrumMaster(user2);
		project1
				.setDescription("Test project with homer, cartman, duke and admin. Some impediments, some backlog items. Also a current sprint.");

		Impediment impediment1 = getImpedimentDao().newEntityInstance();
		impediment1.setProject(project1);
		impediment1.setLabel("Test Impediment 1");
		getImpedimentDao().saveEntity(impediment1);

		Impediment impediment2 = getImpedimentDao().newEntityInstance();
		impediment2.setProject(project1);
		impediment2.setLabel("Test Impediment 2");
		getImpedimentDao().saveEntity(impediment2);

		Impediment impediment3 = getImpedimentDao().newEntityInstance();
		impediment3.setProject(project1);
		impediment3.setLabel("Test Impediment 3");
		getImpedimentDao().saveEntity(impediment3);

		Story story1 = getStoryDao().newEntityInstance();
		story1.setProject(project1);
		story1.setLabel("Test Backlog Item 1");
		getStoryDao().saveEntity(story1);

		Story story2 = getStoryDao().newEntityInstance();
		story2.setProject(project1);
		story2.setLabel("Test Backlog Item 2");
		story2.setEstimatedWork(5);
		getStoryDao().saveEntity(story2);

		Task task1 = getTaskDao().newEntityInstance();
		task1.setStory(story2);
		task1.setLabel("Task 1");
		task1.setRemainingWork(3);
		task1.setBurnedWork(5);
		getTaskDao().saveEntity(task1);

		Task task2 = getTaskDao().newEntityInstance();
		task2.setStory(story2);
		task2.setLabel("Task 2");
		task2.setRemainingWork(1);
		getTaskDao().saveEntity(task2);

		Story story3 = getStoryDao().newEntityInstance();
		story3.setProject(project1);
		story3.setLabel("Test Backlog Item 3");
		getStoryDao().saveEntity(story3);

		Sprint sprint1 = getSprintDao().newEntityInstance();
		sprint1.setProject(project1);
		sprint1.setLabel("Sprint 1");
		getSprintDao().saveEntity(sprint1);
		story2.setSprint(sprint1);
		for (int i = 1; i <= 5; i++) {
			SprintDaySnapshot snapshot = sprint1.getDaySnapshot(Date.today().addDays(-i));
			snapshot.setBurnedWork(i % 2);
			snapshot.setRemainingWork(50 - i);
		}

		project1.setCurrentSprint(sprint1);

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

	public static ScrumWebApplication get() {
		return (ScrumWebApplication) AWebApplication.get();
	}

}
