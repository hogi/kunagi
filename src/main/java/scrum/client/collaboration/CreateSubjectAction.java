package scrum.client.collaboration;

import scrum.client.common.TooltipBuilder;

public class CreateSubjectAction extends GCreateSubjectAction {

	@Override
	public String getLabel() {
		return "Create new Subject";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Create new thread.");
		if (!getCurrentProject().isProductOwnerOrScrumMasterOrTeamMember(getCurrentUser()))
			tb.addRemark(TooltipBuilder.NOT_SCRUMTEAM);

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isProductOwnerOrScrumMasterOrTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		Subject subject = getCurrentProject().createNewSubject();
		cm.getProjectContext().showForum(subject);
	}

}