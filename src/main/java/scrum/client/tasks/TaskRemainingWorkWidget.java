package scrum.client.tasks;

import ilarkesto.gwt.client.AIntegerViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.common.AScrumAction;
import scrum.client.sprint.CloseTaskAction;
import scrum.client.sprint.ReopenTaskAction;
import scrum.client.sprint.Task;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TaskRemainingWorkWidget extends AWidget {

	private Task task;
	private HorizontalPanel panel;
	private RemainingWorkWidget remainingWork;
	private ToolbarWidget toolbar;

	public TaskRemainingWorkWidget(Task task) {
		this.task = task;
	}

	@Override
	protected Widget onInitialization() {
		remainingWork = new RemainingWorkWidget();
		toolbar = new ToolbarWidget();

		panel = new HorizontalPanel();
		panel.setStyleName("TaskRemainingWorkWidget");
		// panel.setWidth("100%");
		panel.add(remainingWork);
		panel.add(toolbar);
		return panel;
	}

	@Override
	protected void onUpdate() {
		toolbar.clear();
		if (task.isClosed()) {
			AScrumAction action = new ReopenTaskAction(task);
			if (action.isExecutable()) {
				toolbar.addButton(action);
			}
		} else {
			AScrumAction action = new CloseTaskAction(task);
			if (action.isExecutable()) {
				toolbar.addButton(action);
			}
		}
		super.onUpdate();
	}

	class RemainingWorkWidget extends AIntegerViewEditWidget {

		@Override
		protected void onIntegerViewerUpdate() {
			setViewerValue(task.getRemainingWork(), "hours");
		}

		@Override
		protected void onEditorUpdate() {
			setEditorValue(task.getRemainingWork());
		}

		@Override
		protected void onEditorSubmit() {
			task.setRemainingWork(getEditorValue(1));
		}

		@Override
		protected void onMinusClicked() {
			task.decrementRemainingWork();
		}

		@Override
		protected void onPlusClicked() {
			task.incrementRemainingWork();
		}

		@Override
		public boolean isEditable() {
			return task.isEditable();
		}

	}

}
