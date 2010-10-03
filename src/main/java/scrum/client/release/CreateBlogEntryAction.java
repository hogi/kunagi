package scrum.client.release;

import ilarkesto.core.scope.Scope;
import scrum.client.common.TooltipBuilder;
import scrum.client.pr.BlogEntry;
import scrum.client.workspace.ProjectWorkspaceWidgets;

public class CreateBlogEntryAction extends GCreateBlogEntryAction {

	public CreateBlogEntryAction(scrum.client.release.Release release) {
		super(release);
	}

	@Override
	public String getLabel() {
		return "Create Blog Entry";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder(
				"Create a new Blog Entry advertizing this Release. It will contain itemized Release Notes.");

		if (!getCurrentProject().isScrumTeamMember(getCurrentUser())) tb.addRemark(TooltipBuilder.NOT_SCRUMTEAM);

		return tb.getTooltip();
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isScrumTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		BlogEntry blogEntry = getCurrentProject().createNewBlogEntry(release);
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).showBlog(blogEntry);
	}

}