package scrum.client.sprint;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.ToolbarWidget;
import scrum.client.common.editable.AEditableIntegerShiftWidget;
import scrum.client.common.editable.AEditableTextWidget;
import scrum.client.common.editable.AEditableTextareaWidget;
import scrum.client.img.Img;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TaskWidget extends ABlockWidget {

	private Task task;

	public TaskWidget(Task task) {
		this.task = task;
	}

	@Override
	protected DropController createDropController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Widget buildContent() {
		if (!isExtended()) {
			// block is not extended -> return only a label with the summary
			return new Label(task.getSummary());
		}

		// block is extended -> create an ItemFieldsWidget
		ItemFieldsWidget fieldsWidget = new ItemFieldsWidget();

		fieldsWidget.addField("Description", new AEditableTextWidget() {

			@Override
			protected String getText() {
				return task.getLabel();
			}

			@Override
			protected void setText(String text) {
				task.setLabel(text);
				rebuild();
			}

		});

		fieldsWidget.addField("Burned Work", new AEditableIntegerShiftWidget() {

			@Override
			protected Integer getValue() {
				return task.getBurnedWork();
			}

			@Override
			protected void setValue(Integer value) {
				if (value == 0) value = 0;
				int previous = task.getBurnedWork();
				int diff = previous - value;
				task.setBurnedWork(value);
				Integer remaining = task.getRemainingWork();
				if (remaining == null) remaining = 0;
				remaining -= diff;
				if (remaining < 0) remaining = 0;
				controller.dataChanged(TaskWidget.this);
			}

			@Override
			protected void onMinusClicked() {
				task.decrementBurnedWork();
				task.incrementRemainingWork();
				controller.dataChanged(TaskWidget.this);
			}

			@Override
			protected void onPlusClicked() {
				task.incrementBurnedWork();
				task.decrementRemainingWork();
				controller.dataChanged(TaskWidget.this);
			}
		});

		fieldsWidget.addField("Remaining Work", new AEditableIntegerShiftWidget() {

			@Override
			protected Integer getValue() {
				return task.getRemainingWork();
			}

			@Override
			protected void setValue(Integer value) {
				task.setRemainingWork(value);
				controller.dataChanged(TaskWidget.this);
			}

			@Override
			protected void onMinusClicked() {
				task.decrementRemainingWork();
				controller.dataChanged(TaskWidget.this);
			}

			@Override
			protected void onPlusClicked() {
				task.incrementRemainingWork();
				controller.dataChanged(TaskWidget.this);
			}

		});

		fieldsWidget.addField("Note", new AEditableTextareaWidget() {

			@Override
			protected String getText() {
				return task.getNotice();
			}

			@Override
			protected void setText(String text) {
				task.setNotice(text);
				rebuild();
			}

		});

		fieldsWidget.addField("Owner", new Label(task.getOwner() == null ? "No owner specified." : task.getOwner()
				.getName()));

		return fieldsWidget;
	}

	@Override
	protected Widget buildToolbar() {
		if (!isExtended()) {
			// block is not extended -> no toolbar
			return null;
		}

		// block is extended -> create toolbar with buttons
		ToolbarWidget toolbar = new ToolbarWidget();
		if (!task.isDone()) {
			toolbar.addButton("Own").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					task.setOwner(ScrumGwtApplication.get().getUser());
					rebuild();
				}

			});
		}

		toolbar.addButton(Img.bundle.delete16().createImage(), "Delete").addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				delete();
			}

		});

		if (!task.isDone()) {
			toolbar.addButton(Img.bundle.done16().createImage(), "Done").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					task.setDone();
					rebuild();
				}

			});
		}

		return toolbar;
	}

	@Override
	protected String getBlockTitle() {
		return task.getLabel();
	}

	@Override
	protected AbstractImagePrototype getIcon16() {
		if (task.isDone()) return Img.bundle.taskDoneIcon16();
		return Img.bundle.taskIcon16();
	}

	@Override
	protected AbstractImagePrototype getIcon32() {
		if (task.isDone()) return Img.bundle.taskDoneIcon32();
		return Img.bundle.taskIcon32();
	}

	@Override
	public void delete() {

		task.getRequirement().deleteTask(task);
		task.getTaskListWidget().update(null);

	}
}
