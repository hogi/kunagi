package scrum.client.files;

import scrum.client.common.TooltipBuilder;

public class DeleteFileAction extends GDeleteFileAction {

	public DeleteFileAction(scrum.client.files.File file) {
		super(file);
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Delete this File.");
		if (!file.getProject().isProductOwnerOrScrumMasterOrTeamMember(getCurrentUser()))
			tb.addRemark(TooltipBuilder.NOT_ANYTHING);

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
		getCurrentProject().deleteFile(file);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Delete " + file.getReference() + " " + file.getLabel();
		}

		@Override
		protected void onUndo() {
			cm.getDao().createFile(file);
		}

	}

}