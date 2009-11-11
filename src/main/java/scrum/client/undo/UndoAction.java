package scrum.client.undo;

import ilarkesto.gwt.client.AAction;
import scrum.client.ComponentManager;

public class UndoAction extends AAction {

	private UndoManager undoManager;
	private AUndoOperation operation;

	public UndoAction(UndoManager undoManager, AUndoOperation operation) {
		this.undoManager = undoManager;
		this.operation = operation;
	}

	@Override
	public String getLabel() {
		return operation.getLabel();
	}

	@Override
	protected void onExecute() {
		undoManager.undo(operation);
		ComponentManager.get().getUi().getWorkspace().update();
	}

}
