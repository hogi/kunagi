package scrum.client.pr;

import ilarkesto.core.base.Str;
import scrum.client.common.TooltipBuilder;

public class PublishBlogEntryAction extends GPublishBlogEntryAction {

	public PublishBlogEntryAction(scrum.client.pr.BlogEntry blogEntry) {
		super(blogEntry);
	}

	@Override
	public String getLabel() {
		return "Publish";
	}

	@Override
	public String getTooltip() {
		// TODO Auto-generated method stub
		TooltipBuilder tb = new TooltipBuilder(
				"Publish this Blog entry. Published entries are taken into account when exporting project data.");

		if (!isPermitted()) tb.addRemark(TooltipBuilder.NOT_PRODUCT_OWNER_NOR_SCRUMMASTER);

		return tb.getTooltip();
	}

	@Override
	public boolean isPermitted() {
		if (!blogEntry.getProject().isProductOwnerOrScrumMaster(getCurrentUser())) return false;
		return true;
	}

	@Override
	public boolean isExecutable() {
		if (blogEntry.isPublished()) return false;
		if (Str.isBlank(blogEntry.getTitle())) return false;
		if (Str.isBlank(blogEntry.getText())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		blogEntry.setPublished(true);
	}

	private class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Publish " + blogEntry.getReference() + " " + blogEntry.getTitle();
		}

		@Override
		protected void onUndo() {
			blogEntry.setPublished(false);
		}

	}

}