package scrum.server;

import ilarkesto.di.app.WebApplicationStarter;
import ilarkesto.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import scrum.client.service.ScrumService;
import scrum.client.service.ServerData;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ScrumServiceImpl extends RemoteServiceServlet implements ScrumService {

	private static final Logger LOG = Logger.get(ScrumServiceImpl.class);
	private static final String SESSION_DATA_KEY = SessionData.class.getSimpleName();

	private ScrumWebApplication app;

	public ServerData getProject(String projectId) {
		SessionData session = getSessionData();
		app.onProjectRequested(session, projectId);
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

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		app = (ScrumWebApplication) WebApplicationStarter
				.startWebApplication(ScrumWebApplication.class.getName(), null);
	}

}
