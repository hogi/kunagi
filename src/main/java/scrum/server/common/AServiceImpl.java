package scrum.server.common;

import ilarkesto.di.app.WebApplicationStarter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import scrum.server.ScrumWebApplication;
import scrum.server.SessionData;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Base class for GWT service implementations.
 */
public abstract class AServiceImpl extends RemoteServiceServlet {

	private static final String SESSION_DATA_KEY = SessionData.class.getSimpleName();

	private ScrumWebApplication app;

	protected SessionData getSessionData() {
		SessionData data = (SessionData) getThreadLocalRequest().getSession().getAttribute(SESSION_DATA_KEY);
		if (data == null) {
			data = new SessionData();
			getThreadLocalRequest().getSession().setAttribute(SESSION_DATA_KEY, data);
		}
		return data;
	}

	protected ScrumWebApplication getApp() {
		return app;
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		app = (ScrumWebApplication) WebApplicationStarter
				.startWebApplication(ScrumWebApplication.class.getName(), null);
	}

}
