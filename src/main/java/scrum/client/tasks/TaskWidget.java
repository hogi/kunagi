package scrum.client.tasks;

import ilarkesto.gwt.client.AFieldValueWidget;
import ilarkesto.gwt.client.AIntegerViewEditWidget;
import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.sprint.Task;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class TaskWidget extends AWidget {

	private Task task;

	public TaskWidget(Task task) {
		this.task = task;
	}

	@Override
	protected Widget onInitialization() {

		TableBuilder tb = new TableBuilder();

		tb.addFieldRow("Label", new ATextViewEditWidget() {

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

		}, 3);

		tb.addFieldRow("Description", new ARichtextViewEditWidget() {

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

		}, 3);

		tb.addField("Burned Work", new AIntegerViewEditWidget() {

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

		tb.addFieldRow("Remaining Work", new TaskRemainingWorkWidget(task));

		tb.addFieldRow("Owner", new AFieldValueWidget() {

			@Override
			protected void onUpdate() {
				setText(task.getOwner() == null ? null : task.getOwner().getName());
			}
		}, 3);

		FlowPanel panel = new FlowPanel();
		panel.add(tb.createTable());
		panel.add(new CommentsWidget(task));
		return panel;
	}
}
