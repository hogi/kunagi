package scrum.client.tasks;

import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.dnd.BlockListDropAction;
import scrum.client.project.Requirement;
import scrum.client.sprint.CloseTaskAction;
import scrum.client.sprint.Task;
import scrum.client.workspace.Ui;

public class CloseTaskDropAction implements BlockListDropAction<Task> {

	private Requirement requirement;

	public CloseTaskDropAction(Requirement requirement) {
		this.requirement = requirement;
	}

	public boolean execute(ABlockWidget<Task> block, BlockListWidget<Task> fromList, int fromIndex,
			BlockListWidget<Task> toList, int toIndex) {
		TaskBlock taskBlock = (TaskBlock) block;
		Task task = taskBlock.getTask();
		task.setRequirement(this.requirement);
		new CloseTaskAction(task).execute();

		boolean selected = taskBlock.isExtended();
		Ui.get().update();
		if (selected) {
			taskBlock.getContainer().selectTask(task);
		}
		return true;
	}

}
