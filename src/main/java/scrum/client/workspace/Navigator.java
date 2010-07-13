package scrum.client.workspace;

import ilarkesto.core.base.Str;
import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.AGwtEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import scrum.client.admin.User;
import scrum.client.admin.UserLoggedInEvent;
import scrum.client.admin.UserLoggedInHandler;
import scrum.client.core.ApplicationStartedEvent;
import scrum.client.core.ApplicationStartedHandler;
import scrum.client.project.Project;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;

public class Navigator extends GNavigator implements BlockExpandedHandler, UserLoggedInHandler,
		ApplicationStartedHandler {

	private String startToken;
	private String page = "Dashboard";

	@Override
	public void initialize() {
		History.addValueChangeHandler(new ValueChangeHandler<String>() {

			public void onValueChange(ValueChangeEvent<String> event) {
				evalHistoryToken(event.getValue());
			}
		});
	}

	private void evalHistoryToken(String token) {
		log.info("evaluating history token:", token);
		// if (token == null || token.length() == 0) return;
		onHistoryToken(parseHistoryToken(token));
	}

	private void onHistoryToken(final Map<String, String> tokens) {

		if (tokens.containsKey("login")) {
			modeSwitcher.activatePublicMode();
			return;
		}

		if (tokens.containsKey("projectSelector")) {
			modeSwitcher.activateUserMode();
			return;
		}

		String projectId = tokens.get("project");
		if (projectId == null) {
			gotoProjectSelector();
			return;
		}

		Project project = Scope.get().getComponent(Project.class);
		if (project != null && !projectId.equals(project.getId())) {
			project = null;
		}

		if (project == null) {
			project = dao.getProject(projectId);
			if (project == null) throw new RuntimeException("Project does not exist: " + projectId);
			modeSwitcher.acitvateProjectMode(project, tokens.get("page"), tokens.get("entity"));
			return;
		}

		String page = tokens.get("page");
		if (page != null) {
			Scope.get().getComponent(ProjectWorkspaceWidgets.class).showPage(page);
		}

		String entityId = tokens.get("entity");
		if (entityId != null) {
			Scope.get().getComponent(ProjectWorkspaceWidgets.class).showEntityById(entityId);
		}
	}

	public void onApplicationStarted(ApplicationStartedEvent event) {
		User user = auth.getUser();
		String historyToken = History.getToken();
		if (user == null) {
			startToken = Str.isBlank(historyToken) ? null : historyToken;
			modeSwitcher.activatePublicMode();
			return;
		}

		if (historyToken.contains("project=")) {
			evalHistoryToken(historyToken);
		} else {
			gotoUsersStart();
		}
	}

	private void gotoUsersStart() {
		User user = auth.getUser();
		Project project = user.getCurrentProject();
		if (project == null || user.isAdmin()) {
			gotoProjectSelector();
		} else {
			gotoProject(project.getId());
		}
	}

	public void onUserLoggedIn(UserLoggedInEvent event) {
		if (startToken != null) {
			log.info("Activating history start token: " + startToken);
			History.newItem(startToken, true);
			return;
		}
		gotoUsersStart();
	}

	public void gotoProjectSelector() {
		gotoToken("projectSelector");
	}

	public void gotoProject(String projectId) {
		gotoToken("project=" + projectId);
	}

	public void gotoEntity(String entityId) {
		Project project = Scope.get().getComponent(Project.class);
		gotoEntity(project.getId(), entityId);
	}

	public void gotoEntity(String projectId, String entityId) {
		if (entityId == null) {
			gotoProject(projectId);
			return;
		}
		gotoToken("project=" + projectId + "|entity=" + entityId);
	}

	private void gotoToken(String token) {
		History.newItem(token);
	}

	public void setPageToken(String page) {
		if (page.equals(this.page)) return;
		this.page = page;
		if (!History.getToken().contains("page=" + page)) {
			Project project = Scope.get().getComponent(Project.class);
			History.newItem("project=" + project.getId() + "|page=" + page, false);
		}
	}

	public void setToken(AGwtEntity entity) {
		Project project = Scope.get().getComponent(Project.class);
		if (project == null) return;
		History.newItem("project=" + project.getId() + "|page=" + page + "|entity=" + entity.getId(), false);
	}

	public void onBlockExpanded(BlockExpandedEvent event) {
		Object object = event.getObject();
		if (object instanceof AGwtEntity) {
			setToken((AGwtEntity) object);
		}
	}

	private Map<String, String> parseHistoryToken(String token) {
		if (token == null || token.length() == 0) return Collections.emptyMap();
		Map<String, String> map = new HashMap<String, String>();
		char separator = '|';
		int idx = token.indexOf(separator);
		while (idx > 0) {
			String subtoken = token.substring(0, idx);
			parseHistorySubToken(subtoken, map);
			token = token.substring(idx + 1);
			idx = token.indexOf(separator);
		}
		parseHistorySubToken(token, map);
		return map;
	}

	private void parseHistorySubToken(String token, Map<String, String> map) {
		int idx = token.indexOf('=');
		if (idx < 0) {
			map.put(token, token);
			return;
		}
		String key = token.substring(0, idx);
		String value = token.substring(idx + 1);
		map.put(key, value);
	}

}
