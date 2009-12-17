package scrum.server;

import ilarkesto.auth.Auth;
import ilarkesto.base.PermissionDeniedException;
import ilarkesto.base.Utl;
import ilarkesto.base.time.Date;
import ilarkesto.base.time.DateAndTime;
import ilarkesto.logging.Logger;
import ilarkesto.persistence.ADao;
import ilarkesto.persistence.AEntity;
import ilarkesto.webapp.AWebApplication;
import ilarkesto.webapp.AWebSession;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import scrum.client.admin.SystemMessage;
import scrum.server.admin.User;
import scrum.server.admin.UserDao;
import scrum.server.collaboration.ChatMessage;
import scrum.server.collaboration.Comment;
import scrum.server.collaboration.CommentDao;
import scrum.server.common.Numbered;
import scrum.server.common.Transient;
import scrum.server.files.File;
import scrum.server.impediments.Impediment;
import scrum.server.journal.ProjectEvent;
import scrum.server.journal.ProjectEventDao;
import scrum.server.project.Project;
import scrum.server.project.ProjectDao;
import scrum.server.project.Requirement;
import scrum.server.project.RequirementDao;
import scrum.server.sprint.Sprint;
import scrum.server.sprint.Task;

public class ScrumServiceImpl extends GScrumServiceImpl {

	private static final Logger LOG = Logger.get(ScrumServiceImpl.class);
	private static final long serialVersionUID = 1;

	// --- dependencies ---

	private transient ProjectDao projectDao;
	private transient UserDao userDao;
	private transient RequirementDao requirementDao;
	private transient CommentDao commentDao;
	private transient ScrumWebApplication webApplication;
	private transient ProjectEventDao projectEventDao;

	public void setProjectEventDao(ProjectEventDao projectEventDao) {
		this.projectEventDao = projectEventDao;
	}

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
	protected void onSearch(GwtConversation conversation, String text) {
		Project project = conversation.getProject();
		if (project == null) return;
		List<AEntity> foundEntities = project.search(text);
		LOG.debug("Found entities for search", "\"" + text + "\"", "->", foundEntities);
		conversation.sendToClient(foundEntities);
	}

	@Override
	protected void onUpdateSystemMessage(GwtConversation conversation, SystemMessage systemMessage) {
		User user = conversation.getSession().getUser();
		if (user == null || user.isAdmin() == false) throw new PermissionDeniedException();
		webApplication.updateSystemMessage(systemMessage);
	}

	private void onStartSession(GwtConversation conversation) {
		conversation.clearRemoteEntities();
		conversation.getNextData().applicationInfo = webApplication.getApplicationInfo();
		conversation.getNextData().entityIdBase = UUID.randomUUID().toString();
	}

	@Override
	protected void onSetSelectedEntitysIds(GwtConversation conversation, Set ids) {
		webApplication.setUsersSelectedEntities(conversation.getProject(), conversation, ids);
	}

	@Override
	protected void onLogout(GwtConversation conversation) {
		WebSession session = conversation.getSession();
		session.invalidate();
		webApplication.destroyWebSession(session, getThreadLocalRequest().getSession());
	}

	@Override
	protected void onResetPassword(GwtConversation conversation, String userId) {
		User user = userDao.getById(userId);
		user.setPassword("geheim");
	}

	@Override
	protected void onChangePassword(GwtConversation conversation, String oldPassword, String newPassword) {
		User user = conversation.getSession().getUser();
		if (user.matchesPassword(oldPassword) == false) { throw new RuntimeException("Wrong password"); }

		user.setPassword(newPassword);

		LOG.info("password changed by user");
	}

	@Override
	public void onCreateEntity(GwtConversation conversation, String type, Map properties) {
		String id = (String) properties.get("id");
		if (id == null) throw new NullPointerException("id == null");

		ADao dao = getDaoService().getDaoByName(type);
		AEntity entity = dao.newEntityInstance(id);
		entity.updateProperties(properties);

		if (entity instanceof Numbered) {
			((Numbered) entity).updateNumber();
		}

		if (entity instanceof Comment) {
			Comment comment = (Comment) entity;
			comment.setDateAndTime(DateAndTime.now());
			postProjectEvent(conversation, comment.getAuthor().getName() + " commented on " + comment.getParent());
		}

		if (entity instanceof ChatMessage) {
			ChatMessage chatMessage = (ChatMessage) entity;
			chatMessage.setDateAndTime(DateAndTime.now());
		}

		if (entity instanceof Impediment) {
			Impediment impediment = (Impediment) entity;
			impediment.setDate(Date.today());
		}

		if (!(entity instanceof Transient)) dao.saveEntity(entity);

		sendToClients(conversation, entity);
	}

	@Override
	public void onDeleteEntity(GwtConversation conversation, String entityId) {
		AEntity entity = getDaoService().getEntityById(entityId);
		if (!Auth.isDeletable(entity, conversation.getSession().getUser())) throw new PermissionDeniedException();

		if (entity instanceof File) {
			File file = (File) entity;
			file.deleteFile();
		}

		ADao dao = getDaoService().getDao(entity);
		dao.deleteEntity(entity);
		for (WebSession s : webApplication.getOtherSessionsByProject(conversation.getSession())) {
			// TODO do this only if client is tracking this entity
			s.getGwtConversation().getNextData().addDeletedEntity(entityId);
		}
	}

	@Override
	public void onChangeProperties(GwtConversation conversation, String entityId, Map properties) {
		AEntity entity = getDaoService().getEntityById(entityId);
		if (!Auth.isEditable(entity, conversation.getSession().getUser())) throw new PermissionDeniedException();

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

			if (task.isClosed() && properties.containsKey("remainingWork")) {
				String event = task.getReferenceAndLabel() + " closed by " + task.getOwner().getName();
				if (task.getRequirement().isTasksClosed()) {
					event += ", all tasks in " + task.getRequirement().getReferenceAndLabel() + " are closed";
				}
				postProjectEvent(conversation, event);
			} else if (task.isOwnerSet() && properties.containsKey("ownerId")) {
				postProjectEvent(conversation, task.getReferenceAndLabel() + " claimed by " + task.getOwner().getName());
			}
			if (!task.isOwnerSet() && properties.containsKey("ownerId")) {
				postProjectEvent(conversation, task.getReferenceAndLabel() + " unclaimed");
			}
		}

		if (entity instanceof Requirement) {
			Requirement requirement = (Requirement) entity;
			Sprint sprint = requirement.getSprint();
			boolean inCurrentSprint = sprint != null && conversation.getProject().isCurrentSprint(sprint);
			if (properties.containsKey("description") || properties.containsKey("testDescription")
					|| properties.containsKey("estimatedWork") || properties.containsKey("qualitysIds")) {
				requirement.setDirty(true);
				conversation.sendToClient(requirement);
			}
			if (properties.containsKey("sprintId")) {
				if (inCurrentSprint) {
					postProjectEvent(conversation, conversation.getSession().getUser().getName() + " pulled "
							+ requirement.getReferenceAndLabel() + " to current sprint");
				} else {
					postProjectEvent(conversation, conversation.getSession().getUser().getName() + " kicked "
							+ requirement.getReferenceAndLabel() + " from current sprint");
				}
			}

			requirement.getProject().getCurrentSprintSnapshot().update();
		}

		if (entity instanceof Project) {
			Project project = (Project) entity;
		}

		if (entity instanceof Impediment) {
			Impediment impediment = (Impediment) entity;
			if (impediment.isClosed() && properties.containsKey("closed")) {
				postProjectEvent(conversation, impediment.getReferenceAndLabel() + " closed");
			}
		}

		sendToOtherSessionsByProject(conversation, entity);
	}

	@Override
	public void onLogin(GwtConversation conversation, String username, String password) {
		conversation.clearRemoteEntities();
		User user = userDao.getUserByName(username);

		if (user == null || user.matchesPassword(password) == false) {
			conversation.getNextData().errors.add("Login failed.");
			return;
		}

		conversation.getSession().setUser(user);
		conversation.getNextData().entityIdBase = UUID.randomUUID().toString();
		conversation.getNextData().setUserId(user.getId());
		conversation.getNextData().systemMessage = webApplication.getSystemMessage();
		conversation.sendToClient(user);
		conversation.sendToClient(projectDao.getEntitiesVisibleForUser(user));
		conversation.sendToClient(userDao.getEntitiesVisibleForUser(user));
	}

	@Override
	public void onSelectProject(GwtConversation conversation, String projectId) {
		Project project = projectDao.getById(projectId);
		User user = conversation.getSession().getUser();
		if (!project.isVisibleFor(user))
			throw new RuntimeException("Project '" + project + "' is not visible for user '" + user + "'");
		conversation.setProject(project);
		user.setCurrentProject(project);
		webApplication.updateOnlineTeamMembers(project, conversation);

		// prepare data for client
		conversation.sendToClient(project);
		// session.sendToClient(project.getCurrentSprint());
		// session.sendToClient(project.getNextSprint());
		conversation.sendToClient(project.getSprints());
		conversation.sendToClient(project.getParticipants());
		Set<Requirement> requirements = project.getRequirements();
		conversation.sendToClient(requirements);
		for (Requirement requirement : requirements) {
			conversation.sendToClient(requirement.getEstimationVotes());
		}
		conversation.sendToClient(project.getQualitys());
		conversation.sendToClient(project.getTasks());
		conversation.sendToClient(project.getUserConfigs());
		conversation.sendToClient(project.getWikipages());
		conversation.sendToClient(project.getImpediments());
		conversation.sendToClient(project.getRisks());
		conversation.sendToClient(project.getProjectEvents());
		conversation.sendToClient(project.getCalendarEvents());
		conversation.sendToClient(project.getFiles());
	}

	@Override
	protected void onCloseProject(GwtConversation conversation) {
		Project project = conversation.getProject();
		if (project != null) webApplication.setUsersSelectedEntities(project, conversation, new HashSet<String>(0));
		conversation.clearRemoteEntities();
		conversation.setProject(null);
		if (project != null) webApplication.updateOnlineTeamMembers(project, conversation);
	}

	@Override
	protected void onSwitchToNextSprint(GwtConversation conversation) {
		assertProjectSelected(conversation);
		Project project = conversation.getProject();
		Sprint oldSprint = project.getCurrentSprint();
		for (Requirement requirement : oldSprint.getRequirements()) {
			if (!requirement.isClosed()) {
				requirement.setDirty(true);
				sendToClients(conversation, requirement);
			}
		}
		project.switchToNextSprint();
		sendToClients(conversation, project.getSprints());
		sendToClients(conversation, project);
	}

	@Override
	protected void onRequestRequirementEstimationVotes(GwtConversation conversation, String requirementId) {
		assertProjectSelected(conversation);
		Project project = conversation.getProject();
		Requirement requirement = requirementDao.getById(requirementId);
		if (!requirement.isProject(project)) throw new PermissionDeniedException();
		conversation.sendToClient(requirement.getEstimationVotes());
	}

	@Override
	public void onRequestImpediments(GwtConversation conversation) {
		assertProjectSelected(conversation);
		Project project = conversation.getProject();
		conversation.sendToClient(project.getImpediments());
	}

	@Override
	protected void onRequestIssues(GwtConversation conversation) {
		assertProjectSelected(conversation);
		Project project = conversation.getProject();
		conversation.sendToClient(project.getIssues());
	}

	@Override
	protected void onRequestRisks(GwtConversation conversation) {
		assertProjectSelected(conversation);
		Project project = conversation.getProject();
		conversation.sendToClient(project.getRisks());
	}

	@Override
	protected void onRequestEntityByReference(GwtConversation conversation, String reference) {
		assertProjectSelected(conversation);
		Project project = conversation.getProject();
		int number = Integer.parseInt(reference.substring(scrum.client.project.Requirement.REFERENCE_PREFIX.length()));
		if (reference.startsWith(scrum.client.project.Requirement.REFERENCE_PREFIX)) {
			conversation.sendToClient(project.getRequirementByNumber(number));
			return;
		} else if (reference.startsWith(scrum.client.project.Quality.REFERENCE_PREFIX)) {
			conversation.sendToClient(project.getQualityByNumber(number));
			return;
		} else if (reference.startsWith(scrum.client.sprint.Task.REFERENCE_PREFIX)) {
			conversation.sendToClient(project.getTaskByNumber(number));
			return;
		} else if (reference.startsWith(scrum.client.impediments.Impediment.REFERENCE_PREFIX)) {
			conversation.sendToClient(project.getImpedimentByNumber(number));
			return;
		} else if (reference.startsWith(scrum.client.issues.Issue.REFERENCE_PREFIX)) {
			conversation.sendToClient(project.getIssueByNumber(number));
			return;
		} else if (reference.startsWith(scrum.client.files.File.REFERENCE_PREFIX)) {
			conversation.sendToClient(project.getFileByNumber(number));
			return;
		}
		LOG.info("Requested entity not found:", reference);
	}

	@Override
	protected void onRequestComments(GwtConversation conversation, String parentId) {
		conversation.sendToClient(commentDao.getCommentsByParentId(parentId));
	}

	@Override
	public void onPing(GwtConversation conversation) {
	// nop
	}

	@Override
	public void onSleep(GwtConversation conversation, long millis) {
		Utl.sleep(millis);
	}

	public scrum.client.DataTransferObject startSession() {
		LOG.debug("startSession");
		WebSession session = (WebSession) getSession();
		GwtConversation conversation = session.getGwtConversation();
		ilarkesto.di.Context context = ilarkesto.di.Context.get();
		context.setName("gwt-srv:startSession");
		context.bindCurrentThread();
		try {
			onStartSession(conversation);
		} catch (Throwable t) {
			handleServiceMethodException("startSession", t);
			throw new RuntimeException(t);
		}
		scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
		onServiceMethodExecuted(context);
		return ret;
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		ScrumWebApplication.get(servletConfig);
		super.init(servletConfig);
	}

	// --- helper ---

	private void postProjectEvent(GwtConversation conversation, String label) {
		assertProjectSelected(conversation);
		ProjectEvent event = projectEventDao.postEvent(conversation.getProject(), label);
		sendToClients(conversation, event);
		sendToClients(conversation, event.createChatMessage());
	}

	private void sendToClients(GwtConversation conversation, Collection<? extends AEntity> entities) {
		for (AEntity entity : entities) {
			sendToClients(conversation, entity);
		}
	}

	private void sendToClients(GwtConversation conversation, AEntity entity) {
		conversation.sendToClient(entity);
		sendToOtherSessionsByProject(conversation, entity);
	}

	private void sendToOtherSessionsByProject(GwtConversation conversation, AEntity entity) {
		for (AWebSession s : webApplication.getOtherSessionsByProject(conversation.getSession())) {
			s.getGwtConversation().sendToClient(entity);
		}
	}

	private void assertProjectSelected(GwtConversation conversation) {
		if (conversation.getProject() == null) throw new RuntimeException("No project selected.");
	}

	@Override
	protected Class<? extends AWebApplication> getWebApplicationClass() {
		return ScrumWebApplication.class;
	}

}
