package scrum.server;

import ilarkesto.base.Tm;
import ilarkesto.base.Url;
import ilarkesto.base.Utl;
import ilarkesto.concurrent.TaskManager;
import ilarkesto.io.IO;
import ilarkesto.logging.Logger;
import ilarkesto.webapp.AWebApplication;
import ilarkesto.webapp.AWebSession;
import ilarkesto.webapp.DestroyTimeoutedSessionsTask;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import scrum.server.admin.UserDao;
import scrum.server.common.BurndownChart;

public class ScrumWebApplication extends GScrumWebApplication {

	private static final Logger LOG = Logger.get(ScrumWebApplication.class);

	private BurndownChart burndownChart;

	// --- components ---

	public BurndownChart getBurndownChart() {
		if (burndownChart == null) {
			burndownChart = new BurndownChart();
			autowire(burndownChart);
		}
		return burndownChart;
	}

	// --- ---

	@Override
	public void ensureIntegrity() {
		// delete entities
		IO.delete(getApplicationDataDir() + "/entities");

		super.ensureIntegrity();
	}

	@Override
	protected void onStartWebApplication() {
		if (getUserDao().getEntities().isEmpty()) {
			LOG.warn("No users. Creating initial user: admin");
			getUserDao().postUser("admin");
		}

		// test data
		if (getProjectDao().getEntities().isEmpty()) createTestData();
	}

	private void createTestData() {
		LOG.warn("Creating test data");

		getUserDao().postUser("homer");
		getUserDao().postUser("cartman");
		getUserDao().postUser("duke");
		getProjectDao().createTestProject(0);
		getProjectDao().createTestProject(1);

		getTransactionService().commit();
	}

	@Override
	protected void scheduleTasks(TaskManager tm) {
		tm.scheduleWithFixedDelay(autowire(new DestroyTimeoutedSessionsTask()), Tm.MINUTE);
	}

	@Override
	protected void onShutdownWebApplication() {}

	@Override
	public Url getHomeUrl() {
		return new Url("index.html");
	}

	private UserDao userDao;

	public UserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDao();
			autowire(userDao);
		}
		return userDao;
	}

	public Set<WebSession> getOtherSessionsByProject(WebSession currentSession) {
		Set<WebSession> ret = new HashSet<WebSession>();
		for (AWebSession webSession : getWebSessions()) {
			if (webSession == currentSession) continue;
			WebSession session = (WebSession) webSession;
			if (Utl.equals(currentSession.getProject(), session.getProject())) ret.add(session);
		}
		return ret;
	}

	@Override
	protected AWebSession createWebSession(HttpServletRequest httpRequest) {
		return autowire(new WebSession(context, httpRequest));
	}

	public static ScrumWebApplication get() {
		return (ScrumWebApplication) AWebApplication.get();
	}

}
