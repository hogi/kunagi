package scrum.client.tasks;

import ilarkesto.gwt.client.AFieldValueWidget;
import ilarkesto.gwt.client.AIntegerViewEditWidget;
import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.FieldsWidget;
import scrum.client.sprint.Task;
import scrum.client.workspace.Ui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class TaskWidget extends AWidget {

	private Task task;

	public TaskWidget(Task task) {
		this.task = task;
	}

	@Override
	protected Widget onInitialization() {
		FieldsWidget fields = new FieldsWidget();
		fields.setAutoUpdateWidget(Ui.get());

		fields.add("Label", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(task.getLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(task.getLabel());
			}

			@Override
			protected void onEditorSubmit() {
				task.setLabel(getEditorText());
			}

		});

		fields.add("Description", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(task.getDescription());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(task.getDescription());
			}

			@Override
			protected void onEditorSubmit() {
				task.setDescription(getEditorText());
			}

		});

		fields.add("Burned Work", new AIntegerViewEditWidget() {

			@Override
			protected void onIntegerViewerUpdate() {
				setViewerValue(task.getBurnedWork(), "hours");
			}

			@Override
			protected void onEditorUpdate() {
				setEditorValue(task.getBurnedWork());
			}

			@Override
			protected void onEditorSubmit() {
				Integer value = getEditorValue(0);
				if (value == null) value = 0;
				int previous = task.getBurnedWork();
				int diff = value - previous;
				task.setBurnedWork(value);
				task.adjustRemainingWork(diff);
			}

			@Override
			protected void onMinusClicked() {
				task.decrementBurnedWork();
				task.adjustRemainingWork(-1);
			}

			@Override
			protected void onPlusClicked() {
				task.incrementBurnedWork();
				task.adjustRemainingWork(1);
			}
		});

		fields.add("Remaining Work", new TaskRemainingWorkWidget(task));

		fields.add("Owner", new AFieldValueWidget() {

			@Override
			protected void onUpdate() {
				setText(task.getOwner() == null ? null : task.getOwner().getName());
			}
		});

		FlowPanel panel = new FlowPanel();
		panel.add(fields);
		panel.add(new CommentsWidget(task));
		return panel;
	}

}
