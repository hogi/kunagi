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

	public Task getTask() {
		return task;
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
				int diff = value - previous;
				task.setBurnedWork(value);
				task.adjustRemainingWork(diff);
				notifyListControllerDataChanged();
				rebuild();
			}

			@Override
			protected void onMinusClicked() {
				task.decrementBurnedWork();
				task.adjustRemainingWork(-1);
				notifyListControllerDataChanged();
				rebuild();
			}

			@Override
			protected void onPlusClicked() {
				task.incrementBurnedWork();
				task.adjustRemainingWork(1);
				notifyListControllerDataChanged();
				rebuild();
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
				notifyListControllerDataChanged();
				rebuild();
			}

			@Override
			protected void onMinusClicked() {
				task.decrementRemainingWork();
				notifyListControllerDataChanged();
				rebuild();
			}

			@Override
			protected void onPlusClicked() {
				task.incrementRemainingWork();
				notifyListControllerDataChanged();
				rebuild();

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
					notifyListControllerDataChanged();
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
		notifyListControllerDataChanged();
	}
}
