/*
 * Copyright 2008, 2009, 2010 Witoslaw Koczewski, Artjom Kochtchi, Fabian Hager, Kacper Grubalski.
 * 
 * This file is part of Kunagi.
 * 
 * Kunagi is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Kunagi is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Foobar. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package scrum.server;

import ilarkesto.base.Tm;
import ilarkesto.base.Url;
import ilarkesto.base.time.Time;
import ilarkesto.concurrent.TaskManager;
import ilarkesto.core.logging.Log;
import ilarkesto.di.app.WebApplicationStarter;
import ilarkesto.fp.FP;
import ilarkesto.fp.Function;
import ilarkesto.gwt.server.AGwtConversation;
import ilarkesto.io.IO;
import ilarkesto.webapp.AWebApplication;
import ilarkesto.webapp.AWebSession;
import ilarkesto.webapp.DestroyTimeoutedSessionsTask;
import ilarkesto.webapp.Servlet;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import scrum.client.ApplicationInfo;
import scrum.client.UsersStatusData;
import scrum.client.admin.SystemMessage;
import scrum.server.admin.SystemConfig;
import scrum.server.admin.User;
import scrum.server.admin.UserDao;
import scrum.server.common.BurndownChart;
import scrum.server.project.Project;

public class ScrumWebApplication extends GScrumWebApplication {

	private static final Log LOG = Log.get(ScrumWebApplication.class);

	private BurndownChart burndownChart;
	private ScrumConfig config;
	private ScrumEntityfilePreparator entityfilePreparator;
	private ApplicationInfo applicationInfo;
	private SystemMessage systemMessage;

	// --- composites ---

	public BurndownChart getBurndownChart() {
		if (burndownChart == null) {
			burndownChart = new BurndownChart();
			burndownChart.setSprintDao(getSprintDao());
			burndownChart.setProjectDao(getProjectDao());
		}
		return burndownChart;
	}

	public SystemConfig getSystemConfig() {
		return getSystemConfigDao().getSystemConfig();
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
			applicationInfo = new ApplicationInfo("kunagi", getBuild(), getDeploymentStage());
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

		Log.setDebugEnabled(true); // TODO remove this for production

		if (getUserDao().getEntities().isEmpty()) {
			LOG.warn("No users. Creating initial user: admin");
			getUserDao().postUser("admin").setAdmin(true);
			getTransactionService().commit();
		}

		getProjectDao().scanFiles();
		getTransactionService().commit();

		// test data
		if (getProjectDao().getEntities().isEmpty()) createTestData();
	}

	private void createTestData() {
		LOG.warn("Creating test data");

		getUserDao().postUser("homer");
		getUserDao().postUser("cartman");
		getUserDao().postUser("duke");
		getUserDao().postUser("spinne");
		getProjectDao().postExampleProject(getUserDao().getUserByName("admin"));

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

	public Set<GwtConversation> getConversationsByProject(Project project, GwtConversation exception) {
		Set<GwtConversation> ret = new HashSet<GwtConversation>();
		for (Object element : getGwtConversations()) {
			if (element == exception) continue;
			GwtConversation conversation = (GwtConversation) element;
			if (project.equals(conversation.getProject())) ret.add(conversation);
		}
		return ret;
	}

	public Set<User> getConversationUsersByProject(Project project) {
		Set<User> ret = new HashSet<User>();
		for (GwtConversation conversation : getConversationsByProject(project, null)) {
			User user = conversation.getSession().getUser();
			if (user != null) ret.add(user);
		}
		return ret;
	}

	public void updateOnlineTeamMembers(Project project, GwtConversation exclude) {
		if (project == null) return;
		Set<User> users = getConversationUsersByProject(project);
		Set<String> userIds = new HashSet<String>(FP.foreach(users, new Function<User, String>() {

			public String eval(User user) {
				return user.getId();
			}
		}));
		project.getUsersStatus().setOnlineUsers(userIds);
		LOG.debug("Updated online team members on project:", project, "->", project.getUsersStatus());
		sendUsersStatusToClients(project, exclude);
	}

	private void sendUsersStatusToClients(Project project, GwtConversation exclude) {
		UsersStatusData status = project.getUsersStatus();
		for (GwtConversation conversation : getConversationsByProject(project, exclude)) {
			conversation.getNextData().usersStatus = status;
		}
	}

	public void setUsersSelectedEntities(Project project, GwtConversation conversation, Set<String> ids) {
		UsersStatusData usersStatus = project.getUsersStatus();
		WebSession session = conversation.getSession();
		User user = session.getUser();
		String userId = user.getId();
		usersStatus.setUsersSelectedEntities(userId, ids);
		sendUsersStatusToClients(project, conversation);
	}

	@Override
	protected AWebSession createWebSession(HttpServletRequest httpRequest) {
		WebSession session = new WebSession(context, httpRequest);
		autowire(session);
		return session;
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

	public void updateSystemMessage(SystemMessage systemMessage) {
		this.systemMessage = systemMessage;
		for (AGwtConversation conversation : getGwtConversations()) {
			LOG.debug("Sending SystemMessage to:", conversation);
			((GwtConversation) conversation).getNextData().systemMessage = systemMessage;
		}
	}

	public SystemMessage getSystemMessage() {
		return systemMessage;
	}

	public static ScrumWebApplication get() {
		return (ScrumWebApplication) AWebApplication.get();
	}

	public static synchronized ScrumWebApplication get(ServletConfig servletConfig) {
		if (AWebApplication.isStarted()) return get();
		return (ScrumWebApplication) WebApplicationStarter.startWebApplication(ScrumWebApplication.class.getName(),
			Servlet.getContextPath(servletConfig));
	}

}
