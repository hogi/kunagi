package scrum.server;

import ilarkesto.base.Utl;
import ilarkesto.base.time.Date;
import ilarkesto.logging.Logger;
import ilarkesto.persistence.ADao;
import ilarkesto.persistence.AEntity;
import ilarkesto.webapp.AWebApplication;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import scrum.server.admin.User;
import scrum.server.admin.UserDao;
import scrum.server.project.Project;
import scrum.server.project.ProjectDao;
import scrum.server.project.Requirement;
import scrum.server.sprint.Sprint;
import scrum.server.sprint.Task;

public class ScrumServiceImpl extends GScrumServiceImpl {

	private static final Logger LOG = Logger.get(ScrumServiceImpl.class);

	// --- dependencies ---

	private ProjectDao projectDao;
	private UserDao userDao;
	private ScrumWebApplication webApplication;

	public void setWebApplication(ScrumWebApplication webApplication) {
		this.webApplication = webApplication;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// --- ---

	@Override
	protected void onLogout(WebSession session) {
		session.invalidate();
		webApplication.destroyWebSession(session, getThreadLocalRequest().getSession());
	}

	@Override
	protected void onResetPassword(WebSession session, String userId) {
		User user = userDao.getById(userId);
		user.setPassword("geheim");
	}

	@Override
	protected void onChangePassword(WebSession session, String oldPassword, String newPassword) {
		if (session.getUser().matchesPassword(oldPassword) == false) { throw new RuntimeException("bad password"); }

		session.getUser().setPassword(newPassword);

		LOG.info("password changed by user");
	}

	@Override
	public void onCreateEntity(WebSession session, String type, Map properties) {
		String id = (String) properties.get("id");
		if (id == null) throw new NullPointerException("id == null");
		ADao dao = getDaoService().getDaoByName(type);
		AEntity entity = dao.newEntityInstance(id);
		entity.updateProperties(properties);
		dao.saveEntity(entity);
	}

	@Override
	public void onDeleteEntity(WebSession session, String entityId) {
		AEntity entity = getDaoService().getEntityById(entityId);
		ADao dao = getDaoService().getDao(entity);
		dao.deleteEntity(entity);
		for (WebSession s : webApplication.getOtherSessionsByProject(session)) {
			s.getNextData().addDeletedEntity(entityId);
		}
	}

	@Override
	public void onChangeProperties(WebSession session, String entityId, Map properties) {
		AEntity entity = getDaoService().getEntityById(entityId);
		entity.updateProperties(properties);

		// probably dirty hacked stuff x-ing
		if (entity instanceof Task) {
			Task task = (Task) entity;
			task.getRequirement().getSprint().getDaySnapshot(Date.today()).update();
		}

		if (entity instanceof Requirement) {
			Requirement requirement = (Requirement) entity;
			requirement.getProject().getCurrentSprintSnapshot().update();
		}

		if (entity instanceof Project) {
			Project project = (Project) entity;

		}

		for (WebSession s : webApplication.getOtherSessionsByProject(session)) {
			LOG.debug("Sending changes to", s);
			s.getNextData().addEntity(toPropertyMap(entity));
		}
	}

	@Override
	public void onLogin(WebSession session, String username, String password) {
		User user = userDao.getUserByName(username);
		if (user == null) throw new RuntimeException("Login failed.");

		if (user.matchesPassword(password) == false) { throw new RuntimeException("Wrong password"); }

		session.setUser(user);
		session.getNextData().entityIdBase = UUID.randomUUID().toString();
		session.getNextData().setUserId(user.getId());
		session.getNextData().addEntity(toPropertyMap(user));
		session.getNextData().addEntities(toPropertyMap(projectDao.getEntitiesVisibleForUser(user)));
		session.getNextData().addEntities(toPropertyMap(userDao.getEntitiesVisibleForUser(user)));
	}

	@Override
	public void onSelectProject(WebSession session, String projectId) {
		Project project = projectDao.getById(projectId);
		session.setProject(project);

		// prepare data for client
		session.getNextData().addEntity(toPropertyMap(project));
		if (project.isCurrentSprintSet()) {
			session.getNextData().addEntity(toPropertyMap(project.getCurrentSprint()));
		}
		if (project.isNextSprintSet()) {
			session.getNextData().addEntity(toPropertyMap(project.getNextSprint()));
		}
	}

	@Override
	protected void onSwitchToNextSprint(WebSession session) {
		Project project = session.getProject();
		project.switchToNextSprint();
		session.getNextData().addEntity(toPropertyMap(project));
		session.getNextData().addEntity(toPropertyMap(project.getCurrentSprint()));
		session.getNextData().addEntity(toPropertyMap(project.getNextSprint()));
	}

	@Override
	public void onRequestCurrentSprint(WebSession session) {
		Project project = session.getProject();
		Sprint sprint = project.getCurrentSprint();
		if (sprint == null) return;
		session.getNextData().addEntity(toPropertyMap(sprint));
		session.getNextData().addEntities(toPropertyMap(project.getRequirements()));
		session.getNextData().addEntities(toPropertyMap(sprint.getTasks()));
	}

	@Override
	public void onRequestImpediments(WebSession session) {
		Project project = session.getProject();
		if (project == null) throw new RuntimeException("No project selected.");
		session.getNextData().addEntities(toPropertyMap(project.getImpediments()));
	}

	@Override
	protected void onRequestRisks(WebSession session) {
		Project project = session.getProject();
		if (project == null) throw new RuntimeException("No project selected.");
		session.getNextData().addEntities(toPropertyMap(project.getRisks()));
	}

	@Override
	public void onRequestRequirements(WebSession session) {
		Project project = session.getProject();
		if (project == null) throw new RuntimeException("No project selected.");
		Collection<Requirement> stories = project.getRequirements();
		for (Requirement item : stories) {
			Sprint sprint = item.getSprint();
			if (sprint != null) session.getNextData().addEntity(toPropertyMap(sprint));
		}
		session.getNextData().addEntities(toPropertyMap(stories));
	}

	@Override
	protected void onRequestQualitys(WebSession session) {
		Project project = session.getProject();
		if (project == null) throw new RuntimeException("No project selected.");
		session.getNextData().addEntities(toPropertyMap(project.getQualitys()));
	}

	@Override
	public void onPing(WebSession session) {
	// nop
	}

	@Override
	public void onSleep(WebSession session, long millis) {
		Utl.sleep(millis);
	}

	@Override
	protected Class<? extends AWebApplication> getWebApplicationClass() {
		return ScrumWebApplication.class;
	}

}
