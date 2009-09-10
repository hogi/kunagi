package scrum.server;

import ilarkesto.auth.Auth;
import ilarkesto.base.PermissionDeniedException;
import ilarkesto.base.Utl;
import ilarkesto.base.time.Date;
import ilarkesto.logging.Logger;
import ilarkesto.persistence.ADao;
import ilarkesto.persistence.AEntity;
import ilarkesto.webapp.AWebApplication;
import ilarkesto.webapp.AWebSession;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import scrum.server.admin.User;
import scrum.server.admin.UserDao;
import scrum.server.common.Numbered;
import scrum.server.common.Transient;
import scrum.server.project.Project;
import scrum.server.project.ProjectDao;
import scrum.server.project.Requirement;
import scrum.server.project.RequirementDao;
import scrum.server.sprint.Task;

public class ScrumServiceImpl extends GScrumServiceImpl {

	private static final Logger LOG = Logger.get(ScrumServiceImpl.class);

	// --- dependencies ---

	private ProjectDao projectDao;
	private UserDao userDao;
	private RequirementDao requirementDao;
	private ScrumWebApplication webApplication;

	public void setWebApplication(ScrumWebApplication webApplication) {
		this.webApplication = webApplication;
	}

	public void setRequirementDao(RequirementDao requirementDao) {
		this.requirementDao = requirementDao;
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

		if (entity instanceof Numbered) {
			((Numbered) entity).updateNumber();
		}

		if (!(entity instanceof Transient)) dao.saveEntity(entity);
		session.sendToClient(entity);

		for (WebSession s : webApplication.getOtherSessionsByProject(session)) {
			// TODO do this only if client is tracking this entity
			s.sendToClient(entity);
		}
	}

	@Override
	public void onDeleteEntity(WebSession session, String entityId) {
		AEntity entity = getDaoService().getEntityById(entityId);
		if (!Auth.isDeletable(entity, session.getUser())) throw new PermissionDeniedException();
		ADao dao = getDaoService().getDao(entity);
		dao.deleteEntity(entity);
		for (WebSession s : webApplication.getOtherSessionsByProject(session)) {
			// TODO do this only if client is tracking this entity
			s.getNextData().addDeletedEntity(entityId);
		}
	}

	@Override
	public void onChangeProperties(WebSession session, String entityId, Map properties) {
		AEntity entity = getDaoService().getEntityById(entityId);
		if (!Auth.isEditable(entity, session.getUser())) throw new PermissionDeniedException();

		if (entity instanceof Task) {
			// update sprint day snapshot before change
			Task task = (Task) entity;
			task.getRequirement().getSprint().getDaySnapshot(Date.today()).update();
		}

		entity.updateProperties(properties);

		if (entity instanceof Task) {
			// update sprint day snapshot after change
			Task task = (Task) entity;
			task.getRequirement().getSprint().getDaySnapshot(Date.today()).update();
		}

		if (entity instanceof Requirement) {
			Requirement requirement = (Requirement) entity;
			if (!properties.containsKey("dirty")) requirement.setDirty(true);
			requirement.getProject().getCurrentSprintSnapshot().update();
		}

		if (entity instanceof Project) {
			Project project = (Project) entity;
		}

		for (AWebSession s : webApplication.getWebSessions()) {
			s.sendToClient(entity);
		}
	}

	@Override
	public void onLogin(WebSession session, String username, String password) {
		session.reinitialize();
		User user = userDao.getUserByName(username);

		if (user == null || user.matchesPassword(password) == false) {
			session.getNextData().errors.add("Login failed.");
			return;
		}

		session.setUser(user);
		session.getNextData().entityIdBase = UUID.randomUUID().toString();
		session.getNextData().setUserId(user.getId());
		session.sendToClient(user);
		session.sendToClient(projectDao.getEntitiesVisibleForUser(user));
		session.sendToClient(userDao.getEntitiesVisibleForUser(user));
	}

	@Override
	public void onSelectProject(WebSession session, String projectId) {
		Project project = projectDao.getById(projectId);
		if (!project.isVisibleFor(session.getUser()))
			throw new RuntimeException("Project '" + project + "' is not visible for user '" + session.getUser() + "'");
		session.setProject(project);
		session.getUser().setCurrentProject(project);

		// prepare data for client
		session.sendToClient(project);
		session.sendToClient(project.getCurrentSprint());
		session.sendToClient(project.getNextSprint());
		session.sendToClient(project.getParticipants());
		session.sendToClient(project.getRequirements());
		session.sendToClient(project.getTasks());
	}

	@Override
	protected void onCloseProject(WebSession session) {
		session.setProject(null);
		session.getUser().setCurrentProject(null);
	}

	@Override
	protected void onSwitchToNextSprint(WebSession session) {
		assertProjectSelected(session);
		Project project = session.getProject();
		project.switchToNextSprint();
		session.sendToClient(project);
		session.sendToClient(project.getCurrentSprint());
		session.sendToClient(project.getNextSprint());
	}

	@Override
	public void onRequestImpediments(WebSession session) {
		assertProjectSelected(session);
		Project project = session.getProject();
		session.sendToClient(project.getImpediments());
	}

	@Override
	protected void onRequestRisks(WebSession session) {
		assertProjectSelected(session);
		Project project = session.getProject();
		session.sendToClient(project.getRisks());
	}

	@Override
	public void onRequestRequirements(WebSession session) {
		assertProjectSelected(session);
		Project project = session.getProject();
		Collection<Requirement> requirements = project.getRequirements();
		for (Requirement requirement : requirements) {
			if (requirement.isSprintSet()) session.sendToClient(requirement.getSprint());
			session.sendToClient(requirement.getTasks());
			session.sendToClient(requirement.getQualitys());
		}
		session.sendToClient(requirements);
		session.sendToClient(project.getQualitys());
		session.sendToClient(project);
	}

	@Override
	protected void onRequestQualitys(WebSession session) {
		assertProjectSelected(session);
		Project project = session.getProject();
		session.sendToClient(project.getQualitys());
	}

	@Override
	protected void onRequestEntityByReference(WebSession session, String reference) {
		assertProjectSelected(session);
		Project project = session.getProject();
		int number = Integer.parseInt(reference.substring(1));
		if (reference.startsWith("r")) {
			Requirement requirement = project.getRequirementByNumber(number);
			if (requirement != null) session.sendToClient(requirement);
			return;
		} else if (reference.startsWith("t")) {
			Task task = project.getTaskByNumber(number);
			if (task != null) session.sendToClient(task);
			return;
		}
		LOG.info("Requested entity not found:", reference);
	}

	@Override
	public void onPing(WebSession session) {
	// nop
	}

	@Override
	public void onSleep(WebSession session, long millis) {
		Utl.sleep(millis);
	}

	// --- helper ---

	private void assertProjectSelected(WebSession session) {
		if (session.getProject() == null) throw new RuntimeException("No project selected.");
	}

	@Override
	protected Class<? extends AWebApplication> getWebApplicationClass() {
		return ScrumWebApplication.class;
	}

}
