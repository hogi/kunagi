package scrum.client.common;

import ilarkesto.gwt.client.AAction;
import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.admin.User;
import scrum.client.project.Project;
import scrum.client.sprint.Sprint;

public abstract class AScrumAction extends AAction {

	public AScrumAction(AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
	}

	// --- helper ---

	protected Project getProject() {
		return ScrumGwtApplication.get().getProject();
	}

	protected boolean isCurrentSprint(Sprint sprint) {
		return getProject().isCurrentSprint(sprint);
	}

	protected User getUser() {
		return ScrumGwtApplication.get().getUser();
	}

}
