package scrum.client;

import scrum.client.workspace.Ui;

public class ClientLogger {

	public static final void debug(String message) {
		showClientMessage(message);
	}

	public static final void showClientMessage(String message) {
		Ui.get().showError(message);
	}

}
