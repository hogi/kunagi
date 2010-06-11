package scrum.client.common;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.AComponent;
import scrum.client.ScrumScopeManager;
import scrum.client.admin.Auth;
import scrum.client.admin.User;
import scrum.client.project.Project;

public class AScrumComponent extends AComponent {

	// --- helper ---

	protected static final User getCurrentUser() {
		assert getAuth().isUserLoggedIn();
		return getAuth().getUser();
	}

	protected static final Auth getAuth() {
		return Scope.get().getComponent(Auth.class);
	}

	protected static final Project getCurrentProject() {
		assert ScrumScopeManager.isProjectScope();
		return ScrumScopeManager.getProject();
	}

}
