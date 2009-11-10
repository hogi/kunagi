package scrum.client.undo;

public abstract class AUndoOperation {

	protected abstract void onUndo();

	public abstract String getLabel();

	public final void undo() {
		onUndo();
	}

	@Override
	public String toString() {
		return "AUndoOperation(" + getLabel() + ")";
	}

}
