package scrum.client.sprint;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.AAction;
import scrum.client.project.Requirement;

public class CreateTaskInRequirementAction extends AAction {

	private Requirement requirement;

	public CreateTaskInRequirementAction(Requirement task, AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
		this.requirement = task;
	}

	@Override
	public String getLabel() {
		return "Create new Task";
	}

	@Override
	public String getTooltip() {
		return "Create a new Task for this Requirement.";
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.createNewTask();
	}

}
