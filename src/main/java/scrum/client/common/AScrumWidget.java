package scrum.client.common;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.AWidget;
import scrum.client.ComponentManager;
import scrum.client.Dao;
import scrum.client.ScrumGwtApplication;
import scrum.client.ScrumScopeManager;
import scrum.client.admin.Auth;
import scrum.client.admin.User;
import scrum.client.project.Project;
import scrum.client.sprint.Sprint;
import scrum.client.workspace.Ui;

public abstract class AScrumWidget extends AWidget {

	protected static final ComponentManager cm = ComponentManager.get();

	// --- helper ---

	// protected static final Navigator getNavigator() {
	// return Scope.get().getComponent(Navigator.class);
	// }

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

	protected static final Sprint getCurrentSprint() {
		return getCurrentProject().getCurrentSprint();
	}

	protected static final Ui getUi() {
		return Scope.get().getComponent(Ui.class);
	}

	protected static final ScrumGwtApplication getApp() {
		return (ScrumGwtApplication) Scope.get().getComponent("app");
	}

	protected static final Dao getDao() {
		return Dao.get();
	}
}
