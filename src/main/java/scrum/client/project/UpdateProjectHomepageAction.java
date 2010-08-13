package scrum.client.project;

import ilarkesto.core.base.Str;
import scrum.client.common.TooltipBuilder;

public class UpdateProjectHomepageAction extends GUpdateProjectHomepageAction {

	public UpdateProjectHomepageAction(scrum.client.project.Project project) {
		super(project);
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder(
				"Export project data, thereby updating the public homepage for this project.");
		if (!project.isProductOwnerOrScrumMasterOrTeamMember(getCurrentUser()))
			tb.addRemark(TooltipBuilder.NOT_SCRUMTEAM);
		return super.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (Str.isBlank(project.getHomepageDir())) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!project.isProductOwnerOrScrumMasterOrTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	public String getLabel() {
		return "Update homepage";
	}

	@Override
	protected void onExecute() {
		new UpdateProjectHomepageServiceCall().execute();
	}

}