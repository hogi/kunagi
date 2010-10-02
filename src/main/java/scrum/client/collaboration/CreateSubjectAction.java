package scrum.client.collaboration;

import ilarkesto.core.scope.Scope;
import scrum.client.common.TooltipBuilder;
import scrum.client.workspace.ProjectWorkspaceWidgets;

public class CreateSubjectAction extends GCreateSubjectAction {

	@Override
	public String getLabel() {
		return "Create Subject";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder(
				"Create new Subject. You can set the title and start discussing after creation.");
		if (!getCurrentProject().isScrumTeamMember(getCurrentUser()))
			tb.addRemark(TooltipBuilder.NOT_SCRUMTEAM);

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isScrumTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		Subject subject = getCurrentProject().createNewSubject();
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).showForum(subject);
	}

}