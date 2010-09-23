package scrum.client.collaboration;

public class PublishCommentAction extends GPublishCommentAction {

	public PublishCommentAction(Comment comment) {
		super(comment);
	}

	@Override
	public String getLabel() {
		return "Publish";
	}

	@Override
	public String getTooltip() {
		return "Make this comment visible on the homepage.";
	}

	@Override
	public boolean isExecutable() {
		if (comment.isPublished()) return false;
		if (getCurrentProject().getHomepageDir() == null) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!comment.isAuthor(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		comment.setPublished(true);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Publish Comment";
		}

		@Override
		protected void onUndo() {
			comment.setPublished(false);
		}
	}

}