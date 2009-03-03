package scrum.server;

import ilarkesto.base.Utl;
import ilarkesto.base.time.Date;
import ilarkesto.logging.Logger;
import ilarkesto.persistence.ADao;
import ilarkesto.persistence.AEntity;

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

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// --- ---

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
			task.getRequirement().getSprint().getDaySnapshot(Date.today()).update();
		}

		if (entity instanceof Requirement) {
			Requirement requirement = (Requirement) entity;
			requirement.getProject().getCurrentSprintSnapshot().update();
		}

		if (entity instanceof Project) {
			Project project = (Project) entity;

		}

		for (SessionData s : getOtherSessions(session)) {
			LOG.debug("Sending changes to", s);
			s.getNextData().addEntity(toPropertyMap(entity));
		}
	}

	@Override
	public void onLogin(SessionData session, String username, String password) {
		User user = userDao.getUserByName(username);
		if (user == null) throw new RuntimeException("Login failed.");

		session.setUser(user);
		session.getNextData().setUserId(user.getId());
		session.getNextData().addEntity(toPropertyMap(user));
		// TODO limit to users projects
		session.getNextData().addEntities(toPropertyMap(projectDao.getEntities()));
		session.getNextData().entityIdBase = UUID.randomUUID().toString();
	}

	@Override
	public void onSelectProject(SessionData session, String projectId) {
		Project project = projectDao.getById(projectId);
		session.setProject(project);

		// prepare data for client
		session.getNextData().addEntity(toPropertyMap(project));
		session.getNextData().addEntities(toPropertyMap(project.getMembers()));
		if (project.isCurrentSprintSet()) {
			session.getNextData().addEntity(toPropertyMap(project.getCurrentSprint()));
		}
	}

	@Override
	public void onRequestCurrentSprint(SessionData session) {
		Project project = session.getProject();
		Sprint sprint = project.getCurrentSprint();
		if (sprint == null) return;
		session.getNextData().addEntity(toPropertyMap(sprint));
		session.getNextData().addEntities(toPropertyMap(project.getRequirements()));
		session.getNextData().addEntities(toPropertyMap(sprint.getTasks()));
	}

	@Override
	public void onRequestImpediments(SessionData session) {
		Project project = session.getProject();
		if (project == null) throw new RuntimeException("No project selected.");
		session.getNextData().addEntities(toPropertyMap(project.getImpediments()));
	}

	@Override
	protected void onRequestRisks(SessionData session) {
		Project project = session.getProject();
		if (project == null) throw new RuntimeException("No project selected.");
		session.getNextData().addEntities(toPropertyMap(project.getRisks()));
	}

	@Override
	public void onRequestRequirements(SessionData session) {
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
	public void onPing(SessionData session) {
	// nop
	}

	@Override
	public void onSleep(SessionData session, long millis) {
		Utl.sleep(millis);
	}

}
