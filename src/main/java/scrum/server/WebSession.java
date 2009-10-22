package scrum.server;

import ilarkesto.base.time.TimePeriod;
import ilarkesto.di.Context;
import ilarkesto.logging.Logger;
import ilarkesto.webapp.AWebSession;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import scrum.client.DataTransferObject;
import scrum.client.Pinger;
import scrum.server.admin.User;
import scrum.server.project.Project;

public class WebSession extends AWebSession {

	private static final Logger LOG = Logger.get(WebSession.class);

	private TimePeriod TIMEOUT = new TimePeriod(Pinger.MAX_DELAY * 2);
	private User user;
	private Project project;

	public WebSession(Context parentContext, HttpServletRequest initialRequest) {
		super(parentContext, initialRequest);
	}

	public Project getProject() {
		return project;
	}

	public void setUser(User user) {
		LOG.info("User set:", user);
		this.user = user;
		getContext().setName(toString());
	}

	public User getUser() {
		return user;
	}

	public void setProject(Project project) {
		LOG.info("Project selected:", project);
		this.project = project;
	}

	@Override
	protected void onInvalidate() {
		ScrumWebApplication.get().updateOnlineTeamMembers(getProject(), this);
		if (user != null && project != null)
			ScrumWebApplication.get().setUsersSelectedEntities(project, this, new HashSet<String>(0));

		setUser(null);
		setProject(null);
		super.onInvalidate();
	}

	@Override
	public DataTransferObject getNextData() {
		return (DataTransferObject) super.getNextData();
	}

	@Override
	protected DataTransferObject createDataTransferObject() {
		return new DataTransferObject();
	}

	@Override
	protected TimePeriod getTimeout() {
		return TIMEOUT;
	}

	@Override
	public String toString() {
		return user == null ? super.toString() : "session:" + user;
	}

}
