package scrum.client.common;

import ilarkesto.gwt.client.AAction;
import scrum.client.ComponentManager;
import scrum.client.admin.User;
import scrum.client.project.Project;
import scrum.client.sprint.Sprint;
import scrum.client.undo.AUndoOperation;

public abstract class AScrumAction extends AAction {

	protected static final ComponentManager cm = ComponentManager.get();

	public AScrumAction() {
		super(cm.getUi().getWorkspace());
	}

	// --- helper ---

	protected static final void addUndo(AUndoOperation undo) {
		cm.getUndo().getManager().add(undo);
	}

	protected static final boolean isCurrentSprint(Sprint sprint) {
		return getCurrentProject().isCurrentSprint(sprint);
	}

	protected static final User getCurrentUser() {
		assert cm.getAuth().isUserLoggedIn();
		return cm.getAuth().getUser();
	}

	protected static final Project getCurrentProject() {
		assert cm.getProjectContext().isProjectOpen();
		return cm.getProjectContext().getProject();
	}

	protected abstract class ALocalUndo extends AUndoOperation {

		@Override
		public String getLabel() {
			return "Undo " + AScrumAction.this.getLabel();
		}

	}

}
