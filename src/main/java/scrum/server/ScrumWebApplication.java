package scrum.server;

import ilarkesto.base.Tm;
import ilarkesto.base.Url;
import ilarkesto.base.Utl;
import ilarkesto.base.time.Time;
import ilarkesto.concurrent.TaskManager;
import ilarkesto.fp.FP;
import ilarkesto.fp.Function;
import ilarkesto.io.IO;
import ilarkesto.logging.Logger;
import ilarkesto.webapp.AWebApplication;
import ilarkesto.webapp.AWebSession;
import ilarkesto.webapp.DestroyTimeoutedSessionsTask;

import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import scrum.client.ApplicationInfo;
import scrum.server.admin.User;
import scrum.server.admin.UserDao;
import scrum.server.common.BurndownChart;
import scrum.server.project.Project;

public class ScrumWebApplication extends GScrumWebApplication {

	private static final Logger LOG = Logger.get(ScrumWebApplication.class);

	private BurndownChart burndownChart;
	private ScrumConfig config;
	private ScrumEntityfilePreparator entityfilePreparator;
	private ApplicationInfo applicationInfo;

	// --- composites ---

	public BurndownChart getBurndownChart() {
		if (burndownChart == null) {
			burndownChart = new BurndownChart();
			autowire(burndownChart);
		}
		return burndownChart;
	}

	public ScrumConfig getConfig() {
		if (config == null) {
			config = new ScrumConfig(getApplicationDataDir());
		}
		return config;
	}

	public ScrumEntityfilePreparator getEntityfilePreparator() {
		if (entityfilePreparator == null) {
			entityfilePreparator = new ScrumEntityfilePreparator();
			entityfilePreparator.setBackupDir(getApplicationDataDir() + "/backup/entities");
		}
		return entityfilePreparator;
	}

	public ApplicationInfo getApplicationInfo() {
		if (applicationInfo == null) {
			applicationInfo = new ApplicationInfo("Scrum42", getBuild(), getDeploymentStage());
		}
		return applicationInfo;
	}

	// --- ---

	@Override
	public void ensureIntegrity() {
		if (getConfig().isStartupDelete()) {
			LOG.warn("DELETING ALL ENTITIES (set startup.delete=false in config.properties to prevent this behavior)");
			IO.delete(getApplicationDataDir() + "/entities");
		}

		super.ensureIntegrity();
	}

	@Override
	protected void onStartWebApplication() {
		if (getUserDao().getEntities().isEmpty()) {
			LOG.warn("No users. Creating initial user: admin");
			getUserDao().postUser("admin").setAdmin(true);
			getTransactionService().commit();
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
		Project project = currentSession.getProject();
		if (project == null) return Collections.emptySet();
		Set<WebSession> ret = getSessionsByProject(project);
		ret.remove(currentSession);
		return ret;
	}

	public Set<WebSession> getSessionsByProject(Project project) {
		Set<WebSession> ret = new HashSet<WebSession>();
		for (AWebSession webSession : getWebSessions()) {
			if (webSession.isSessionInvalidated()) continue;
			WebSession session = (WebSession) webSession;
			if (Utl.equals(project, session.getProject())) ret.add(session);
		}
		return ret;
	}

	public Set<User> getSessionUsersByProject(Project project) {
		Set<User> ret = new HashSet<User>();
		for (WebSession session : getSessionsByProject(project)) {
			ret.add(session.getUser());
		}
		return ret;
	}

	public void updateOnlineTeamMembers(Project project) {
		if (project == null) return;
		Set<User> users = getSessionUsersByProject(project);
		LOG.debug("Uupdate online team members on project:", project, "->", users);
		Set<String> userIds = new HashSet<String>(FP.foreach(users, new Function<User, String>() {

			public String eval(User user) {
				return user.getId();
			}
		}));
		for (WebSession session : getSessionsByProject(project)) {
			session.getNextData().onlineTeamMembersIds = userIds;
		}
	}

	@Override
	protected AWebSession createWebSession(HttpServletRequest httpRequest) {
		return autowire(new WebSession(context, httpRequest));
	}

	public String getDeploymentStage() {
		if (isDevelopmentMode()) return ApplicationInfo.DEPLOYMENT_STAGE_DEVELOPMENT;
		if (getConfig().isStageIntegration()) return ApplicationInfo.DEPLOYMENT_STAGE_INTEGRATION;
		return ApplicationInfo.DEPLOYMENT_STAGE_PRODUCTION;
	}

	public String getBuild() {
		Properties properties = IO.loadPropertiesFromClasspath("scrum/server/build.properties");
		String date = properties.getProperty("date");
		if ("@build-date@".equals(date)) date = Time.now().toString();
		return date;
	}

	public static ScrumWebApplication get() {
		return (ScrumWebApplication) AWebApplication.get();
	}

}
