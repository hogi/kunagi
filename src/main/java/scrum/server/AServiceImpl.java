package scrum.server;

import ilarkesto.base.Str;
import ilarkesto.di.app.WebApplicationStarter;
import ilarkesto.logging.Logger;
import ilarkesto.persistence.AEntity;
import ilarkesto.persistence.DaoService;
import ilarkesto.persistence.EntityUtils;
import ilarkesto.webapp.AWebApplication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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
	private Set<SessionData> sessions = new HashSet<SessionData>();

	protected final void handleServiceMethodException(String method, Throwable t) {
		LOG.error("Service method failed:", method, "->", t);
		getSessionData().getNextData().errors.add("Server error:" + Str.getRootCauseMessage(t));
	}

	protected final void onServiceMethodExecuted() {
		getApp().getTransactionService().commit();
	}

	protected final SessionData getSessionData() {
		SessionData data = (SessionData) getThreadLocalRequest().getSession().getAttribute(SESSION_DATA_KEY);
		if (data == null) {
			LOG.info("Session created");
			data = new SessionData();
			getThreadLocalRequest().getSession().setAttribute(SESSION_DATA_KEY, data);
			sessions.add(data);
			data.getNextData().entityIdBase = UUID.randomUUID().toString();
		}
		return data;
	}

	protected final ScrumWebApplication getApp() {
		return app;
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		app = (ScrumWebApplication) WebApplicationStarter
				.startWebApplication(ScrumWebApplication.class.getName(), null);
		app.autowire(this);
	}

	@Override
	protected void doUnexpectedFailure(Throwable e) {
		LOG.error("Service execution failed:", e);
		super.doUnexpectedFailure(e);
	}

	@Override
	public void destroy() {
		AWebApplication.get().shutdown();
		super.destroy();
	}

	protected DaoService getDaoService() {
		return app.getDaoService();
	}

	protected final List<SessionData> getOtherSessions(SessionData session) {
		List<SessionData> ret = new ArrayList<SessionData>(sessions);
		ret.remove(session);
		return ret;
	}

	protected final Map toPropertyMap(AEntity entity) {
		return entity.createPropertiesMap();
	}

	protected final List<Map> toPropertyMap(Collection<? extends AEntity> entities) {
		return EntityUtils.createPropertiesMaps(entities);
	}

}
