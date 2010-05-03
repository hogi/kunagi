package scrum.client.release;

import scrum.client.common.TooltipBuilder;

public class DeleteReleaseAction extends GDeleteReleaseAction {

	public DeleteReleaseAction(scrum.client.release.Release release) {
		super(release);
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Delete this Release");

		if (!getCurrentProject().isProductOwner(getCurrentUser())) tb.addRemark(TooltipBuilder.NOT_SCRUMTEAM);

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isProductOwner(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		getDao().deleteRelease(release);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Delete " + release.getReference() + " " + release.getLabel();
		}

		@Override
		protected void onUndo() {
			getDao().createRelease(release);
		}

	}
}