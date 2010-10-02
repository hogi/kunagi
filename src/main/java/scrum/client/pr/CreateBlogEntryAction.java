package scrum.client.pr;

import ilarkesto.core.scope.Scope;
import scrum.client.common.TooltipBuilder;
import scrum.client.workspace.ProjectWorkspaceWidgets;

public class CreateBlogEntryAction extends GCreateBlogEntryAction {

	@Override
	public String getLabel() {
		return "Create Blog entry";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder(
				"Create a new Blog entry. You can set date, title and text after creation.");

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
		BlogEntry blogEntry = getCurrentProject().createNewBlogEntry();
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).showBlog(blogEntry);
	}
}