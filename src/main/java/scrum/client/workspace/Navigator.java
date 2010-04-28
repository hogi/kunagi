package scrum.client.workspace;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.AGwtEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import scrum.client.BlockExpandedListener;
import scrum.client.ScrumScopeManager;
import scrum.client.admin.User;
import scrum.client.project.Project;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;

public class Navigator extends GNavigator implements BlockExpandedListener {

	private Map<String, String> tokensForStart;
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
		User user = auth.getUser();
		Project project = Scope.get().getComponent(Project.class);

		if (tokens.containsKey("login")) {
			Scope.get().getComponent(PublicWorkspaceWidgets.class).activate();
			return;
		}

		if (user == null) {
			tokensForStart = tokens;
			gotoLogin();
			return;
		}

		if (tokens.containsKey("projectSelector")) {
			if (project != null) ScrumScopeManager.destroyProjectScope();
			Scope.get().getComponent(UsersWorkspaceWidgets.class).activate();
			return;
		}

		String projectId = tokens.get("project");
		if (projectId == null) {
			gotoProjectSelector();
			return;
		}

		if (project != null && !projectId.equals(project.getId())) {
			ScrumScopeManager.destroyProjectScope();
			project = null;
		}

		if (project == null) {
			project = dao.getProject(projectId);
			if (project == null) throw new RuntimeException("Project does not exist: " + projectId);
			ScrumScopeManager.createProjectScope(project, new Runnable() {

				public void run() {
					Scope.get().getComponent(ProjectWorkspaceWidgets.class).activate();
					String entityId = tokens.get("entity");
					String page = tokens.get("page");
					if (page != null) {
						Scope.get().getComponent(ProjectWorkspaceWidgets.class).showPage(page);
					}
					if (entityId != null) {
						Scope.get().getComponent(ProjectWorkspaceWidgets.class).showEntityById(entityId);
					}
				}
			});
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

	public void gotoStart() {
		User user = auth.getUser();
		if (user == null) {
			evalHistoryToken(History.getToken());
			return;
		}
		if (tokensForStart == null) {
			if (History.getToken().contains("project=")) {
				evalHistoryToken(History.getToken());
			} else {
				Project project = user.getCurrentProject();
				if (project == null || user.isAdmin()) {
					gotoProjectSelector();
				} else {
					gotoProject(project.getId());
				}
			}
		} else {
			onHistoryToken(tokensForStart);
			tokensForStart = null;
		}
	}

	public void gotoLogin() {
		gotoToken("login");
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
			History.newItem("project=" + project.getId() + "|page=" + page);
		}
	}

	public void setToken(AGwtEntity entity) {
		Project project = Scope.get().getComponent(Project.class);
		History.newItem("project=" + project.getId() + "|page=" + page + "|entity=" + entity.getId());
	}

	public void onBlockExpanded(Object object) {
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
