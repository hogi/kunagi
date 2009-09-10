package scrum.client.project;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.AAction;

public class AddRequirementToCurrentSprintAction extends AAction {

	private Requirement requirement;

	public AddRequirementToCurrentSprintAction(Requirement task, AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
		this.requirement = task;
	}

	@Override
	public String getLabel() {
		return "Add to current Sprint";
	}

	@Override
	public String getTooltip() {
		return "Add this requirement the the current Sprint.";
	}

	@Override
	public boolean isExecutable() {
		if (!requirement.isEstimatedWorkValid()) return false;
		if (isCurrentSprint(requirement.getSprint())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.setSprint(getProject().getCurrentSprint());
	}

}
