package scrum.client;

import ilarkesto.gwt.client.AComponent;
import scrum.client.workspace.Workspace;

public class Ui extends AComponent {

	private Workspace workspace;

	@Override
	protected void onInitialization() {
		super.onInitialization();
		workspace = new Workspace();
	}

	public Workspace getWorkspace() {
		return workspace;
	}

}