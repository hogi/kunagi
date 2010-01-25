package scrum.client.project;

import scrum.client.common.TooltipBuilder;

public class DeleteQualityAction extends GDeleteQualityAction {

	protected DeleteQualityAction(Quality quality) {
		super(quality);
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Delete this quality.");

		if (!quality.getProject().isProductOwner(getCurrentUser())) tb.addRemark(TooltipBuilder.NOT_A_PRODUCT_OWNER);

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!quality.getProject().isProductOwner(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		quality.getProject().deleteQuality(quality);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Delete " + quality.getReference() + " " + quality.getLabel();
		}

		@Override
		protected void onUndo() {
			cm.getDao().createQuality(quality);
		}

	}

}
