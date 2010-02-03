package scrum.client.project;

import scrum.client.common.TooltipBuilder;

public class CreateProjectAction extends GCreateProjectAction {

	@Override
	public String getLabel() {
		return "Create new Project";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Create a new Project.");

		if (!getCurrentUser().isAdmin()) tb.addRemark(TooltipBuilder.NOT_ADMIN);

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
		Project project = new Project(getCurrentUser());
		cm.getDao().createProject(project);
	}

}