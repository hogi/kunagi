package scrum.client.project;


public class CreateProjectAction extends GCreateProjectAction {

	@Override
	public String getLabel() {
		return "Create new Project";
	}

	@Override
	public boolean isExecutable() {
		return getCurrentUser().isAdmin();
	}

	@Override
	protected void onExecute() {
		Project project = new Project(getCurrentUser());
		cm.getDao().createProject(project);
	}

}