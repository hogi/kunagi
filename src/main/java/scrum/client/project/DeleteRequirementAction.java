package scrum.client.project;

import scrum.client.common.TooltipBuilder;

public class DeleteRequirementAction extends GDeleteRequirementAction {

	protected DeleteRequirementAction(Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Delete this Story.");

		if (!requirement.getProject().isProductOwner(getCurrentUser())) tb.addRemark(TooltipBuilder.NOT_PRODUCT_OWNER);

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (getCurrentProject().isCurrentSprint(requirement.getSprint())) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!requirement.getProject().isProductOwner(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.getProject().deleteRequirement(requirement);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Delete " + requirement.getReferenceAndLabel();
		}

		@Override
		protected void onUndo() {
			cm.getDao().createRequirement(requirement);
		}

	}

}
