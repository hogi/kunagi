package scrum.client.project;

import ilarkesto.core.scope.Scope;
import scrum.client.common.TooltipBuilder;
import scrum.client.workspace.ProjectWorkspaceWidgets;

public class CreateRequirementAction extends GCreateRequirementAction {

	@Override
	public String getLabel() {
		return "Create new Story";
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Create a new Story.");

		if (!getCurrentProject().isProductOwner(getCurrentUser())) tb.addRemark(TooltipBuilder.NOT_PRODUCT_OWNER);

		return tb.getTooltip();
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isProductOwner(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		Requirement requirement = getCurrentProject().createNewRequirement();
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).showProductBacklog(requirement);
		// addUndo(new Undo(requirement));
	}

	// class Undo extends ALocalUndo {
	//
	// private Requirement requirement;
	//
	// public Undo(Requirement requirement) {
	// this.requirement = requirement;
	// }
	//
	// @Override
	// public String getLabel() {
	// return "Create " + requirement.getReferenceAndLabel();
	// }
	//
	// @Override
	// protected void onUndo() {
	// requirement.getProject().deleteRequirement(requirement);
	// }
	//
	// }

}
