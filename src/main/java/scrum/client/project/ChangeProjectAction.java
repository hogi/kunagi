package scrum.client.project;

import scrum.client.common.TooltipBuilder;

public class ChangeProjectAction extends GChangeProjectAction {

	private Project project;

	public ChangeProjectAction(Project project) {
		this.project = project;
	}

	@Override
	public String getLabel() {
		return project.getLabel();
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Switch to another Project.");
		return tb.getTooltip();
	}

	@Override
	protected void onExecute() {
		getNavigator().gotoProject(project.getId());
	}

}