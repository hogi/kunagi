package scrum.client.common;

import ilarkesto.gwt.client.AAction;
import scrum.client.Dao;
import scrum.client.ScrumGwtApplication;
import scrum.client.admin.User;
import scrum.client.project.Project;
import scrum.client.sprint.Sprint;
import scrum.client.workspace.Ui;

public abstract class AScrumAction extends AAction {

	public AScrumAction() {
		super(Ui.get());
	}

	// --- helper ---

	protected Dao getDao() {
		return ScrumGwtApplication.get().getDao();
	}

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
