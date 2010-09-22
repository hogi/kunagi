package scrum.client.workspace;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.AGwtEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import scrum.client.ScrumScopeManager;
import scrum.client.admin.User;
import scrum.client.core.ApplicationStartedEvent;
import scrum.client.core.ApplicationStartedHandler;
import scrum.client.project.Project;
import scrum.client.project.ProjectDataReceivedEvent;
import scrum.client.project.SelectProjectServiceCall;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;

public class Navigator extends GNavigator implements BlockExpandedHandler, ApplicationStartedHandler {

	public static enum Mode {
		USER, PROJECT
	}

	private Mode currentMode;
	private String page = "Dashboard";

	@Override
	public void initialize() {
		History.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				evalHistoryToken(event.getValue());
			}
		});
	}

	private void evalHistoryToken(String token) {
		log.info("evaluating history token:", token);

		Map<String, String> tokens = parseHistoryToken(token);

		// if (tokens.containsKey("project") && !projectDataReceived) {
		// startToken = token;
		// return;
		// }

		onHistoryToken(tokens);
	}

	private void onHistoryToken(final Map<String, String> tokens) {

		if (tokens.containsKey("projectSelector")) {
			gotoProjectSelector();
			return;
		}

		String projectId = tokens.get("project");
		if (projectId == null) {
			gotoProjectSelector();
			return;
		}

		gotoProject(projectId, tokens.get("page"), tokens.get("entity"));
	}

	@Override
	public void onApplicationStarted(ApplicationStartedEvent event) {
		String historyToken = History.getToken();
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

	public void gotoProjectSelector() {
		History.newItem("projectSelector", false);
		activateUserMode();
	}

	public void gotoProject(String projectId) {
		gotoProject(projectId, null, null);
	}

	private void gotoProject(String projectId, String page, String entityId) {
		Project project = Scope.get().getComponent(Project.class);
		if (project != null && !projectId.equals(project.getId())) {
			project = null;
		}

		if (project == null) {
			project = dao.getProject(projectId);
			if (project == null) throw new RuntimeException("Project does not exist: " + projectId);
			acitvateProjectMode(project, page, entityId);
			return;
		}

		if (page != null) {
			Scope.get().getComponent(ProjectWorkspaceWidgets.class).showPage(page);
		}

		if (entityId != null) {
			Scope.get().getComponent(ProjectWorkspaceWidgets.class).showEntityById(entityId);
		}

		String token = "project=" + projectId;
		if (page != null) token += "|page=" + page;
		if (entityId != null) token += "|entity=" + entityId;
		History.newItem(token, false);
	}

	public void gotoEntity(String entityId) {
		Project project = Scope.get().getComponent(Project.class);
		gotoEntity(project.getId(), entityId);
	}

	public void gotoEntity(String projectId, String entityId) {
		gotoProject(projectId, null, entityId);
	}

	public void setPageToken(String page) {
		if (page.equals(this.page)) return;
		this.page = page;
		if (!History.getToken().contains("page=" + page)) {
			Project project = Scope.get().getComponent(Project.class);
			History.newItem("project=" + project.getId() + "|page=" + page, false);
		}
	}

	private void setToken(AGwtEntity entity) {
		Project project = Scope.get().getComponent(Project.class);
		if (project == null) return;
		History.newItem("project=" + project.getId() + "|page=" + page + "|entity=" + entity.getId(), false);
	}

	@Override
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

	private void activateUserMode() {
		if (currentMode == Mode.PROJECT) {
			ScrumScopeManager.destroyProjectScope();
		}

		log.info("Activating USER mode");
		Scope.get().getComponent(UsersWorkspaceWidgets.class).activate();
		currentMode = Mode.USER;
	}

	private void acitvateProjectMode(final Project project, final String page, final String entityId) {
		assert project != null;

		if (currentMode == Mode.PROJECT) {
			if (project.equals(Scope.get().getComponent(Project.class))) return;
			ScrumScopeManager.destroyProjectScope();
		}

		log.info("Activating PROJECT mode");
		Scope.get().getComponent(Ui.class).lock("Loading " + project.getLabel() + "...");
		new SelectProjectServiceCall(project.getId()).execute(new Runnable() {

			@Override
			public void run() {
				ScrumScopeManager.createProjectScope(project);
				currentMode = Mode.PROJECT;
				new ProjectDataReceivedEvent().fireInCurrentScope();
				ProjectWorkspaceWidgets projectWorkspaceWidgets = Scope.get().getComponent(
					ProjectWorkspaceWidgets.class);
				projectWorkspaceWidgets.activate();
				if (page != null) {
					projectWorkspaceWidgets.showPage(page);
				} else {
					projectWorkspaceWidgets.showDashboard();
				}
				if (entityId != null) {
					projectWorkspaceWidgets.showEntityById(entityId);
				}
			}
		});
	}

}
