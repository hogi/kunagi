package scrum.client.project;

import ilarkesto.core.scope.Scope;

import java.util.List;

import scrum.client.workspace.Ui;
import scrum.client.workspace.UsersWorkspaceWidgets;

public class CreateExampleProjectAction extends GCreateExampleProjectAction {

	@Override
	public String getLabel() {
		return "Create Example Project";
	}

	@Override
	protected void onExecute() {
		Scope.get().getComponent(Ui.class).lock("Creating Example Project...");
		final List<Project> previousProjects = getDao().getProjects();
		new CreateExampleProjectServiceCall().execute(new Runnable() {

			public void run() {
				Scope.get().getComponent(Ui.class).unlock();
				List<Project> newProjects = getDao().getProjects();
				newProjects.removeAll(previousProjects);
				if (!newProjects.isEmpty()) {
					Scope.get().getComponent(UsersWorkspaceWidgets.class).getProjectSelector()
							.select(newProjects.get(0));
				}
			}
		});
	}

}