package scrum.client.project;

import scrum.client.common.TooltipBuilder;

public class CloseRequirementAction extends GCloseRequirementAction {

	public CloseRequirementAction(scrum.client.project.Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Accept";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Accept this Story as completed.");

		if (!getCurrentProject().isProductOwner(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_PRODUCT_OWNER);
		} else {
			if (requirement.isClosed()) tb.addRemark("Story is already closed.");
			if (!requirement.getTasks().isEmpty() && !requirement.isTasksClosed())
				tb.addRemark("Requirement contains unclosed tasks.");
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isProductOwner(getCurrentUser())) return false;
		if (!requirement.isTasksClosed()) return false;
		return true;
	}

	@Override
	public boolean isExecutable() {
		if (requirement.isClosed()) return false;
		if (!requirement.isInCurrentSprint()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.setClosed(true);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Close " + requirement.getReferenceAndLabel();
		}

		@Override
		protected void onUndo() {
			requirement.setClosed(false);
		}

	}

}