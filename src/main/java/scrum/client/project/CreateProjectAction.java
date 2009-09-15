package scrum.client.project;

import scrum.client.ScrumGwtApplication;

public class CreateProjectAction extends GCreateProjectAction {

	@Override
	public String getLabel() {
		return "Create new Project";
	}

	@Override
	public boolean isExecutable() {
		return getUser().isAdmin();
	}

	@Override
	protected void onExecute() {
		Project project = new Project(ScrumGwtApplication.get().getUser());
		ScrumGwtApplication.get().getDao().createProject(project);
	}

}