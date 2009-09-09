package scrum.client.project;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.AAction;

public class DeleteRequirementAction extends AAction {

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
		return !getProject().isCurrentSprint(requirement.getSprint());
	}

	@Override
	protected void onExecute() {
		getProject().deleteRequirement(requirement);
	}

}
