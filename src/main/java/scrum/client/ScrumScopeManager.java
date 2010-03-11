package scrum.client;

import ilarkesto.core.scope.CascadingScope;
import ilarkesto.core.scope.NonConcurrentScopeManager;
import ilarkesto.core.scope.Scope;
import scrum.client.admin.User;
import scrum.client.collaboration.Chat;
import scrum.client.collaboration.Wiki;
import scrum.client.communication.Pinger;
import scrum.client.journal.ChangeHistoryManager;
import scrum.client.project.Project;

public class ScrumScopeManager {

	private static NonConcurrentScopeManager scopeManager;
	private static CascadingScope appScope;
	private static CascadingScope userScope;
	private static CascadingScope projectScope;

	private static ComponentManager cm;

	static void initialize(ComponentManager cm) {
		assert scopeManager == null;

		ScrumScopeManager.cm = cm;

		scopeManager = NonConcurrentScopeManager.createCascadingScopeInstance("app", new ScrumComponentsReflector());
		appScope = (CascadingScope) scopeManager.getScope();
		Scope scope = appScope;

		scope.putComponent("app", cm.getApp());
		scope.putComponent(cm.getDao());
		scope.putComponent(new Pinger());

		appScope.wireComponents();
	}

	public static void createUserScope(User user) {
		userScope = appScope.createScope("user");
		Scope scope = scopeManager.setScope(userScope);

		userScope.wireComponents();
	}

	public static void createProjectScope(Project project) {
		projectScope = userScope.createScope("project");
		Scope scope = scopeManager.setScope(projectScope);

		scope.putComponent(new Chat());
		scope.putComponent(new ChangeHistoryManager());
		scope.putComponent(new Wiki());

		projectScope.wireComponents();
		cm.getProjectContext().openProject(project);
	}

	public static void destroyProjectScope() {
		projectScope = null;
		scopeManager.setScope(userScope);
	}

	public static void destroyUserScope() {
		projectScope = null;
		userScope = null;
		scopeManager.setScope(appScope);
	}

}
