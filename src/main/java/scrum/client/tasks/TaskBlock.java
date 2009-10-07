package scrum.client.tasks;

import scrum.client.common.ABlockWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.ClipboardSupport;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;
import scrum.client.sprint.ClaimTaskAction;
import scrum.client.sprint.CloseTaskAction;
import scrum.client.sprint.DeleteTaskAction;
import scrum.client.sprint.ReopenTaskAction;
import scrum.client.sprint.Task;
import scrum.client.sprint.UnclaimTaskAction;

import com.google.gwt.user.client.ui.Image;

public class TaskBlock extends AExtensibleBlockWidget<Task> implements TrashSupport, ClipboardSupport {

	private Task task;
	private TaskBlockContainer container;
	private TaskWidget taskWidget;

	public TaskBlock(TaskBlockContainer container) {
		this.container = container;
	}

	@Override
	protected Task getObject() {
		return task;
	}

	@Override
	protected void setObject(Task object) {
		this.task = object;
	}

	@Override
	protected void onCollapsedInitialization() {
		setIcon(Img.bundle.task16());
	}

	@Override
	protected void onUpdateHead() {
		setBlockTitle(task.getLongLabel(container.isShowOwner(), container.isShowRequirement()));
		setAdditionalStyleName(task.isDone() && isTaskOverview() ? "TaskBlock-taskClosed" : null);
		addMenuAction(new ClaimTaskAction(task));
		addMenuAction(new CloseTaskAction(task));
		addMenuAction(new ReopenTaskAction(task));
		addMenuAction(new UnclaimTaskAction(task));
		addMenuAction(new DeleteTaskAction(task));
	}

	@Override
	protected void onExtendedInitialization() {
		taskWidget = new TaskWidget(task);
	}

	@Override
	protected void onUpdateBody() {
		setContent(taskWidget.update());
	}

	public Task getTask() {
		return task;
	}

	public Image getClipboardIcon() {
		return Img.bundle.task16().createImage();
	}

	public String getClipboardLabel() {
		return task.getLabel();
	}

	public ABlockWidget getClipboardPayload() {
		return this;
	}

	public AScrumAction getTrashAction() {
		return new DeleteTaskAction(task);
	}

	private boolean isTaskOverview() {
		return container instanceof TaskOverviewWidget;
	}

	public static class TaskBlockFactory extends BlockWidgetFactory<Task> {

		private TaskBlockContainer container;

		public TaskBlockFactory(TaskBlockContainer container) {
			this.container = container;
		}

		public ABlockWidget<Task> createBlock() {
			return new TaskBlock(container);
		}
	}

	public TaskBlockContainer getContainer() {
		return this.container;
	}
}
