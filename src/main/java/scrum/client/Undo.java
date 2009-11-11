package scrum.client;

import scrum.client.common.AScrumComponent;
import scrum.client.undo.UndoManager;

public class Undo extends AScrumComponent {

	private UndoManager manager;

	@Override
	protected void onInitialization() {
		manager = new UndoManager();
	}

	public UndoManager getManager() {
		return manager;
	}

}