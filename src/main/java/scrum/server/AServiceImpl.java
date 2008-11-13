package scrum.server;

import ilarkesto.base.Str;
import ilarkesto.di.app.WebApplicationStarter;
import ilarkesto.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Base class for GWT service implementations.
 */
public abstract class AServiceImpl extends RemoteServiceServlet {

	private static final Logger LOG = Logger.get(AServiceImpl.class);

	private static final String SESSION_DATA_KEY = SessionData.class.getSimpleName();

	private ScrumWebApplication app;

	protected final void handleServiceMethodException(String method, Throwable t) {
		LOG.error("Service method failed:", method, "->", t);
		getSessionData().getNextData().errors.add("Server error:" + Str.getRootCauseMessage(t));
	}

	protected final void onServiceMethodExecuted() {
		getApp().getTransactionService().commit();
	}

	protected SessionData getSessionData() {
		SessionData data = (SessionData) getThreadLocalRequest().getSession().getAttribute(SESSION_DATA_KEY);
		if (data == null) {
			data = new SessionData();
			getThreadLocalRequest().getSession().setAttribute(SESSION_DATA_KEY, data);
			getApp().onSessionCreated(data);
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
