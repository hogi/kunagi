package scrum.client;

import java.util.LinkedList;
import java.util.List;

import scrum.client.common.AScrumComponent;
import scrum.client.undo.AUndoOperation;

public class Undo extends AScrumComponent {

	private int maxOperations = 5;
	private List<AUndoOperation> operations = new LinkedList<AUndoOperation>();

	public void add(AUndoOperation operation) {
		operations.add(0, operation);
		while (operations.size() > maxOperations) {
			operations.remove(operations.size() - 1);
		}
	}

	public void undo(AUndoOperation operation) {
		log.info("Undo:", operation);
		operation.undo();
		operations.remove(operation);
	}

	public List<AUndoOperation> getOperations() {
		return operations;
	}

}