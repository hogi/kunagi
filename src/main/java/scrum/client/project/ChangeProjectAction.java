package scrum.client.project;

public class ChangeProjectAction extends GChangeProjectAction {

	@Override
	public String getLabel() {
		return "Change Project";
	}

	@Override
	public boolean isExecutable() {
		return getProject() != null;
	}

	@Override
	protected void onExecute() {
		cm.getProjectContext().closeProject(true);
	}

}