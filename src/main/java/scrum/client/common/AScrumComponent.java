package scrum.client.common;

import ilarkesto.gwt.client.AComponent;
import scrum.client.ComponentManager;
import scrum.client.admin.User;
import scrum.client.project.Project;

public class AScrumComponent extends AComponent {

	protected static final ComponentManager cm = ComponentManager.get();

	// --- helper ---

	protected static final User getCurrentUser() {
		assert cm.getAuth().isUserLoggedIn();
		return cm.getAuth().getUser();
	}

	protected static final Project getCurrentProject() {
		assert cm.getProjectContext().isProjectOpen();
		return cm.getProjectContext().getProject();
	}

}
