package scrum.client;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.undo.UndoManager;
import scrum.client.common.AScrumComponent;

public class Undo extends AScrumComponent {

	@Override
	protected void onInitialization() {
		getManager().clear();
	}

	public UndoManager getManager() {
		return Gwt.getUndoManager();
	}

}