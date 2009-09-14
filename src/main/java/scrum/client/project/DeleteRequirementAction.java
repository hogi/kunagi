package scrum.client.project;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.AScrumAction;

public class DeleteRequirementAction extends AScrumAction {

	private Requirement requirement;

	public DeleteRequirementAction(Requirement task, AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
		this.requirement = task;
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		return "Delete this requirement.";
	}

	@Override
	public boolean isExecutable() {
		if (!getProject().isProductOwner(getUser())) return false;
		if (!getProject().isCurrentSprint(requirement.getSprint())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		getProject().deleteRequirement(requirement);
	}

}
