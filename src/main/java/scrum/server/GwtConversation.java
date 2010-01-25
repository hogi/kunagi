package scrum.server;

import ilarkesto.gwt.server.AGwtConversation;
import ilarkesto.logging.Logger;

import java.util.HashSet;

import scrum.client.DataTransferObject;
import scrum.server.project.Project;

public class GwtConversation extends AGwtConversation {

	private static final Logger LOG = Logger.get(GwtConversation.class);

	private Project project;

	public GwtConversation(WebSession session, int number) {
		super(session, number);
	}

	public void invalidate() {
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

}
