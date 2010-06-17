package scrum.client.core;

import ilarkesto.core.scope.Scope;
import scrum.client.ScrumGwtApplication;
import scrum.client.ScrumScopeManager;
import scrum.client.project.Project;
import scrum.client.project.ProjectDataReceivedEvent;
import scrum.client.project.ProjectDataReceivedHandler;
import scrum.client.project.SelectProjectServiceCall;
import scrum.client.workspace.ProjectWorkspaceWidgets;
import scrum.client.workspace.PublicWorkspaceWidgets;
import scrum.client.workspace.Ui;
import scrum.client.workspace.UsersWorkspaceWidgets;

/**
 * Switches workspace modes:
 * <ul>
 * <li>public (login, registration, ...)</li>
 * <li>user (project selector, user management, ...)</li>
 * <li>project (dashboard, product backlog, ...)</li>
 * </ul>
 */
public class ModeSwitcher extends GModeSwitcher implements ProjectDataReceivedHandler {

	public static enum Mode {
		PUBLIC, USER, PROJECT
	}

	private Mode currentMode;

	public void onProjectDataReceived(ProjectDataReceivedEvent event) {
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).activate();
	}

	public void activatePublicMode() {

		if (currentMode == Mode.PROJECT || currentMode == Mode.USER) {
			ScrumGwtApplication.get().logout();
		}

		log.info("Activating PUBLIC mode");
		Scope.get().getComponent(PublicWorkspaceWidgets.class).activate();
		currentMode = Mode.PUBLIC;
	}

	public void activateUserMode() {
		if (currentMode == Mode.PROJECT) {
			ScrumScopeManager.destroyProjectScope();
		}

		log.info("Activating USER mode");
		Scope.get().getComponent(UsersWorkspaceWidgets.class).activate();
		currentMode = Mode.USER;
	}

	public void acitvateProjectMode(Project project, final String page, final String entityId) {
		assert project != null;

		if (currentMode == null) {
			activatePublicMode();
			return;
		}

		if (currentMode == Mode.PROJECT) {
			if (project.equals(Scope.get().getComponent(Project.class))) return;
			ScrumScopeManager.destroyProjectScope();
		}

		log.info("Activating PROJECT mode");
		Scope.get().getComponent(Ui.class).lock("Loading " + project.getLabel() + "...");
		ScrumScopeManager.createProjectScope(project);
		currentMode = Mode.PROJECT;
		new SelectProjectServiceCall(project.getId()).execute(new Runnable() {

			public void run() {
				new ProjectDataReceivedEvent().fireInCurrentScope();
				if (page != null) {
					Scope.get().getComponent(ProjectWorkspaceWidgets.class).showPage(page);
				}
				if (entityId != null) {
					Scope.get().getComponent(ProjectWorkspaceWidgets.class).showEntityById(entityId);
				}
			}
		});
	}

}
