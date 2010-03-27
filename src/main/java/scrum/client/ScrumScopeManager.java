package scrum.client;

import ilarkesto.core.scope.CascadingScope;
import ilarkesto.core.scope.NonConcurrentScopeManager;
import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.ObjectMappedFlowPanel;
import scrum.client.admin.Auth;
import scrum.client.admin.SystemMessageManager;
import scrum.client.admin.User;
import scrum.client.calendar.Calendar;
import scrum.client.collaboration.Chat;
import scrum.client.collaboration.UsersStatus;
import scrum.client.collaboration.Wiki;
import scrum.client.communication.Pinger;
import scrum.client.files.Uploader;
import scrum.client.issues.IssueManager;
import scrum.client.journal.ChangeHistoryManager;
import scrum.client.project.Project;
import scrum.client.search.Search;
import scrum.client.undo.Undo;
import scrum.client.workspace.DndManager;
import scrum.client.workspace.ProjectWorkspaceWidgets;
import scrum.client.workspace.PublicWorkspaceWidgets;
import scrum.client.workspace.Ui;
import scrum.client.workspace.UsersWorkspaceWidgets;

public class ScrumScopeManager {

	private static NonConcurrentScopeManager scopeManager;
	private static CascadingScope appScope;
	private static CascadingScope userScope;
	private static CascadingScope projectScope;

	static void initialize(ComponentManager cm) {
		assert scopeManager == null;

		Dao dao = Dao.get();
		dao.setApp(cm.getApp());
		dao.setEventBus(cm.getEventBus());

		scopeManager = NonConcurrentScopeManager.createCascadingScopeInstance("app", new ScrumComponentsReflector());
		appScope = (CascadingScope) scopeManager.getScope();
		Scope scope = appScope;

		scope.putComponent("app", cm.getApp());
		scope.putComponent(dao);
		scope.putComponent(new Pinger());
		scope.putComponent(new Ui());
		scope.putComponent(new SystemMessageManager());
		scope.putComponent(new Auth());
		scope.putComponent(new PublicWorkspaceWidgets());

		appScope.wireComponents();
	}

	public static void createUserScope(User user) {
		assert user != null;

		userScope = appScope.createScope("user");
		Scope scope = scopeManager.setScope(userScope);

		scope.putComponent("user", user);
		scope.putComponent(new UsersWorkspaceWidgets());

		userScope.wireComponents();
	}

	public static void createProjectScope(Project project) {
		assert project != null;
		Scope.get().getComponent(Ui.class).lock("Loading " + project.getLabel() + "...");

		projectScope = userScope.createScope("project");
		Scope scope = scopeManager.setScope(projectScope);

		scope.putComponent("project", project);
		final ProjectWorkspaceWidgets projectWorkspaceWidgets = scope.putComponent(new ProjectWorkspaceWidgets());
		scope.putComponent(new Chat());
		scope.putComponent(new ChangeHistoryManager());
		scope.putComponent(new Wiki());
		scope.putComponent(new Calendar());
		scope.putComponent(new Undo());
		scope.putComponent(new DndManager());
		scope.putComponent(new Uploader());
		scope.putComponent(new Search());
		scope.putComponent(new UsersStatus());
		scope.putComponent(new IssueManager());

		projectScope.wireComponents();

		((ScrumGwtApplication) appScope.getComponent("app")).callSelectProject(project.getId(), new Runnable() {

			public void run() {
				projectWorkspaceWidgets.activate();
			}
		});
	}

	public static void destroyProjectScope() {
		Scope.get().getComponent(Ui.class).lock("Closing project...");
		((ScrumGwtApplication) appScope.getComponent("app")).callCloseProject();
		appScope.getComponent(Dao.class).clearProjectEntities();
		ObjectMappedFlowPanel.objectHeights.clear();
		projectScope = null;
		scopeManager.setScope(userScope);
	}

	public static void destroyUserScope() {
		destroyProjectScope();
		userScope = null;
		scopeManager.setScope(appScope);
	}

	public static boolean isProjectScope() {
		return projectScope != null;
	}

	public static Project getProject() {
		return (Project) projectScope.getComponent("project");
	}
}
