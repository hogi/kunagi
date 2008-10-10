package scrum.server;

import ilarkesto.logging.Logger;
import scrum.client.service.ProjectDto;
import scrum.client.service.ScrumService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ScrumServiceImpl extends RemoteServiceServlet implements ScrumService {

	private static final Logger LOG = Logger.get(ScrumServiceImpl.class);
	private static final String SESSION_DATA_KEY = SessionData.class.getSimpleName();

	private DataStore dataStore = DataStore.get();

	public ProjectDto getProject(String projectId) {
		return dataStore.getProject(projectId);
	}

	private SessionData getSessionData() {
		SessionData data = (SessionData) getThreadLocalRequest().getSession().getAttribute(SESSION_DATA_KEY);
		if (data == null) {
			data = new SessionData();
			getThreadLocalRequest().getSession().setAttribute(SESSION_DATA_KEY, data);
		}
		return data;
	}

}
