package scrum.client.undo;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.undo.UndoManager;

public class Undo extends GUndo {

	@Override
	public void initialize() {
		getManager().clear();
	}

	public UndoManager getManager() {
		return Gwt.getUndoManager();
	}
}
