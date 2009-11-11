package scrum.client.tasks;

import ilarkesto.gwt.client.AFieldValueWidget;
import ilarkesto.gwt.client.AIntegerViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.sprint.Task;

import com.google.gwt.user.client.ui.Widget;

public class TaskWidget extends AWidget {

	private Task task;
	private boolean wideMode;

	public TaskWidget(Task task, boolean wideMode) {
		this.task = task;
		this.wideMode = wideMode;
	}

	@Override
	protected Widget onInitialization() {

		TableBuilder tb = new TableBuilder();
		tb.setCellSpacing(5);

		tb.addFieldRow("Label", new TextEditorWidget(task.getLabelModel()).switchToEditModeIfNull(), 3);

		tb.addFieldRow("Description", new RichtextEditorWidget(task.getDescriptionModel()), 3);

		tb.addField("Burned", new AIntegerViewEditWidget() {

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

			@Override
			public boolean isEditable() {
				return task.isEditable();
			}
		});

		tb.addFieldRow("Remaining", new TaskRemainingWorkWidget(task));

		tb.addFieldRow("Owner", new AFieldValueWidget() {

			@Override
			protected void onUpdate() {
				setText(task.getOwner() == null ? null : task.getOwner().getName());
			}
		}, 3);

		return wideMode ? TableBuilder.row(20, tb.createTable(), new CommentsWidget(task)) : Gwt.createFlowPanel(tb
				.createTable(), new CommentsWidget(task));
	}
}
