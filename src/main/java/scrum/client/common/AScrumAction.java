package scrum.client.common;

import ilarkesto.gwt.client.AAction;
import scrum.client.ComponentManager;
import scrum.client.admin.User;
import scrum.client.project.Project;
import scrum.client.sprint.Sprint;

public abstract class AScrumAction extends AAction {

	protected static final ComponentManager cm = ComponentManager.get();

	public AScrumAction() {
		super(ComponentManager.get().getUi().getWorkspace());
	}

	// --- helper ---

	protected final Project getProject() {
		return cm.getProjectContext().getProject();
	}

	protected final boolean isCurrentSprint(Sprint sprint) {
		return getProject().isCurrentSprint(sprint);
	}

	protected final User getUser() {
		return cm.getAuth().getUser();
	}

}
