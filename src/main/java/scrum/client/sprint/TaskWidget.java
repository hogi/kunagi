package scrum.client.sprint;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.editable.AEditableIntegerShiftWidget;
import scrum.client.common.editable.AEditableTextWidget;
import scrum.client.common.editable.AEditableTextareaWidget;
import scrum.client.img.Img;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
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
				task.setBurnedWork(value == null ? 0 : value);
				controller.dataChanged(TaskWidget.this);
			}

			@Override
			protected void onMinusClicked() {
				task.decrementBurnedWork();
			}

			@Override
			protected void onPlusClicked() {
				task.incrementBurnedWork();
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
			}

			@Override
			protected void onPlusClicked() {
				task.incrementRemainingWork();
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
		VerticalPanel toolbar = new VerticalPanel();
		toolbar.setStyleName("Toolbar");

		if (!task.isDone()) {
			Button ownButton = new Button("Own");
			ownButton.addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					task.setOwner(ScrumGwtApplication.get().getUser());
					rebuild();
				}

			});
			toolbar.add(ownButton);
		}

		Button deleteButton = new Button("Delete");
		deleteButton.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				delete();
			}

		});
		toolbar.add(deleteButton);

		if (!task.isDone()) {
			Button doneButton = new Button("Done");
			doneButton.addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					task.setDone();
					rebuild();
				}

			});
			toolbar.add(doneButton);
		}

		return toolbar;
	}

	@Override
	protected String getBlockTitle() {
		return task.getLabel();
	}

	@Override
	protected AbstractImagePrototype getIcon() {
		if (task.isDone()) return Img.bundle.taskDoneIcon32();
		return Img.bundle.taskIcon32();
	}

	@Override
	public void delete() {

		task.getStory().deleteTask(task);
		task.getTaskListWidget().update(null);

	}
}
