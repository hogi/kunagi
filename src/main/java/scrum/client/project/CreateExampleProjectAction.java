package scrum.client.project;

import ilarkesto.core.scope.Scope;
import scrum.client.workspace.Ui;

public class CreateExampleProjectAction extends GCreateExampleProjectAction {

	@Override
	public String getLabel() {
		return "Create Example Project";
	}

	@Override
	protected void onExecute() {
		Scope.get().getComponent(Ui.class).lock("Creating Example Project...");
		getApp().callCreateExampleProject(new Runnable() {

			public void run() {
				Scope.get().getComponent(Ui.class).unlock();
			}
		});
	}

}