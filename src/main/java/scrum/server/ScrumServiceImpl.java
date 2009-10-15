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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import scrum.client.admin.SystemMessage;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import scrum.server.admin.User;
import scrum.server.admin.UserDao;
import scrum.server.collaboration.CommentDao;
import scrum.server.common.Numbered;
import scrum.server.common.Transient;
import scrum.server.project.Project;
import scrum.server.project.ProjectDao;
import scrum.server.project.Requirement;
import scrum.server.project.RequirementDao;
import scrum.server.sprint.Sprint;
import scrum.server.sprint.Task;

public class ScrumServiceImpl extends GScrumServiceImpl {

	private static final Logger LOG = Logger.get(ScrumServiceImpl.class);

	// --- dependencies ---

	private ProjectDao projectDao;
	private UserDao userDao;
	private RequirementDao requirementDao;
	private CommentDao commentDao;
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

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	// --- ---

	@Override
	protected void onUpdateSystemMessage(WebSession session, SystemMessage systemMessage) {
		User user = session.getUser();
		if (user == null || user.isAdmin() == false) throw new PermissionDeniedException();
		webApplication.updateSystemMessage(systemMessage);
	}

	private void onStartSession(WebSession session) {
		session.clearRemoteEntities();
		session.getNextData().applicationInfo = webApplication.getApplicationInfo();
		session.getNextData().entityIdBase = UUID.randomUUID().toString();
	}

	@Override
	protected void onSetSelectedEntitysIds(WebSession session, Set ids) {
		webApplication.setUsersSelectedEntities(session.getProject(), session.getUser(), ids);
	}

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

		sendToClients(session, entity);
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
			if (properties.containsKey("description") || properties.containsKey("testDescription")
					|| properties.containsKey("estimatedWork") || properties.containsKey("qualitysIds")) {
				requirement.setDirty(true);
				session.sendToClient(requirement);
			}
			requirement.getProject().getCurrentSprintSnapshot().update();
		}

		if (entity instanceof Project) {
			Project project = (Project) entity;
		}

		sendToOtherSessionsByProject(session, entity);
	}

	@Override
	public void onLogin(WebSession session, String username, String password) {
		session.clearRemoteEntities();
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
		webApplication.updateOnlineTeamMembers(project);

		// prepare data for client
		session.sendToClient(project);
		// session.sendToClient(project.getCurrentSprint());
		// session.sendToClient(project.getNextSprint());
		session.sendToClient(project.getSprints());
		session.sendToClient(project.getParticipants());
		session.sendToClient(project.getRequirements());
		session.sendToClient(project.getQualitys());
		session.sendToClient(project.getTasks());
		session.sendToClient(project.getUserConfigs());
		session.sendToClient(project.getWikipages());
	}

	@Override
	protected void onCloseProject(WebSession session) {
		assertProjectSelected(session);
		Project project = session.getProject();
		webApplication.setUsersSelectedEntities(project, session.getUser(), new HashSet<String>(0));
		session.clearRemoteEntities();

		session.setProject(null);
		if (project != null) webApplication.updateOnlineTeamMembers(project);
	}

	@Override
	protected void onSwitchToNextSprint(WebSession session) {
		assertProjectSelected(session);
		Project project = session.getProject();
		Sprint oldSprint = project.getCurrentSprint();
		for (Requirement requirement : oldSprint.getRequirements()) {
			if (!requirement.isClosed()) {
				requirement.setDirty(true);
				sendToClients(session, requirement);
			}
		}
		project.switchToNextSprint();
		sendToClients(session, project.getCurrentSprint());
		sendToClients(session, project.getNextSprint());
		sendToClients(session, project);
	}

	@Override
	public void onRequestImpediments(WebSession session) {
		assertProjectSelected(session);
		Project project = session.getProject();
		session.sendToClient(project.getImpediments());
	}

	@Override
	protected void onRequestIssues(WebSession session) {
		assertProjectSelected(session);
		Project project = session.getProject();
		session.sendToClient(project.getIssues());
	}

	@Override
	protected void onRequestRisks(WebSession session) {
		assertProjectSelected(session);
		Project project = session.getProject();
		session.sendToClient(project.getRisks());
	}

	@Override
	protected void onRequestEntityByReference(WebSession session, String reference) {
		assertProjectSelected(session);
		Project project = session.getProject();
		int number = Integer.parseInt(reference.substring(scrum.client.project.Requirement.REFERENCE_PREFIX.length()));
		if (reference.startsWith(scrum.client.project.Requirement.REFERENCE_PREFIX)) {
			Requirement requirement = project.getRequirementByNumber(number);
			if (requirement != null) session.sendToClient(requirement);
			return;
		} else if (reference.startsWith(scrum.client.sprint.Task.REFERENCE_PREFIX)) {
			Task task = project.getTaskByNumber(number);
			if (task != null) session.sendToClient(task);
			return;
		}
		LOG.info("Requested entity not found:", reference);
	}

	@Override
	protected void onRequestComments(WebSession session, String parentId) {
		session.sendToClient(commentDao.getCommentsByParentId(parentId));
	}

	@Override
	public void onPing(WebSession session) {
	// nop
	}

	@Override
	public void onSleep(WebSession session, long millis) {
		Utl.sleep(millis);
	}

	public scrum.client.DataTransferObject startSession() {
		LOG.debug("startSession");
		WebSession session = (WebSession) getSession();
		ilarkesto.di.Context context = ilarkesto.di.Context.get();
		context.setName("gwt-srv:startSession");
		context.bindCurrentThread();
		try {
			onStartSession(session);
		} catch (Throwable t) {
			handleServiceMethodException("startSession", t);
			throw new RuntimeException(t);
		}
		scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) session.popNextData();
		onServiceMethodExecuted(context);
		return ret;
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		ScrumWebApplication.get(servletConfig);
		super.init(servletConfig);
	}

	// --- helper ---

	private void sendToClients(WebSession session, AEntity entity) {
		session.sendToClient(entity);
		sendToOtherSessionsByProject(session, entity);
	}

	private void sendToOtherSessionsByProject(WebSession session, AEntity entity) {
		for (AWebSession s : webApplication.getOtherSessionsByProject(session)) {
			s.sendToClient(entity);
		}
	}

	private void assertProjectSelected(WebSession session) {
		if (session.getProject() == null) throw new RuntimeException("No project selected.");
	}

	@Override
	protected Class<? extends AWebApplication> getWebApplicationClass() {
		return ScrumWebApplication.class;
	}

}
