package scrum.client.pr;

public class UnpublishBlogEntryAction extends GUnpublishBlogEntryAction {

	public UnpublishBlogEntryAction(scrum.client.pr.BlogEntry blogEntry) {
		super(blogEntry);
	}

	@Override
	public String getLabel() {
		return "Unpublish this Blog entry";
	}

	@Override
	public boolean isPermitted() {
		if (!blogEntry.getProject().isProductOwnerOrScrumMaster(getCurrentUser())) return false;
		return true;
	}

	@Override
	public boolean isExecutable() {
		if (!blogEntry.isPublished()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		blogEntry.setPublished(false);
	}

	private class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Unpublish " + blogEntry.getReference() + " " + blogEntry.getTitle();
		}

		@Override
		protected void onUndo() {
			blogEntry.setPublished(true);
		}

	}
}