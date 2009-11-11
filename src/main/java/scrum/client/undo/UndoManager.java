package scrum.client.undo;

import ilarkesto.gwt.client.GwtLogger;

import java.util.LinkedList;
import java.util.List;

public class UndoManager {

	private static final GwtLogger LOG = GwtLogger.createLogger(UndoManager.class);

	private int maxOperations = 5;
	private List<AUndoOperation> operations = new LinkedList<AUndoOperation>();

	public void add(AUndoOperation operation) {
		operations.add(0, operation);
		while (operations.size() > maxOperations) {
			operations.remove(operations.size() - 1);
		}
	}

	public void undo(AUndoOperation operation) {
		LOG.info("Undo:", operation);
		operation.undo();
		operations.remove(operation);
	}

	public List<AUndoOperation> getOperations() {
		return operations;
	}
}
