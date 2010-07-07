package scrum.client.pr;

import ilarkesto.core.base.Str;

public class PublishBlogEntryAction extends GPublishBlogEntryAction {

	public PublishBlogEntryAction(scrum.client.pr.BlogEntry blogEntry) {
		super(blogEntry);
	}

	@Override
	public String getLabel() {
		return "Publish this Blog entry";
	}

	@Override
	public boolean isPermitted() {
		if (!blogEntry.getProject().isProductOwnerOrScrumMaster(getCurrentUser())) return false;
		return true;
	}

	@Override
	public boolean isExecutable() {
		if (blogEntry.isPublished()) return false;
		if (Str.isBlanc(blogEntry.getTitle())) return false;
		if (Str.isBlanc(blogEntry.getText())) return false;
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