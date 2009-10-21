package scrum.client.tasks;

import scrum.client.common.ABlockWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;
import scrum.client.sprint.ClaimTaskAction;
import scrum.client.sprint.CloseTaskAction;
import scrum.client.sprint.DeleteTaskAction;
import scrum.client.sprint.ReopenTaskAction;
import scrum.client.sprint.Task;
import scrum.client.sprint.UnclaimTaskAction;

import com.google.gwt.user.client.ui.Widget;

public class TaskBlock extends AExtensibleBlockWidget<Task> implements TrashSupport {

	private TaskBlockContainer container;

	public TaskBlock(TaskBlockContainer container) {
		this.container = container;
	}

	@Override
	protected void onCollapsedInitialization() {
		setIcon(Img.bundle.task16());
	}

	@Override
	protected void onUpdateHead() {
		Task task = getObject();
		setBlockTitle(task.getLongLabel(container.isShowOwner(), container.isShowRequirement()));
		addMenuAction(new ClaimTaskAction(task));
		addMenuAction(new CloseTaskAction(task));
		addMenuAction(new ReopenTaskAction(task));
		addMenuAction(new UnclaimTaskAction(task));
		addMenuAction(new DeleteTaskAction(task));
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new TaskWidget(getObject());
	}

	public AScrumAction getTrashAction() {
		return new DeleteTaskAction(getObject());
	}

	private boolean isTaskOverview() {
		return container instanceof TaskOverviewWidget;
	}

	public TaskBlockContainer getContainer() {
		return this.container;
	}

	public static class TaskBlockFactory implements BlockWidgetFactory<Task> {

		private TaskBlockContainer container;

		public TaskBlockFactory(TaskBlockContainer container) {
			this.container = container;
		}

		public ABlockWidget<Task> createBlock() {
			return new TaskBlock(container);
		}
	}
}
