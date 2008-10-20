package scrum.server;

import ilarkesto.logging.Logger;
import scrum.client.service.ServerData;
import scrum.client.service.ScrumService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ScrumServiceImpl extends RemoteServiceServlet implements ScrumService {

	private static final Logger LOG = Logger.get(ScrumServiceImpl.class);
	private static final String SESSION_DATA_KEY = SessionData.class.getSimpleName();

	private static ScrumServer scrumServer = ScrumServer.get();

	public ServerData getProject(String projectId) {
		SessionData session = getSessionData();
		scrumServer.onProjectRequested(session, projectId);
		return session.popNextData();
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
