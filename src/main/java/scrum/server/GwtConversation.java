package scrum.server;

import ilarkesto.base.time.TimePeriod;
import ilarkesto.core.logging.Log;
import ilarkesto.gwt.server.AGwtConversation;
import ilarkesto.persistence.AEntity;

import java.util.HashSet;

import scrum.client.DataTransferObject;
import scrum.client.communication.Pinger;
import scrum.server.admin.User;
import scrum.server.collaboration.Emoticon;
import scrum.server.collaboration.EmoticonDao;
import scrum.server.project.Project;

public class GwtConversation extends AGwtConversation {

	private static final Log LOG = Log.get(GwtConversation.class);
	private TimePeriod TIMEOUT = new TimePeriod(Pinger.MAX_DELAY * 10);

	private Project project;

	// --- dependencies ---

	private EmoticonDao emoticonDao;

	public void setEmoticonDao(EmoticonDao emoticonDao) {
		this.emoticonDao = emoticonDao;
	}

	// --- ---

	public GwtConversation(WebSession session, int number) {
		super(session, number);
	}

	public void sendUserScopeDataToClient(User user) {
		getNextData().setUserId(user.getId());
		ScrumWebApplication app = ScrumWebApplication.get();
		getNextData().systemMessage = app.getSystemMessage();
		sendToClient(user);
		sendToClient(app.getProjectDao().getEntitiesVisibleForUser(user)); // all projects
		sendToClient(app.getUserDao().getEntitiesVisibleForUser(user)); // all users
	}

	@Override
	public synchronized void sendToClient(AEntity entity) {
		super.sendToClient(entity);
		for (Emoticon emoticon : emoticonDao.getEmoticonsByParent(entity)) {
			super.sendToClient(emoticon);
		}
	}

	@Override
	public void invalidate() {
		super.invalidate();
		ScrumWebApplication.get().updateOnlineTeamMembers(getProject(), this);
		if (getSession().getUser() != null && project != null)
			ScrumWebApplication.get().setUsersSelectedEntities(project, this, new HashSet<String>(0));
	}

	@Override
	protected DataTransferObject createDataTransferObject() {
		return new DataTransferObject();
	}

	@Override
	public WebSession getSession() {
		return (WebSession) super.getSession();
	}

	@Override
	public DataTransferObject getNextData() {
		return (DataTransferObject) super.getNextData();
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		LOG.info("Project selected:", project);
		this.project = project;
	}

	@Override
	protected TimePeriod getTimeout() {
		return TIMEOUT;
	}

}
