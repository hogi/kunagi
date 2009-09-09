package scrum.client.sprint;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.AAction;
import scrum.client.project.Requirement;
import scrum.client.workspace.WorkareaWidget;

public class CreateTaskAction extends AAction {

	private Requirement requirement;

	public CreateTaskAction(Requirement task, AWidget... widgetsToUpdate) {
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
		Task task = requirement.createNewTask();
		WorkareaWidget.get().showTask(task);
	}

}
