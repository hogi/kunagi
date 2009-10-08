package scrum.client.common;

import ilarkesto.gwt.client.AAction;
import scrum.client.Components;
import scrum.client.Dao;
import scrum.client.ProjectContext;
import scrum.client.Ui;
import scrum.client.admin.User;
import scrum.client.project.Project;
import scrum.client.sprint.Sprint;

public abstract class AScrumAction extends AAction {

	public AScrumAction() {
		super(Components.get().getUi().getWorkspace());
	}

	// --- helper ---

	protected final Ui getUi() {
		return getComponents().getUi();
	}

	protected final Dao getDao() {
		return getComponents().getDao();
	}

	protected final Project getProject() {
		return getProjectContext().getProject();
	}

	protected final ProjectContext getProjectContext() {
		return getComponents().getProjectContext();
	}

	protected final boolean isCurrentSprint(Sprint sprint) {
		return getProject().isCurrentSprint(sprint);
	}

	protected final User getUser() {
		return getComponents().getAuth().getUser();
	}

	protected final Components getComponents() {
		return Components.get();
	}

}
