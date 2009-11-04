package scrum.client.project;

import scrum.client.common.TooltipBuilder;

import com.google.gwt.user.client.Window;

public class DeleteProjectAction extends GDeleteProjectAction {

	public DeleteProjectAction(Project project) {
		super(project);
	}

	@Override
	public String getLabel() {
		return "Delete Project";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Delete this project and destroy all its data.");

		if (!getCurrentUser().isAdmin()) tb.addRemark(TooltipBuilder.NOT_AN_ADMIN);

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentUser().isAdmin()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		if (Window.confirm("Delete project " + project.getLabel() + "?")) {
			cm.getDao().deleteProject(project);
		}
	}

}