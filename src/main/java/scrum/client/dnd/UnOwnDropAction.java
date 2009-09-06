package scrum.client.dnd;

import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.project.Requirement;
import scrum.client.sprint.Task;
import scrum.client.tasks.TaskBlock;
import scrum.client.workspace.Ui;

public class UnOwnDropAction implements BlockListDropAction<Task> {

	private Requirement requirement;

	public UnOwnDropAction(Requirement requirement) {
		this.requirement = requirement;
	}

	public boolean execute(ABlockWidget<Task> block, BlockListWidget<Task> fromList, int fromIndex,
			BlockListWidget<Task> toList, int toIndex) {
		TaskBlock taskBlock = (TaskBlock) block;
		Task task = taskBlock.getTask();
		task.setRequirement(this.requirement);
		task.setUnOwned();

		boolean selected = taskBlock.isSelected();
		Ui.get().update();
		if (selected) {
			taskBlock.getContainer().selectTask(task);
		}
		return true;
	}

}
